package vo;

public class MenuVO {
//    no NUMBER primary key,
//    category VARCHAR2(60) not null,
//    name VARCHAR2(60) not null,
//    image VARCHAR2(100),
//    price NUMBER not null,
//    valid NUMBER default 0
    private int no;
    private String category;
    private String name;
    private String image;
    private int price;
    private int valid;

    public MenuVO() {
    }

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

    @Override
    public String toString() {
        return "MenuVO{" +
                "no=" + no +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", valid=" + valid +
                '}';
    }
}
