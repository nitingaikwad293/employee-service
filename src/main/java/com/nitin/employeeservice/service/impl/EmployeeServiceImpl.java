package com.nitin.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nitin.employeeservice.dto.EmployeeDto;
import com.nitin.employeeservice.entiry.Employee;
import com.nitin.employeeservice.exception.ResourceNotFoundException;
import com.nitin.employeeservice.repository.EmployeeRepository;
import com.nitin.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	ModelMapper mapper;
	
	EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		

		Employee employee = mapper.map(employeeDto, Employee.class);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto dto = mapper.map(savedEmployee, EmployeeDto.class);
		
		return dto;
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		

		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Empoyee not found"));
		
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		
		return employeeDto;
	}

}
