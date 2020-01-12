package com.fh.service;

import com.fh.model.PayLog;
import com.fh.model.ServerResponse;

public interface PayLogService {
    void addPayLog(PayLog payLog);

    ServerResponse getPayMoney(String outTradeNo);

    void updatePayLog(PayLog payLog);
}
