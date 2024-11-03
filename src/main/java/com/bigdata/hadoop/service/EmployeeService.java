package com.bigdata.hadoop.service;

import com.bigdata.hadoop.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
}
