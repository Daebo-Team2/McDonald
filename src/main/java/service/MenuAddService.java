package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MenuDAO;

public class MenuAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		MenuDAO dao = new MenuDAO();

		String category = request.getParameter("category").trim();
		String name = request.getParameter("name").trim();
		int price = Integer.parseInt(request.getParameter("price").trim());
		String[] foodnos = request.getParameterValues("foodno");

		int menuno = dao.addMenu(category, name, price);

		if  ( menuno ==0 ) {
			return null;
		}

		for (int i = 0; i < foodnos.length; i++ ) {
			dao.addRecipe(menuno, Integer.parseInt(foodnos[i]) );
		}
		String fileName = null;
		String extension = null;

		try {
			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				if (part.getHeader("Content-Disposition").contains("filename=")) {
					fileName = part.getHeader("Content-Disposition");
					if (part.getSize() > 0) {
						String orifilename= part.getSubmittedFileName();
						extension = orifilename.substring(orifilename.lastIndexOf(".")+1,orifilename.length());
						fileName = Integer.toString(menuno);
						ServletContext sc = request.getServletContext();
						Properties properties = new Properties();
						properties.load(new FileReader(sc.getRealPath(sc.getInitParameter("fileSavePath"))));
						File file = new File(properties.get("path") + fileName + "." + extension);
						if (!file.exists()) {
							file.createNewFile();
						}
						part.write(properties.get("path") + fileName + "." + extension);
					}
				}
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		String image = "/menuImage/" + fileName + "." + extension;
		dao.updateMenu(menuno, image);

		forward.setPath("/super/menuContent.do");
		return forward;
	}

}
