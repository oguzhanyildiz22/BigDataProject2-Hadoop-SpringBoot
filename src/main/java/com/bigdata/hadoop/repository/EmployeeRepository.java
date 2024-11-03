package com.bigdata.hadoop.repository;

import com.bigdata.hadoop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
