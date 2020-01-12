package com.fh.controller;

import com.fh.model.Product;
import com.fh.model.ServerResponse;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ServerResponse queryProductList(){
        try {
            List<Product> productList = productService.queryProductList();
            return ServerResponse.success(productList);
        } catch (Exception e) {
            e.printStackTrace();
           return  ServerResponse.success();
        }
    }
}
