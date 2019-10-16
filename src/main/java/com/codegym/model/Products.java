package com.codegym.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Products  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String name;
    private String maHang;
    private int price;
    private String detail;
    private int count =1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Products() {
    }
    public Products(String image, String name, String maHang, int price, String detail) {
        this.image = image;
        this.name = name;
        this.maHang = maHang;
        this.price = price;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return maHang.equals(products.maHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHang);
    }
}
