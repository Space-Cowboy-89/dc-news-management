package com.spacecowboy89.dc.newsmanagement.persistence.repository;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = """
            select *
            from employee e
            join news n on e.id = n.employee_id
            where n.newsCode =:newsCode
            """,nativeQuery = true)
    public void findEmplByNewsCode(String newsCode);
}
