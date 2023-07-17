package vo;

/*
 * RECIPE
MENUNO NOT NULL NUMBER 
FOODNO NOT NULL NUMBER
 */ 

public class RecipeVO {

	private int menuno;
	private int foodno;
	private String foodname;

	public RecipeVO() {	} //생성자 함수

	public int getMenuno() {
		return menuno;
	}
	public void setMenuno(int menuno) {
		this.menuno = menuno;
	}
	public int getFoodno() {
		return foodno;
	}
	public void setFoodno(int foodno) {
		this.foodno = foodno;
		this.foodname = FoodName.getFoodName(foodno);
	}
	public String getFoodname() {
		return foodname;
	}

	@Override
	public String toString() {
		return "RecipeVO [menuno=" + menuno + ", foodno=" + foodno + ", foodname=" + foodname + "]";
	}
	
	

}
