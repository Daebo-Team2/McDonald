package vo;

/*NO       NOT NULL NUMBER        
CATEGORY NOT NULL VARCHAR2(60)  
NAME     NOT NULL VARCHAR2(60)  
IMAGE             VARCHAR2(100) 
PRICE    NOT NULL NUMBER        
VALID             NUMBER */

public class MenuVO {
	private int no;
	private String category;
	private String name;
	private String image;
	private int price;
	private int valid;
	
	public MenuVO() {	} //생성자함수

	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

}
