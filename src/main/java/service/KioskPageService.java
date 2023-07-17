package service;

import vo.MenuName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KioskPageService implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("setList", MenuName.getCategoryList("세트"));
        request.setAttribute("burgerList", MenuName.getCategoryList("버거"));
        request.setAttribute("drinkList", MenuName.getCategoryList("음료/카페"));
        request.setAttribute("sideList", MenuName.getCategoryList("사이드"));
        request.setAttribute("dessertList", MenuName.getCategoryList("디저트"));

        ActionForward forward = new ActionForward();
        forward.setPath("/WEB-INF/pages/kioskPage.jsp");
        return forward;
    }
}
