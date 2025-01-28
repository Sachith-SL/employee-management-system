package com.sachith.employee_management.service;

import com.sachith.employee_management.dto.EmployeePayload;
import com.sachith.employee_management.model.Employee;
import com.sachith.employee_management.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeePayload readAll() {
        EmployeePayload payload = new EmployeePayload();
        try {
            List<Employee> result = employeeRepository.findAll();
            if(!result.isEmpty()){
                payload.setEmployeeList(result);
                payload.setStatusCode(200);
                payload.setMessage("Successful");
            } else {
                payload.setStatusCode(404);
                payload.setMessage("No employees found");
            }


        } catch (Exception exception) {
            logger.error("", exception);
            payload.setStatusCode(500);
            payload.setError(exception.getMessage());
        }

        return payload;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee readById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                Employee oldEmployee = optionalEmployee.get();
                oldEmployee.setName(employee.getName());
                oldEmployee.setDepartment(employee.getDepartment());
                oldEmployee.setSalary(employee.getSalary());
                return employeeRepository.save(oldEmployee);
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                employeeRepository.deleteById(id);
            }
        } catch (Exception e) {

        }

    }
}
