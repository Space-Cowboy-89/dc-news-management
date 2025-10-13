package com.spacecowboy89.dc.newsmanagement.persistence.dao;


import com.spacecowboy89.dc.newsmanagement.persistence.entity.Employee;
import com.spacecowboy89.dc.newsmanagement.persistence.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeDao {
    private final EmployeeRepository employeeRepo;

    public EmployeeDao(EmployeeRepository employeeRepo) {
        this.employeeRepo=employeeRepo;
    }


    public Optional<Employee> findByEmployeeCode(String employeeCode){
        return employeeRepo.findByEmployeeCode(employeeCode);
    }
}
