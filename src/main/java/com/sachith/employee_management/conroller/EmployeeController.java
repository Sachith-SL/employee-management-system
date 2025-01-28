package com.sachith.employee_management.conroller;

import com.sachith.employee_management.model.Employee;
import com.sachith.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public List<Employee> readAll() {
        return employeeService.readAll();
    }

    @PostMapping()
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }
}
