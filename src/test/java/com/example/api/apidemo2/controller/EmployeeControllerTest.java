package com.example.api.apidemo2.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.api.apidemo2.model.Employee;
import com.example.api.apidemo2.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class EmployeeControllerTest 
{
	@MockBean
	private EmployeeService employeeService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	
	private Employee emp1;
	private Employee emp2;
	
	
	@BeforeEach
	void init()
	{
		emp1=new Employee();
		emp1.setId(1L);
		emp1.setEmployeeName("Prem");
		emp1.setEmployeeJob("Trainee");
		emp1.setEmployeeAdress("MTM");
		employeeService.create(emp1);
		
		emp2=new Employee();
		emp2.setId(2L);
		emp2.setEmployeeName("krishna");
		emp2.setEmployeeJob("Senior Engineer");
		emp2.setEmployeeAdress("HYD");
		employeeService.create(emp2);
	}
	
	@Test
	void createEmployee() throws Exception
	{
		when(employeeService.create(any(Employee.class))).thenReturn(emp1);
		ResultActions resultMathcer=mockMvc.perform(post("/employee/createEmployee").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(emp1)));
		resultMathcer.andDo(print()).andExpect(status().isCreated())
		.andExpect(jsonPath("$.employeeName",is(emp1.getEmployeeName())))
		.andExpect(jsonPath("$.employeeJob",is(emp1.getEmployeeJob())))
		.andExpect(jsonPath("$.employeeAdress",is(emp1.getEmployeeAdress())));
	}
	@Test
	//@RepeatedTest(2)
	void getEmployee() throws Exception
	{
		List<Employee> list=new ArrayList();
		list.add(emp1);
		list.add(emp2);
		when(employeeService.getAllEmployee()).thenReturn(list);
		this.mockMvc.perform(get("/employee/allEmployee").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.size()",is(list.size())));
	}
	@Test
	void getEmployeeById() throws Exception
	{
		when(employeeService.getEmployeeById(anyLong())).thenReturn(Optional.of(emp1));
		this.mockMvc.perform(get("/employee/employeeById/{id}",1L).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.employeeName",is(emp1.getEmployeeName())))
		.andExpect(jsonPath("$.employeeJob",is(emp1.getEmployeeJob())))
		.andExpect(jsonPath("$.employeeAdress",is(emp1.getEmployeeAdress())));
	}
	@Test
	void updateEmployeeById() throws Exception
	{
		emp1.setEmployeeAdress("VJG");
		when(employeeService.updateEmployeeById(any(Employee.class))).thenReturn(emp1);
		this.mockMvc.perform(put("/employee/updateEmployee/{id}",1L).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(emp1)))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.employeeName", is(emp1.getEmployeeName())))
		.andExpect(jsonPath("$.employeeJob",is(emp1.getEmployeeJob())))
		.andExpect(jsonPath("$.employeeAdress",is(emp1.getEmployeeAdress())));
	}
	@Test
	void deleteEmployeeById() throws Exception
	{
		doNothing().when(employeeService).deleteEmployeeById(anyLong());
		this.mockMvc.perform(delete("/employee/deleteEmployee/{id}",1L))
		.andDo(print()).andExpect(status().isOk());
	}
}
