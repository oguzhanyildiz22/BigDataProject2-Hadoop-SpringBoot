package com.bigdata.hadoop.service.impl;

import com.bigdata.hadoop.dto.DepartmentDTO;
import com.bigdata.hadoop.dto.EmployeeDTO;
import com.bigdata.hadoop.dto.SaveEmployeeDTO;
import com.bigdata.hadoop.model.Department;
import com.bigdata.hadoop.model.Employee;
import com.bigdata.hadoop.repository.DepartmentRepository;
import com.bigdata.hadoop.repository.EmployeeRepository;
import com.bigdata.hadoop.service.EmployeeService;
import com.bigdata.hadoop.service.ImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
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

    @Override
    public void saveEmployee(SaveEmployeeDTO employeeDTO) {

        MultipartFile file = employeeDTO.getImg();
        String imageName = file.getOriginalFilename();

        imageService.saveImageToHadoop(file, imageName);

        Employee employee = modelMapper.map(employeeDTO,Employee.class);

        Department department = departmentRepository.findDepartmentByDeptName(employeeDTO.getDepartmentDTO().getDeptName())
                .orElseThrow(() -> new EntityNotFoundException("department"));

        employee.setImg(imageName);
        employee.setDepartment(department);
        employee.setHireDate(employeeDTO.getHireDate().atStartOfDay());

        employeeRepository.save(employee);
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Employee getEmployeeByEmpNo(Long empNo) {
        return employeeRepository.findEmployeeByEmpNo(empNo)
                .orElseThrow(() -> new EntityNotFoundException("employee"));
    }

    @Transactional
    @Override
    public void deleteEmployee(Long empNo) {
        employeeRepository.deleteEmployeeByEmpNo(empNo);
    }

    @Override
    public Employee findEmployeeByEmpNo(Long empNo) {
        return employeeRepository.findEmployeeByEmpNo(empNo)
                .orElseThrow(() -> new EntityNotFoundException("employee"));
    }

    @Override
    public void updateEmployee(SaveEmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findEmployeeByEmpNo(employeeDTO.getEmpNo())
                .orElseThrow(() -> new EntityNotFoundException("employee"));

        employee.setEmpName(employee.getEmpName());

    }
}
