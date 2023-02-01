package se.jensen.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable
{
    @JsonProperty("id") public int id;
    @JsonProperty("category") public String category;
    @JsonProperty("title") public String title;
    @JsonProperty("description") public String description;
    @JsonProperty("previewImage") public String previewImage;
    @JsonProperty("colorVariants") public List<ColorVariant> colorVariants;
    public Product()
    {
        List<ColorVariant> colorVariants = new ArrayList<>();
    }

    public Product(int id, String category, String title, String description, String previewImage) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.previewImage = previewImage;
        this.colorVariants = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public List<ColorVariant> getColorVariants() {
        return colorVariants;
    }

    public void setColorVariants(List<ColorVariant> colorVariants) {
        this.colorVariants = colorVariants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(category, product.category) && Objects.equals(title, product.title) && Objects.equals(description, product.description) && Objects.equals(previewImage, product.previewImage) && Objects.equals(colorVariants, product.colorVariants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, description, previewImage, colorVariants);
    }




}
