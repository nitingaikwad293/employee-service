package com.nitin.employeeservice.service;

import com.nitin.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
	
	public EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(Long id);
	
}
