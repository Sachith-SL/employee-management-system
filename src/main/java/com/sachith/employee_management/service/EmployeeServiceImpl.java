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
            if (!result.isEmpty()) {
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
    public EmployeePayload create(EmployeePayload newPayload) {

        EmployeePayload payload = new EmployeePayload();

        try {
            Employee employee = new Employee();
            employee.setName(newPayload.getName());
            employee.setSalary(newPayload.getSalary());
            employee.setDepartment(newPayload.getDepartment());

            Employee result = employeeRepository.save(employee);
            if (result.getId() > 0) {
                payload.setEmployee(result);
                payload.setStatusCode(200);
                payload.setMessage("Employee created successfully");
            }

        } catch (Exception exception) {
            logger.error("", exception);
            payload.setStatusCode(500);
            payload.setError(exception.getMessage());
        }

        return payload;
    }

    @Override
    public EmployeePayload readById(Integer id) {

        EmployeePayload payload = new EmployeePayload();

        try {
            Employee result = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
            payload.setEmployee(result);
            payload.setStatusCode(200);
            payload.setMessage("Employee with id " + id + " found successfully");
        } catch (Exception exception) {
            logger.error("", exception);
            payload.setStatusCode(500);
            payload.setError(exception.getMessage());
        }

        return payload;
    }

    @Override
    public EmployeePayload updateById(Integer id, Employee employee) {
        EmployeePayload payload = new EmployeePayload();
        try {
            Employee oldEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

            oldEmployee.setName(employee.getName());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setSalary(employee.getSalary());
            payload.setEmployee(employeeRepository.save(oldEmployee));
            payload.setStatusCode(200);
            payload.setMessage("Employee with id " + id + " updated successfully");

        } catch (Exception exception) {
            logger.error("", exception);
            payload.setStatusCode(500);
            payload.setError(exception.getMessage());
        }
        return payload;
    }

    @Override
    public EmployeePayload deleteById(Integer id) {
        EmployeePayload payload = new EmployeePayload();
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            if (employeeOptional.isPresent()) {
                employeeRepository.deleteById(id);
                payload.setStatusCode(200);
                payload.setMessage("Employee deleted successfully");
            } else {
                payload.setStatusCode(404);
                payload.setMessage("Employee not found for deletion");
            }
        } catch (Exception e) {
            payload.setStatusCode(500);
            payload.setError("Error occurred while deleting employee: " + payload.getMessage());
        }
        return payload;
    }
}
