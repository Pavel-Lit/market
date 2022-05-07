package ru.geekbrains.march.market.api;



import java.math.BigDecimal;


public class CartItemDto {

    private Long productId;
    private BigDecimal price;
    private int quantity;
    private String productTitle;
    private  BigDecimal pricePerProduct;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public CartItemDto() {
    }

    public CartItemDto(Long productId, BigDecimal price, int quantity, String productTitle, BigDecimal pricePerProduct) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.productTitle = productTitle;
        this.pricePerProduct = pricePerProduct;
    }
}
