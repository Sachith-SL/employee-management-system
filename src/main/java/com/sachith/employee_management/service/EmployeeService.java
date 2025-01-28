package com.sachith.employee_management.service;

import com.sachith.employee_management.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> readAll();


    Employee create(Employee employee);
}
