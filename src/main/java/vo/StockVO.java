package vo;

/*STOCK
FOODNO			  NUMBER 
QUANTITY NOT NULL NUMBER       
STORENO  NOT NULL NUMBER */

public class StockVO {
	
	private int foodno;
	private int quantity;
	private int storeno;
	private String foodName;
	private String storeName;
	
	//생성자합수
	public StockVO() {	}
	
	
	public int getFoodno() {
		return foodno;
	}
	public void setFoodno(int foodno) {
		this.foodno = foodno;
		this.foodName = FoodName.getFoodName(foodno);
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
		this.storeName = StoreName.getStoreName(storeno);
		System.out.println("storeName: " + this.storeName);
	}
	public String getFoodName() {
		return foodName;
	}
//	public void setFoodName(String foodName) {
//		this.foodName = foodName;
//	}
	public String getStoreName() {
		return storeName;
	}
//	public void setStoreName(String storeName) {
//		this.storeName = storeName;
//	}
	
}
