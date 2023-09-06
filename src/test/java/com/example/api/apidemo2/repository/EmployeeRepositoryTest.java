package com.example.api.apidemo2.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.api.apidemo2.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	private Employee employee2;
	@BeforeEach
	void init()
	{
		employee=new Employee();
		employee.setId(1L);
		employee.setEmployeeName("Prem");
		employee.setEmployeeJob("Trainee");
		employee.setEmployeeAdress("MTM");
		employeeRepository.save(employee);	
		
		employee2=new Employee();
		employee2.setId(2L);
		employee2.setEmployeeName("Kumar");
		employee2.setEmployeeJob("Associate");
		employee2.setEmployeeAdress("Hyd");
		employeeRepository.save(employee2);	
	}
	
	@Test
	void createEmployee()
	{
		Employee newEmployee=employeeRepository.save(employee);
		assertThat(newEmployee);
		assertThat(newEmployee.getId()).isNotNull();
	}
	
	@Test
	void returnAllEmployees()
	{
		List<Employee> list=employeeRepository.findAll();
		assertThat(list.size()).isEqualTo(2L);
	}
	@Test
	void getEmployeeById()
	{
		Employee existingEmployee=employeeRepository.findById(employee.getId()).get();
		assertNotNull(existingEmployee);
		assertThat(existingEmployee.getEmployeeAdress()).isEqualTo("MTM");
	}
	@Test
	void updateEmployeeByID()
	{
		employee.setEmployeeJob("Associate Engineer");
		Employee updatedEmployee=employeeRepository.save(employee);
		assertThat(employee.getEmployeeAdress()).isEqualTo(updatedEmployee.getEmployeeAdress());
	}
	@Test
	void deleteEmployeeById()
	{
		employeeRepository.deleteById(1L);
		List<Employee> list = employeeRepository.findAll();
		assertThat(list.size()).isEqualTo(1);
	}
}
