package com.bigdata.hadoop.controller;

import com.bigdata.hadoop.dto.EmployeeDTO;
import com.bigdata.hadoop.dto.SaveEmployeeDTO;
import com.bigdata.hadoop.model.Department;
import com.bigdata.hadoop.model.Employee;
import com.bigdata.hadoop.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public String home(Model model) {
        List<EmployeeDTO> employeeDTOS = employeeService.getEmployees();
        model.addAttribute("employeeDTO",employeeDTOS);
        return "home";
    }

    @GetMapping("/add-employee-form")
    public String showAddEmployeeForm(Model model){
        List<Department> departments = employeeService.findAllDepartment();
        model.addAttribute("employeeDTO",new SaveEmployeeDTO());
        model.addAttribute("departments",departments);
        return "add";
    }

    @PostMapping("/save-employee")
    public String saveEmployeeForm(@ModelAttribute SaveEmployeeDTO employeeDTO){
        employeeService.saveEmployee(employeeDTO);
        return "redirect:/employees";
    }

    @GetMapping("/delete-employee-confirmation/{empNo}")
    public String showDeleteConfirmation(@PathVariable("empNo") Long empNo, Model model) {
        model.addAttribute("empNo", empNo);
        return "delete";
    }

    @PostMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("empNo") Long empNo) {
        employeeService.deleteEmployee(empNo);
        return "redirect:/employees";
    }

    @GetMapping("/update-form/{empNo}")
    public String showUpdateForm(Model model,@PathVariable("empNo") Long empNo){
        Employee employee = employeeService.findEmployeeByEmpNo(empNo);
        model.addAttribute("employee", employee);
        return "update";
    }

    @PostMapping("/update-employee")
    public String updateEmployeeForm(@ModelAttribute SaveEmployeeDTO employeeDTO){
        employeeService.updateEmployee(employeeDTO);
        return "redirect:/employees";
    }


}
