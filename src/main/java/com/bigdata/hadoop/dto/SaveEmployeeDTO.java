package com.bigdata.hadoop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveEmployeeDTO {
    private Long id;
    private Long empNo;
    private String empName;
    private String job;
    private Long mgr;
    private LocalDate hireDate;
    private Long salary;
    private Long comm;
    private MultipartFile img;
    private DepartmentDTO departmentDTO;
}
