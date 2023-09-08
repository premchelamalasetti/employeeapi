package com.example.api.apidemo2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.apidemo2.model.Employee;
import com.example.api.apidemo2.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping(value = "/createEmployee")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Employee createEmployee(Employee employee)
	{
		return employeeService.create(employee);
	}
	@GetMapping(value="/allEmployee")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployee();
	}
	@GetMapping(value="/employeeById/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id)
	{
		return employeeService.getEmployeeById(id);
	}
	@PutMapping(value="/updateEmployee/{id}")
	public Employee updateEmployeeById(@RequestBody Employee employee)
	{
		return employeeService.updateEmployeeById(employee);
	}
	@DeleteMapping(value = "/deleteEmployee/{id}")
	public String deleteEmployeeById(@PathVariable Long id)
	{
		employeeService.deleteEmployeeById(id);
		return "Employee Deleted Successfully";
	}
}
