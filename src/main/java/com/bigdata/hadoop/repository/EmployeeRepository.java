package com.bigdata.hadoop.repository;

import com.bigdata.hadoop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByEmpNo(Long empNo);
    void deleteEmployeeByEmpNo(Long empNo);
}
