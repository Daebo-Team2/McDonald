package vo;

/*STORE
NO     NOT NULL NUMBER       
NAME   NOT NULL VARCHAR2(60) 
ID     NOT NULL VARCHAR2(60) 
PWD    NOT NULL VARCHAR2(60) 
TEL    NOT NULL VARCHAR2(60) 
STATUS          NUMBER      */

public class StoreVO {
	
	private int no;
	private String name;
	private String id;
	private String pwd;
	private String tel;
	private int status;
	
	//생성자함수
	public StoreVO() {	}

	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
