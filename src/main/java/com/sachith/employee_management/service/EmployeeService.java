package com.sachith.employee_management.service;

import com.sachith.employee_management.dto.EmployeePayload;
import com.sachith.employee_management.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeePayload readAll();


    EmployeePayload create(EmployeePayload payload);

    EmployeePayload readById(Integer id);

    EmployeePayload updateById(Integer id, Employee employee);

    EmployeePayload deleteById(Integer id);
}
