package todo.core.java8.nashorn;

import lombok.Data;

@Data
public class Product {

    private String name;
    private double price;
    private int stock;
    private double valueOfGoods;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }
}
