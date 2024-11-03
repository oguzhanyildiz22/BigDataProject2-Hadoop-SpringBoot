package com.bigdata.hadoop.repository;

import com.bigdata.hadoop.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
