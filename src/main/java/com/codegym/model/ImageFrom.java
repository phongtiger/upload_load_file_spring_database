package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;



public class ImageFrom {
    private Long id;
    private MultipartFile image;

    public ImageFrom() {
    }

    public ImageFrom(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
