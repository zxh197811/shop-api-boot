package com.fh.service.impl;

import com.fh.mapper.PayLogMapper;
import com.fh.model.PayLog;
import com.fh.model.ResponseEnum;
import com.fh.model.ServerResponse;
import com.fh.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayLogServiceImpl implements PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public void addPayLog(PayLog payLog) {
        payLogMapper.addPayLog(payLog);
    }

    @Override
    public ServerResponse getPayMoney(String outTradeNo) {
        PayLog payLog = payLogMapper.getPayLogByOutTradeNo(outTradeNo);
        if(payLog == null){
            return ServerResponse.error(ResponseEnum.OUT_TRADE_NO_INVALID);
        }
        if(payLog.getPayStatus() == 1){
            return ServerResponse.error();
        }

        return ServerResponse.success(payLog.getPayMoney());
    }

    @Override
    public void updatePayLog(PayLog payLog) {
        payLogMapper.updatePayLog(payLog);
    }
}
