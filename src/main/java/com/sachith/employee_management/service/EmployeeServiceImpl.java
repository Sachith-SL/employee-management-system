package com.sachith.employee_management.service;

import com.sachith.employee_management.model.Employee;
import com.sachith.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> readAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }
}
