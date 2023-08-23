package com.example.api.apidemo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.apidemo2.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
