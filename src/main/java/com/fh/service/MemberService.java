package com.fh.service;

import com.fh.model.Area;
import com.fh.model.Member;
import com.fh.model.ServerResponse;

import java.util.List;

public interface MemberService {

    ServerResponse addMember(Member member, String code);

    void updateMember(Member member);

    List<Area> queryAreaByPid(Integer pid);

    ServerResponse login(String loginname, String password);

    Member getMemberById(Integer id);
}
