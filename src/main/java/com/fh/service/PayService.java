package com.fh.service;

import com.fh.model.ServerResponse;

public interface PayService {
    ServerResponse createNative(Integer id);

    ServerResponse queryPayStatus(Integer id);
}
