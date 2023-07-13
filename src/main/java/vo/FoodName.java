package vo;

import java.util.HashMap;
import java.util.List;

import dao.FoodDAO;

public class FoodName { //foodno로 name 얻어오기
	static HashMap<Integer, FoodVO> foodMap = null; 
	static boolean isUpdate = false; //update,insert,delete 등 데이터 변경이 일어날때 true로 설정
	
	static String getFoodName(int no) {
		if (foodMap == null || isUpdate) { //이전에 저장해둔 정보가 없거나, 업데이트가 true일 경우
			FoodDAO dao = new FoodDAO(); 
			List<FoodVO> list = dao.selectAll();
			foodMap = new HashMap();
			for (FoodVO vo : list) {
				foodMap.put(vo.getNo(), vo);
			}
			isUpdate = false;
		}
		
		FoodVO vo = foodMap.get(no);
		return vo == null ? null : vo.getName(); //vo가 null일때 null 반환, null아닐때 이름 반환 (안해주면 NullPointerException발생할수있다.)
	}
	
}


