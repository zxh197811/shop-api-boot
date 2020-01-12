package com.fh.controller;


import com.fh.model.ServerResponse;
import com.fh.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payLog")
public class PayLogController {

        @Autowired
        private PayLogService payLogService;

    @RequestMapping("getPayMoney")
    public ServerResponse getPayMoney(String outTradeNo){
        try {
            ServerResponse serverResponse = payLogService.getPayMoney(outTradeNo);
            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }


}
