package vo;

import java.sql.Date;
import java.util.List;

public class OrderVO {
    private int no;
    private int price;
    private Date orderTime;
    private int storeNo;
    private String storeName;
    private List<OrderListVO> menuList;
    private String place;

    public OrderVO() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(int storeNo) {
        this.storeNo = storeNo;
        this.storeName = StoreName.getStoreName(storeNo);
    }

    public String getStoreName() {
        return storeName;
    }

    public List<OrderListVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<OrderListVO> menuList) {
        this.menuList = menuList;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "{" +
                "no=" + no +
                ", price=" + price +
                ", orderTime=" + orderTime +
                ", storeNo=" + storeNo +
                ", storeName=" + storeName + '\'' +
                ", menuList=" + menuList +
                '}';
    }
}
