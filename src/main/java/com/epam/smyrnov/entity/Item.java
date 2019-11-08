package com.epam.smyrnov.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Item extends Entity {

    private static final long serialVersionUID = -3438686339632143540L;
    private String category;
    private String name;
    private BigDecimal price;
    private Date date;
    private String size;
    private String color;
    private int weight;
    private List<String> imageURLs;

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return weight == item.weight &&
                category.equals(item.category) &&
                name.equals(item.name) &&
                price.equals(item.price) &&
                date.equals(item.date) &&
                size.equals(item.size) &&
                color.equals(item.color) &&
                imageURLs.equals(item.imageURLs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, price, date, size, color, weight, imageURLs);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ").append(id).append("<br>");
        stringBuilder.append("Category: ").append(category).append("<br>");
        stringBuilder.append("Name: ").append(name).append("<br>");
        stringBuilder.append("Size: ").append(size).append("<br>");
        stringBuilder.append("Weight: ").append(weight).append("<br>");
        stringBuilder.append("Color: ").append(color).append("<br>");
        stringBuilder.append("Date of addition: ").append(date).append("<br>");
        stringBuilder.append("Image URLs: ").append(imageURLs).append("<br>");
        return stringBuilder.toString();
    }
}
