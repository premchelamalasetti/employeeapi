package com.example.api.apidemo2.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.api.apidemo2.model.Employee;
import com.example.api.apidemo2.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest 
{
	@InjectMocks
	private EmployeeService employeeService;
	@Mock
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
	void saveEmployee()
	{
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		Employee newEmployee=employeeService.create(employee);
		assertNotNull(employee);
		assertThat(newEmployee.getEmployeeName()).isEqualTo("Prem");
	}
	@Test
	void getEmployee()
	{
		List<Employee> list=employeeRepository.findAll();
		list.add(employee);
		list.add(employee1);
		when(employeeRepository.findAll()).thenReturn(list);
		List<Employee> employess=employeeService.getAllEmployee();
		assertNotNull(employess);
		assertThat(employess.size()).isEqualTo(2);
	}
	@Test
	void updateEmployee()
	{
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		employee.setEmployeeName("Kumar");
		Employee newEmployee=employeeService.updateEmployeeById(employee);
		assertEquals("Kumar",newEmployee.getEmployeeName());
	}
	@Test
	void deleteEmployee()
	{
		employee.setId(1L);
		employeeService.deleteEmployeeById(1L);
		verify(employeeRepository,times(1)).deleteById(1L);
		assertNotNull(employee);
	}
	@Test
	void getEmployeeById()
	{
		employee.setId(1L);
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
		Optional<Employee> exisitngEmployee=employeeService.getEmployeeById(1L);
		assertNotNull(exisitngEmployee);
		assertThat(exisitngEmployee.get().getId()).isEqualTo(1L);
	}
}
