package com.bigdata.hadoop.service;

import com.bigdata.hadoop.dto.EmployeeDTO;
import com.bigdata.hadoop.dto.SaveEmployeeDTO;
import com.bigdata.hadoop.model.Department;
import com.bigdata.hadoop.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
    void saveEmployee(SaveEmployeeDTO employeeDTO);
    List<Department> findAllDepartment();
    Employee getEmployeeByEmpNo(Long empNo);
    void deleteEmployee(Long empNo);

    Employee findEmployeeByEmpNo(Long empNo);

    void updateEmployee(SaveEmployeeDTO employeeDTO);
}
