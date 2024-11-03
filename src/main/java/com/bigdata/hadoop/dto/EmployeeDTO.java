package com.bigdata.hadoop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private Long empNo;
    private String empName;
    private String job;
    private Long mgr;
    private LocalDate hireDate;
    private Long salary;
    private Long comm;
    private String img;
    private DepartmentDTO departmentDTO;

}
