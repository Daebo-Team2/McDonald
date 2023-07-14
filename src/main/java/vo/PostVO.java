package vo;

import java.util.Date;

public class PostVO { // Model 
	
	private int no; // 글번호 	
	private int storeno; // 작성 가맹점 번호 
	private String storename;
	private String title; // 제목 
	private String	content; // 작성 내용 
	private Date time; // 작성 시간  
	private int status; // 답글 확인 
	private int reno; // 답글 번호 
	
	public PostVO() {}
	

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReno() {
		return reno;
	}

	public void setReno(int reno) {
		this.reno = reno;
	}
	
	public String getStorename() {
		return this.storename;
	}


	@Override
	public String toString() {
		return "PostVO [no=" + no + ", storeno=" + storeno + ", title=" + title + ", content=" + content + ", time="
				+ time + ", status=" + status + ", reno=" + reno + "]";
	}
	
	

}
