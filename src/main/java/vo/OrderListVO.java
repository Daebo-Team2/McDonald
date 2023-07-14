package vo;

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

    public void setMenuNo(int menuNo) {
        this.menuNo = menuNo;
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

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
