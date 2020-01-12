package com.fh.controller;

import com.fh.model.Brand;
import com.fh.model.ServerResponse;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public ServerResponse queryBrandList(){
        try {
           List<Brand> brandList = brandService.queryBrandList();
           return ServerResponse.success(brandList);
        } catch (Exception e) {
            e.printStackTrace();
           return  ServerResponse.error();
        }

    }

}
