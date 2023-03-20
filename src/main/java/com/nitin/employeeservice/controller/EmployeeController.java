package com.nitin.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.employeeservice.dto.EmployeeDto;
import com.nitin.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {
	
	EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmpployee(@RequestBody EmployeeDto employeeDto) {
		
	EmployeeDto dto	= employeeService.saveEmployee(employeeDto);
	
	return new ResponseEntity<>(dto, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id")  Long employeeId){
		
		EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
		
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
		
	}
	
	

}
