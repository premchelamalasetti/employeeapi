package com.example.api.apidemo2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.api.apidemo2.model.Employee;
import com.example.api.apidemo2.repository.EmployeeRepository;

@Service
public class EmployeeService {


	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployee()
	{
		return employeeRepository.findAll();
	}
	public Optional<Employee> getEmployeeById(Long id)
	{
		return employeeRepository.findById(id);
	}
	public Employee updateEmployeeById(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployeeById(Long id)
	{
		employeeRepository.deleteById(id);
	}
}
