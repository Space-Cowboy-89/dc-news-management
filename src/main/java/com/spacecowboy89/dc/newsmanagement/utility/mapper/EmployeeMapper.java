package com.spacecowboy89.dc.newsmanagement.utility.mapper;


import com.spacecowboy89.dc.newsmanagement.dto.EmployeeDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto toEmployeeDto (Employee employee);
}
