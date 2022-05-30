package com.asianaidt.springrestful.step01.service;

import com.asianaidt.springrestful.step01.entity.Member;

import java.util.List;

public interface MemberService {
    Member add(final Member member);
    Member get(final Long id);
    Member update(final Member member);
    boolean delete(final Long id);
    Member findByUsername(final String username);
    List<Member> findByAll();
}
