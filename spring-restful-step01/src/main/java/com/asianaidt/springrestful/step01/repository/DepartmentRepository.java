package com.asianaidt.springrestful.step01.repository;

import com.asianaidt.springrestful.step01.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
