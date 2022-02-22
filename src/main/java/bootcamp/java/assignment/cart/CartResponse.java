package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.product.Product;

import java.util.List;

public class CartResponse {
    private List<Product> products;
    private int itemsCount;
    private float total;

    public CartResponse() {

    }

    public CartResponse(List<Product> products) {
        this.products = products;
        this.itemsCount = this.products.size();

        for (Product product:this.products) {
            this.total += product.getPrice();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
