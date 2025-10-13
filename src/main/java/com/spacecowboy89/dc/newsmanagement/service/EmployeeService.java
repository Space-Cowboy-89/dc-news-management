package com.spacecowboy89.dc.newsmanagement.service;

import com.spacecowboy89.dc.newsmanagement.dto.EmployeeDto;
import com.spacecowboy89.dc.newsmanagement.exception.NoResFoundInDBException;
import com.spacecowboy89.dc.newsmanagement.persistence.dao.EmployeeDao;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeDao employeeDao;


    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee retrieveByEmployeeCode(String employeeCode){
        Employee employee = employeeDao.findByEmployeeCode(employeeCode)
                .orElseThrow(NoResFoundInDBException::new);
        return employee;
    }
}
