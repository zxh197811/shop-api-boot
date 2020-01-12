package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Area;
import com.fh.model.Member;

import java.util.List;

public interface MemberMapper extends BaseMapper<Member> {
    void addMember(Member member);

    Member getMemberByLoginname(String loginname);

    List<Area> queryAreaByPid(Integer pid);

    void updateMember(Member member);

    Member getMemberById(Integer id);
}
