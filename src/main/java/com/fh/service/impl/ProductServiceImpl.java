package com.fh.service.impl;

import com.fh.mapper.ProductMapper;
import com.fh.model.Product;
import com.fh.service.ProductService;
import com.fh.util.RedisKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Product> queryProductList() {
        //先判断redis中是否有数据分类
        if(redisTemplate.hasKey(RedisKeyConstant.KEY_Product)){
            return(List<Product>) redisTemplate.opsForValue().get(RedisKeyConstant.KEY_Product);
        }
        List<Product> productList =productMapper.queryProductList();
        redisTemplate.opsForValue().set(RedisKeyConstant.KEY_Product,productList);
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        return productMapper.getProductById(productId);
    }

    @Override
    public Long updateProductStock(Integer productId, Long count) {
        return productMapper.updateProductStock(productId,count);
    }
}
