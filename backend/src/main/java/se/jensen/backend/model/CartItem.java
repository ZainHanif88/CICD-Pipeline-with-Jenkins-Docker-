package se.jensen.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CartItem {


    @JsonProperty("productId") public int productId;
    @JsonProperty("title") public String title;
    @JsonProperty("color") public String color;
    @JsonProperty("size") public String size;
    @JsonProperty("previewImage") public String previewImage;
    @JsonProperty("quantity") public int quantity;

    public CartItem() {

    }

    public CartItem(int productId, String title, String color, String size, String previewImage) {
        this.productId = productId;
        this.title = title;
        this.color = color;
        this.size = size;
        this.previewImage = previewImage;



    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem item = (CartItem) o;
        return productId == item.productId && quantity == item.quantity && Objects.equals(title, item.title) && Objects.equals(color, item.color) && Objects.equals(size, item.size) && Objects.equals(previewImage, item.previewImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, color, size, previewImage, quantity);
    }
}

