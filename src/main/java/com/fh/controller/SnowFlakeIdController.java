package com.fh.controller;

import com.fh.model.Member;
import com.fh.model.ServerResponse;
import com.fh.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("snowflake")
public class SnowFlakeIdController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("getId")
    public ServerResponse getId(){
        try {
            String token = IdUtil.createIdWithoutTimestamp() + "";
            redisTemplate.opsForHash().put(token,token,token);
            return ServerResponse.success(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

}
