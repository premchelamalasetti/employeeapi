package com.example.api.apidemo2.integration;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.example.api.apidemo2.model.Employee;
import com.example.api.apidemo2.repository.EmployeeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	private String baseUrl="http://localhost";
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static RestTemplate restTemplate;
	
	private Employee employee;
	private Employee employee1;
	
	@BeforeAll
	public void init()
	{
		restTemplate =new RestTemplate();
	}
	
	@BeforeEach
	public void beforeSetUp()
	{
		baseUrl =baseUrl+":"+port+"/employee";
	    employee=new Employee();
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
	@AfterAll
	public void delete()
	{
		employeeRepository.deleteAll();
	}
	@Test
	void createEmployee()
	{
		Employee employee=new Employee();
		employee= new Employee();
		employee.setEmployeeName("Prem");
		employee.setEmployeeJob("Trainee");
		employee.setEmployeeAdress("Hyd");
		employeeRepository.save(employee);
		
		Employee employee1= new Employee();
		employee1.setEmployeeName("Prem");
		employee1.setEmployeeJob("Trainee");
		employee1.setEmployeeAdress("Hyd");
		employeeRepository.save(employee1);	
		Employee newEmployee=restTemplate.postForObject(baseUrl+"/createEmployee",employee,Employee.class);
//		assertNotNull(employee);
		assertThat(newEmployee.getId()).isNotNull();
		
	}
}
