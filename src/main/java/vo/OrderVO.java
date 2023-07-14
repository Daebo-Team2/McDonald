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
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<OrderListVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<OrderListVO> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "no=" + no +
                ", price=" + price +
                ", orderTime=" + orderTime +
                ", storeNo=" + storeNo +
                ", storeName='" + storeName + '\'' +
                ", menuList=" + menuList +
                '}';
    }
}