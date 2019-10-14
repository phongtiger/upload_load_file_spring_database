package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.ImageFrom;
import com.codegym.model.Products;
import com.codegym.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class ProductsController {
    @ModelAttribute("cart")
    public Cart setUpCart() {
        return new Cart();
    }
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
    @GetMapping("/views-cart")
    public ModelAndView viewCart(@ModelAttribute ("cart") Cart cart) {
        ArrayList<Products> productsCart = (ArrayList<Products>) cart.findAllInCart();
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("carts",productsCart);
//        System.out.println(productsCart.get(0).);
        return modelAndView;
    }
    @PostMapping("/add-product-to-cart")
    public ModelAndView addProductToCartCart(@RequestParam long id,@ModelAttribute ("cart") Cart cart) {
        System.out.println(id);
        cart.addProductIntoCart(productsService.findById(id));
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("product", productsService.findById(id));
        modelAndView.addObject("message","Da them thanh cong vao gio hang");
        return modelAndView;
    }
    @GetMapping("/create-upload")
    public ModelAndView showFormUpLoadImg() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product",new Products());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView createProduct(@ModelAttribute("product") ImageFrom productFile){
        MultipartFile multipartFile = productFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String name = productFile.getName();
        String maHang = productFile.getMaHang();
        int price = productFile.getPrice();
        String detail = productFile.getDetail();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Products products = new Products(fileName,name,maHang,price,detail);
        productsService.addProduct(products);
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
    @GetMapping("/detail-product/{id}")
    public ModelAndView detailProduct(@PathVariable Long id) {
        Products products =  productsService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("product",products);
        return modelAndView;
    }
    @PostMapping("/edit-img")
    public ModelAndView saveEditImg(@ModelAttribute("product") ImageFrom productFile, RedirectAttributes redirectAttributes){
        MultipartFile multipartFile = productFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String name = productFile.getName();
        String maHang = productFile.getMaHang();
        int price = productFile.getPrice();
        String detail = productFile.getDetail();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Products products = new Products(fileName,name,maHang,price,detail);
        products.setId(productFile.getId());
        productsService.addProduct(products);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        redirectAttributes.addFlashAttribute("message","bạn vừa thêm 1 sản phẩm mới");
        return modelAndView;
    }
}
