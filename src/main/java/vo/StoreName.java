package vo;

import java.util.HashMap;
import java.util.List;

import dao.StoreDAO;

public class StoreName { //storeno로 name 얻어오기
	static HashMap<Integer, StoreVO> storeMap = null;
	static public boolean isUpdate = false; //update,insert,delete 등 데이터 변경이 일어날때 true로 설정
	
	static String getStoreName(int no) {
		
		if (storeMap == null || isUpdate) { //이전에 저장해둔 정보가 없거나, 업데이트가 true일 경우
			StoreDAO dao = new StoreDAO();
			List<StoreVO> list = dao.selectAllStore();
			storeMap = new HashMap();
			for ( StoreVO vo : list ) {
				storeMap.put(vo.getNo(), vo);
			}
			isUpdate = false;
		}
		
		StoreVO vo = storeMap.get(no);
		return vo == null ? null : vo.getName(); //vo가 null일때 null 반환, null아닐때 이름 반환 (안해주면 NullPointerException발생할수있다.)
	}
}
