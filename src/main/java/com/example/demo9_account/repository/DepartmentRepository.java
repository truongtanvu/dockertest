package com.example.demo9_account.repository;

import com.example.demo9_account.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByDepartment(String department);
}
