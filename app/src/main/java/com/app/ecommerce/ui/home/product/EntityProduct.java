package com.app.ecommerce.ui.home.product;

public class EntityProduct {

    private String id;
    private String name;
    private String description_short;
    private String description_long;
    private String specifications;

    public EntityProduct() {
    }

    public String getId() {
        return id;
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
