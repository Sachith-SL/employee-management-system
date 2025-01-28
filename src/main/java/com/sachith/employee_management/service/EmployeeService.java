package com.sachith.employee_management.service;

import com.sachith.employee_management.dto.EmployeePayload;
import com.sachith.employee_management.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeePayload readAll();


    Employee create(Employee employee);

    Employee readById(Integer id);

    Employee updateById(Integer id, Employee employee);

    void deleteById(Integer id);
}
