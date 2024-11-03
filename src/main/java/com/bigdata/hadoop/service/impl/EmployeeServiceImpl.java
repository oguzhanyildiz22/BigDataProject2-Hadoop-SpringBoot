package com.bigdata.hadoop.service.impl;

import com.bigdata.hadoop.dto.DepartmentDTO;
import com.bigdata.hadoop.dto.EmployeeDTO;
import com.bigdata.hadoop.model.Employee;
import com.bigdata.hadoop.repository.EmployeeRepository;
import com.bigdata.hadoop.service.EmployeeService;
import com.bigdata.hadoop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final ImageService imageService;

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map(e -> {
                    byte[] image = imageService.getImage(e.getImg());
                    EmployeeDTO data = modelMapper.map(e, EmployeeDTO.class);
                    data.setHireDate((e.getHireDate() != null) ? e.getHireDate().toLocalDate() : null);
                    data.setImg(Base64.getEncoder().encodeToString(image));
                    DepartmentDTO departmentDTO = new DepartmentDTO();
                    departmentDTO.setDeptName(e.getDepartment().getDeptName());
                    departmentDTO.setDeptNo(e.getDepartment().getDeptNo());
                    departmentDTO.setLocation(e.getDepartment().getLocation());

                    data.setDepartmentDTO(departmentDTO);
                    return data;
                }).toList();
    }
}
