package vo;

/*
STOCKORDERNO NOT NULL NUMBER 
FOODNO       NOT NULL NUMBER 
QUANTITY     NOT NULL NUMBER
*/

public class StockOrderListVO {
	
	private int stockorderno;
	private int foodno;
	private int quantity;
	private String foodname;
	
	public StockOrderListVO() {	}

	
	
	public int getStockorderno() {
		return stockorderno;
	}

	public void setStockorderno(int stockorderno) {
		this.stockorderno = stockorderno;
	}

	public int getFoodno() {
		return foodno;
	}

	public void setFoodno(int foodno) {
		this.foodno = foodno;
		this.foodname = FoodName.getFoodName(foodno);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFoodname() {
		return foodname;
	}



	@Override
	public String toString() {
		return "StockOrderListVO [stockorderno=" + stockorderno + ", foodno=" + foodno + ", quantity=" + quantity
				+ ", foodname=" + foodname + "]";
	}
	
	
	
}
