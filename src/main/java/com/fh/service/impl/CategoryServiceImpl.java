package com.fh.service.impl;


import com.fh.mapper.CategoryMapper;
import com.fh.model.Category;
import com.fh.service.CategoryService;
import com.fh.util.RedisKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Category> queryCategoryList() {
         //先判断redis中是否有数据分类
        if(redisTemplate.hasKey(RedisKeyConstant.KEY_CATEGORY)){
            return(List<Category>) redisTemplate.opsForValue().get(RedisKeyConstant.KEY_CATEGORY);
        }
        List<Category> categoryList =categoryMapper.queryCategoryList();
        redisTemplate.opsForValue().set(RedisKeyConstant.KEY_CATEGORY,categoryList);
        return categoryList;
    }
}
