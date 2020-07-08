package com.app.ecommerce.ui.home.product;

public class EntityProduct {

    private String id;
    private String name;
    private String price_current;
    private String price_previous;
    private String discount;
    private String description_short;
    private String description_long;
    private String specifications;
    private String miniPhoto;
    private String category_id;

    public EntityProduct() {
    }

    public String getId() {
        return id;
    }

    public String getPrice_current() {
        return price_current;
    }

    public void setPrice_current(String price_current) {
        this.price_current = price_current;
    }

    public String getPrice_previous() {
        return price_previous;
    }

    public void setPrice_previous(String price_previous) {
        this.price_previous = price_previous;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }


    public String getMiniPhoto() {
        return miniPhoto;
    }

    public void setMiniPhoto(String miniPhoto) {
        this.miniPhoto = miniPhoto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription_short() {
        return description_short;
    }

    public void setDescription_short(String description_short) {
        this.description_short = description_short;
    }

    public String getDescription_long() {
        return description_long;
    }

    public void setDescription_long(String description_long) {
        this.description_long = description_long;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
