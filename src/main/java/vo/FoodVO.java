package vo;

/*FOOD
NO   NOT NULL NUMBER       
NAME NOT NULL VARCHAR2(60)*/

public class FoodVO {
	
	private int no;
	private String name;
	
	//생성자함수
	public FoodVO() { }
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
