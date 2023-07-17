package vo;

import java.sql.SQLException;

public class OrderListVO {
    private int orderNo;
    private int menuNo;
    private int quantity;
    private String menuName;
    private int price;

    public OrderListVO() {
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(int menuNo) throws SQLException {
        this.menuNo = menuNo;
        this.menuName = MenuNamePrice.getMenuName(menuNo);
        this.price = MenuNamePrice.getMenuPrice(menuNo);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderListVO{" +
                "orderNo=" + orderNo +
                ", menuNo=" + menuNo +
                ", quantity=" + quantity +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                '}';
    }
}
