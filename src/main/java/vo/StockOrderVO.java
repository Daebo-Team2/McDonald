package vo;

import java.sql.Timestamp;

/*STOCKORDER
NO       NOT NULL NUMBER       
STORENO  NOT NULL NUMBER       
FOODNAME NOT NULL VARCHAR2(60) 
QUANTITY NOT NULL NUMBER       
TIME     NOT NULL DATE         
STATUS   NOT NULL NUMBER*/

public class StockOrderVO {
	
	private int no;
	private int storeno;
	private String foodname;
	private int quantity;
	private Timestamp time;
	private int status;
	
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
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	
	

}
