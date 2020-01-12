package com.fh.service.impl;

import com.fh.mapper.BrandMapper;
import com.fh.model.Brand;
import com.fh.service.BrandService;
import com.fh.util.RedisKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl  implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Brand> queryBrandList() {
        //先判断redis中是否有数据分类
        if(redisTemplate.hasKey(RedisKeyConstant.KEY_Brand)){
            return(List<Brand>) redisTemplate.opsForValue().get(RedisKeyConstant.KEY_Brand);
        }
        List<Brand> brandList =brandMapper.queryBrandList();
        redisTemplate.opsForValue().set(RedisKeyConstant.KEY_Brand,brandList);
        return brandList;
    }
}
