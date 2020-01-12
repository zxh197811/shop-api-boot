package com.fh.model;

public enum ResponseEnum {
    OK(200,"操作成功"),
    ERROR(500,"操作失败"),
    PHONE_NUMBER_IS_NULL(505,"手机号为空"),
    MEMBER_INFO_IS_NULL(506,"会员信息为空"),
    MEMBER_USERNAME_IS_NULL(507,"会员用户名为空"),
    MEMBER_PASSWORD_IS_NULL(508,"会员密码为空"),
    MEMBER_PHONE_NUMBER_IS_NULL(509,"会员手机号为空"),
    MEMBER_PHONE_NUMBER_IS_ILLEGAL(510,"会员手机号不合法"),
    VERIFY_CODE_IS_NULL(511,"验证码为空"),
    VERIFY_CODE_IS_EXPIRED(512,"验证码无效"),
    VERIFY_CODE_IS_ERROR(513,"验证码错误"),
    MEMBER_USERNAME_IS_EXISTED(514,"用户名已存在"),
    MEMBER_PHONE_NUMBER_IS_USED(515,"手机号已注册"),
    MEMBER_LOGINNAME_IS_NULL(516,"会员登录名为空"),
    MEMBER_NOT_EXISTED(517,"会员不存在"),
    MEMBER_PASSWORD_ERROR(518,"密码错误"),
    TOKEN_IS_NULL(519,"请求头中token信息为空"),
    TOKEN_IS_SHORT_OF(520,"请求头中token信息不完整"),
    TOKEN_IS_CHANGED(521,"请求头中token信息被篡改"),
    TOKEN_IS_EXPIRED(522,"登录信息已过期"),
    TOKEN_VERIFY_ERROR(523,"验证token失败"),
    PRODUCT_ID_IS_NULL(524,"商品id为空"),
    PRODUCT_IS_SOLD_OUT(525,"商品已下架"),
    CART_IS_NOT_EXISTED(526,"购物车不存在"),
    CART_PRODUCT_IS_NOT_EXISTED(527,"购物车不存在该商品"),
    CART_ALL_CHECKED_PRODUCT_UNDER_STOCK(528,"购物车中所有选中商品都库存不足"),
    ORDER_ALREADY_CREATED(529,"该订单已创建"),
    REPETITIVE_OPERATION(530,"重复操作"),
    PAY_LOG_IS_NULL(531,"当前会员没有待支付订单"),
    OUT_TRADE_NO_INVALID(532,"无效的订单号！"),
    PWD_ERROR(403,"密码"),
    ;

    private int code;

    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
