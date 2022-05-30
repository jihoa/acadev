package com.asianaidt.springrestful.step01.repository;

import com.asianaidt.springrestful.step01.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(final String username);
    Optional<Member> findByEmail(final String email);

    @Query("select m from Member m where m.username = ?1")
    Member findByCustom(String username);

}
