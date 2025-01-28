package com.sachith.employee_management.conroller;

import com.sachith.employee_management.dto.EmployeePayload;
import com.sachith.employee_management.model.Employee;
import com.sachith.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping()
    public ResponseEntity<EmployeePayload> readAll() {
        return ResponseEntity.ok(employeeService.readAll());
    }

    @GetMapping("/{id}")
    public Employee readById(@PathVariable Integer id) {
        return employeeService.readById(id);
    }

    @PutMapping("/{id}")
    public Employee updateById(@PathVariable Integer id, @RequestBody Employee user) {
        return employeeService.updateById(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Integer id) {
        employeeService.deleteById(id);
    }
}
