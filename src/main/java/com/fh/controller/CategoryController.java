package com.fh.controller;


import com.fh.model.Category;
import com.fh.model.ServerResponse;
import com.fh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {


  @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ServerResponse queryCategoryList(){

        Map<String,Object> resultMap = new HashMap<>();
        try {
            List<Category> categoryList = categoryService.queryCategoryList();
            return ServerResponse.success(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.success();
        }

    }

}
