package vo;

/*STOCK
FOODNAME          VARCHAR2(60) 
QUANTITY NOT NULL NUMBER       
STORENO  NOT NULL NUMBER */

public class StockVO {
	
	private String foodname;
	private int quantity;
	private int storeno;
	
	//생성자합수
	public StockVO() {	}
	
	
	
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
	public int getStoreno() {
		return storeno;
	}
	public void setStoreno(int storeno) {
		this.storeno = storeno;
	}
	
	
	
	

}
