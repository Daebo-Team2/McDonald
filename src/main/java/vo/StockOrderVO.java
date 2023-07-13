package vo;

import java.sql.Timestamp;
import java.util.List;


/*STOCKORDER
NO       NOT NULL NUMBER       
STORENO  NOT NULL NUMBER       
TIME     NOT NULL DATE         
STATUS   NOT NULL NUMBER*/

public class StockOrderVO {
	
	private int no;
	private int storeno;
	private Timestamp time;
	private int status; 
	private String storename;
	private List<StockOrderListVO> list;
	
	
	//생성자함수
	public StockOrderVO() {	}
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getStoreno() {
		return storeno;
	}
	public void setStoreno(int storeno) {
		this.storeno = storeno;
		this.storename = StoreName.getStoreName(storeno);
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStorename() {
		return storename;
	}
	public List<StockOrderListVO> getList() {
		return list;
	}
	public void setList(List<StockOrderListVO> list) {
		this.list = list;
		//System.out.println("this.list : " + this.list);
	}



	@Override
	public String toString() {
		return "StockOrderVO [no=" + no + ", storeno=" + storeno + ", time=" + time + ", status=" + status
				+ ", storename=" + storename + ", list=" + list + "]";
	}
	
	

}
