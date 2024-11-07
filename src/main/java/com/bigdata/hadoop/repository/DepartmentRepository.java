package com.bigdata.hadoop.repository;

import com.bigdata.hadoop.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByDeptName(String depName);

}
