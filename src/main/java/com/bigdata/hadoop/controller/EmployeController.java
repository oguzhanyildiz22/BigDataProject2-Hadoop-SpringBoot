package com.bigdata.hadoop.controller;

import com.bigdata.hadoop.dto.EmployeeDTO;
import com.bigdata.hadoop.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public String home(Model model) {
        List<EmployeeDTO> employeeDTOS = employeeService.getEmployees();
        model.addAttribute("employeeDTO",employeeDTOS);
        return "home";
    }
}
