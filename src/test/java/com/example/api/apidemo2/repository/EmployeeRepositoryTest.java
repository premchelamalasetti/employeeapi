package com.example.api.apidemo2.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.api.apidemo2.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	private Employee employee1;
	
	@BeforeEach
	void init()
	{
		employee= new Employee();
		employee.setEmployeeName("Prem");
		employee.setEmployeeJob("Trainee");
		employee.setEmployeeAdress("Hyd");
		employeeRepository.save(employee);
		
		employee1= new Employee();
		employee1.setEmployeeName("Prem");
		employee1.setEmployeeJob("Trainee");
		employee1.setEmployeeAdress("Hyd");
		employeeRepository.save(employee1);
	}

	@Test
	void save()
	{
		Employee newEmployee=employeeRepository.save(employee);
		assertThat(employee);
		assertThat(newEmployee.getId()).isNotEqualTo(null);
	}
	@Test
	void allEmployee()
	{
		List<Employee> list=employeeRepository.findAll();
		assertNotNull(list);
		assertThat(list.size()).isEqualTo(2);
	}
	@Test
	void updateEmployee()
	{
		Employee newEmployee=employeeRepository.findById(employee.getId()).get();
		employee.setEmployeeJob("AssociateEngineer");
		Employee updatedEmployee=employeeRepository.save(newEmployee);
		assertThat(employee.getEmployeeJob()).isEqualTo("AssociateEngineer");
		
	}
	@Test
	void deleteEmployee()
	{
		Long id=employee.getId();
		employeeRepository.delete(employee);
		Optional<Employee> emp=employeeRepository.findById(id);
		List<Employee> list=employeeRepository.findAll();
		assertThat(list.size()).isEqualTo(1);
	}
	@Test
	void getEmployeeById()
	{
		Employee existingEmployee=employeeRepository.findById(employee.getId()).get();
		assertNotNull(existingEmployee);
		assertEquals("Prem",existingEmployee.getEmployeeName());
	}
}
