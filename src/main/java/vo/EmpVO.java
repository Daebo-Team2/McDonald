package vo;

import java.sql.Date;

public class EmpVO {
    private int no;
    private String name;
    private int storeNo;
    private Date hireDate;
    private String tel;
    private int pay;
    private int wTime;
    private Date inTime;

    public EmpVO() {
    }

    public EmpVO(String name, int storeNo, String tel, int pay) {
        this.name = name;
        this.storeNo = storeNo;
        this.tel = tel;
        this.pay = pay;
    }

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

    public int getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(int storeNo) {
        this.storeNo = storeNo;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getwTime() {
        return wTime;
    }

    public void setwTime(int wTime) {
        this.wTime = wTime;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Override
    public String toString() {
        return "EmpVO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", storeNo=" + storeNo +
                ", hireDate=" + hireDate +
                ", tel='" + tel + '\'' +
                ", pay=" + pay +
                ", wTime=" + wTime +
                ", inTime=" + inTime +
                '}';
    }
}
