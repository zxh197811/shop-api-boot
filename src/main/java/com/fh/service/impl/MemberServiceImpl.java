package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.mapper.MemberMapper;
import com.fh.model.Area;
import com.fh.model.Member;
import com.fh.model.ResponseEnum;
import com.fh.model.ServerResponse;
import com.fh.service.MemberService;
import com.fh.util.JwtUtil;
import com.fh.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ServerResponse addMember(Member member, String code) {
        //会员信息非空校验
        if(member == null){
            return ServerResponse.error(ResponseEnum.MEMBER_INFO_IS_NULL);
        }

        //用户名非空校验
        if(StringUtils.isBlank(member.getUsername())){
            return ServerResponse.error(ResponseEnum.MEMBER_USERNAME_IS_NULL);
        }

        //密码非空校验
        if(StringUtils.isBlank(member.getPassword())){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_IS_NULL);
        }

        //手机号非空校验
        if(StringUtils.isBlank(member.getPhoneNumber())){
            return ServerResponse.error(ResponseEnum.MEMBER_PHONE_NUMBER_IS_NULL);
        }

        //手机号合法性校验
        if(member.getPhoneNumber().matches("/^1\\d{10}$/")){
            return ServerResponse.error(ResponseEnum.MEMBER_PHONE_NUMBER_IS_ILLEGAL);
        }

        //验证码非空校验
        if(StringUtils.isBlank(code)){
            return ServerResponse.error(ResponseEnum.VERIFY_CODE_IS_NULL);
        }

        //根据用户输入的手机号从redis中取出对应的验证码
        String redisCode = (String) redisTemplate.opsForValue().get(member.getPhoneNumber());
        //如果验证码过期
        if(redisCode == null){
            return ServerResponse.error(ResponseEnum.VERIFY_CODE_IS_EXPIRED);
        }

        //判断redis中取出的验证码和用户输入的验证码是否一致
        if(!redisCode.equals(code)){
            return ServerResponse.error(ResponseEnum.VERIFY_CODE_IS_ERROR);
        }

        //用户名唯一性校验
        QueryWrapper<Member> usernameQueryWrapper = new QueryWrapper<>();
        usernameQueryWrapper.eq("username",member.getUsername());
        Member memberByUsername = memberMapper.selectOne(usernameQueryWrapper);
        if(memberByUsername != null){
            return ServerResponse.error(ResponseEnum.MEMBER_USERNAME_IS_EXISTED);
        }

        //手机号唯一性校验
        QueryWrapper<Member> phoneNumberQueryWrapper = new QueryWrapper<>();
        phoneNumberQueryWrapper.eq("phone_number",member.getPhoneNumber());
        Member memberByPhoneNumber = memberMapper.selectOne(phoneNumberQueryWrapper);
        if(memberByPhoneNumber != null){
            return ServerResponse.error(ResponseEnum.MEMBER_PHONE_NUMBER_IS_USED);
        }

        memberMapper.addMember(member);
        return ServerResponse.success();
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    public List<Area> queryAreaByPid(Integer pid) {
        return memberMapper.queryAreaByPid(pid);
    }

    @Override
    public ServerResponse login(String loginname, String password) {
        //登录名非空校验
        if(StringUtils.isBlank(loginname)){
            return ServerResponse.error(ResponseEnum.MEMBER_LOGINNAME_IS_NULL);
        }

        //密码非空校验
        if(StringUtils.isBlank(password)){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_IS_NULL);
        }

        //通过登录名去查找用户名等于登录名的用户信息
        Member member = memberMapper.getMemberByLoginname(loginname);
        if(member == null){
            return ServerResponse.error(ResponseEnum.MEMBER_NOT_EXISTED);
        }

        //验证用户在登录页面输入的密码是否和数据库中存储的密码一致
        if(!member.getPassword().equals(password)){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_ERROR);
        }
        //生成TOKEN
        Member loginMember = new Member();
        loginMember.setUuid(UUID.randomUUID().toString());
        loginMember.setId(member.getId());
        loginMember.setUsername(member.getUsername());
        String token = JwtUtil.createToken(loginMember);
        /*//将会员对象转为JSON格式的字符串
        String loginMemberJson = JSON.toJSONString(loginMember);
        //将会员对象转为JSON格式的字符串进行Base64编码
        String base64LoginMemberJson = Base64.getEncoder().encodeToString(loginMemberJson.getBytes());
        //对进行Base64编码后(会员对象转为JSON格式的字符串)进行加密,也就是进行签名，签名的作用是为了防止客户端篡改TOKEN信息。
        //在服务器端定义一个密钥，这个密钥只有服务器端知道
        String secretKey = "dKskJsd23#3$%!~CS32*";
        String sign = MD5Util.md5Hex(base64LoginMemberJson + secretKey);
        //将签名字符串进行Base64编码
        String base64Sign = Base64.getEncoder().encodeToString(sign.getBytes());
        //生成token
        String token = base64LoginMemberJson + "." + base64Sign;
        System.out.println(token);*/
        //将用户的登录状态存放到redis中
        redisTemplate.opsForValue().set("member:" + loginMember.getUuid(),232324,30, TimeUnit.MINUTES);

        return ServerResponse.success(token);
}



    @Override
    public Member getMemberById(Integer id) {
        return memberMapper.getMemberById(id);
    }
}
