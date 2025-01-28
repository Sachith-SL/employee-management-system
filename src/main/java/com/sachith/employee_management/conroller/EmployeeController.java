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
    public ResponseEntity<EmployeePayload> create(@RequestBody EmployeePayload payload) {
        return ResponseEntity.ok(employeeService.create(payload));
    }

    @GetMapping()
    public ResponseEntity<EmployeePayload> readAll() {
        return ResponseEntity.ok(employeeService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeePayload> readById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.readById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeePayload> updateById(@PathVariable Integer id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateById(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeePayload> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }
}
