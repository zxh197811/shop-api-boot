package com.fh.mapper;

import com.fh.model.PayLog;

public interface PayLogMapper {
    void addPayLog(PayLog payLog);

    void updatePayLog(PayLog payLog);

    PayLog getPayLogByOutTradeNo(String outTradeNo);
}
