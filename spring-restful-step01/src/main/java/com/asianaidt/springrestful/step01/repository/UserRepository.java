package com.asianaidt.springrestful.step01.repository;

import com.asianaidt.springrestful.step01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
