package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageFrom {
    private Long id;
    private MultipartFile image;
    private String name;
    private String maHang;
    private int price;
    private String detail;

    public ImageFrom() {
    }

    public ImageFrom(Long id, MultipartFile image, String name, String maHang, int price, String detail) {
        this.id = id;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
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
}
