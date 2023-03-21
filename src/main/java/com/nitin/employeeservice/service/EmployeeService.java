package com.nitin.employeeservice.service;

import com.nitin.employeeservice.dto.ApiResponseDto;
import com.nitin.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
	
	public EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	ApiResponseDto getEmployeeById(Long id);
	
}
