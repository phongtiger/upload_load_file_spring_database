package com.codegym.controller;

import com.codegym.model.ImageFrom;
import com.codegym.model.Products;
import com.codegym.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductsController {
    @Autowired
    Environment env;
    @Autowired
    private ProductsService productsService;
    @GetMapping("/home")
    public ModelAndView showListImg() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", productsService.findAll());
        return modelAndView;
    }
    @GetMapping("/create-upload")
    public ModelAndView showFormUpLoadImg() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product",new Products());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView createProduct(@ModelAttribute("product") ImageFrom productFile, Pageable pageable){
        MultipartFile multipartFile = productFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Products products = new Products(fileName);
        productsService.addImage(products);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("message","bạn vừa thêm 1 sản phẩm mới");
        return modelAndView;
    }
    @GetMapping("/edit-product/{id}")
    public ModelAndView editImg(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", productsService.findById(id));
        return modelAndView;
    }
    @PostMapping("/edit-img")
    public ModelAndView saveEditImg(@ModelAttribute("product") ImageFrom productFile, Pageable pageable, RedirectAttributes redirectAttributes){
        MultipartFile multipartFile = productFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Products products = new Products(fileName);
        products.setId(productFile.getId());
        productsService.addImage(products);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        redirectAttributes.addFlashAttribute("message","bạn vừa thêm 1 sản phẩm mới");
        return modelAndView;
    }
}
