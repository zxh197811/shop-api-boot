package com.fh.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


public class AliyunSmsUtil {

    /***
     * 发送短信验证码
     * @param phoneNumber
     * @param code
     * @return
     */
    public static boolean sendSms(String phoneNumber,String code){
        boolean success = false;
        try {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FvzJ6iTTp43KL36DkEz", "xMrvt4sotdRdmszvJlRlIZuMuR9Z8i");
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phoneNumber);
            request.putQueryParameter("SignName", "飞狐商城");
            request.putQueryParameter("TemplateCode", "SMS_180755064");
            request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String responseCode = (String) jsonObject.get("Code");
            if(responseCode.equals("OK")){
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

}
