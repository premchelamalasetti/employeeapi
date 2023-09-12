package com.example.api.apidemo2.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
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
	public static void init()
	{
		restTemplate =new RestTemplate();
	}
	
	@BeforeEach
	public void beforeSetUp()
	{
		baseUrl =baseUrl+":"+port+"/employee";
	    employee=new Employee();
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
	@AfterEach
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
	@Test
	void getEmployee()
	{
		List<Employee> list = restTemplate.getForObject(baseUrl+"/allEmployee", List.class);
		//assertEquals(list.size(), 2);
		assertThat(list.size()).isEqualTo(2);
	}	
	@Test
	void getEmployeeById()
	{
		Employee existingEmployee=restTemplate.getForObject(baseUrl+"/employeeById/"+employee.getId(), Employee.class);
		assertNotNull(existingEmployee);
		assertEquals("Prem", existingEmployee.getEmployeeName());
	}
	@Test
	void updateEmployeeById()
	{
		employee.setEmployeeAdress("Banglore");
		restTemplate.put(baseUrl+"/updateEmployee/{id}",employee,employee.getId());
		Employee updatedEmployee=restTemplate.getForObject(baseUrl+"/employeeById/"+employee.getId(), Employee.class);
		assertThat(employee.getEmployeeAdress()).isEqualTo("Banglore");
	}
	@Test
	void deleteEmployeeById()
	{
		restTemplate.delete(baseUrl+"/deleteEmployee/"+employee.getId(),Employee.class);
		int count=employeeRepository.findAll().size();
		assertThat(1).isEqualTo(count);
	}
}