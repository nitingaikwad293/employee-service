package com.nitin.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nitin.employeeservice.dto.ApiResponseDto;
import com.nitin.employeeservice.dto.DepartmentDto;
import com.nitin.employeeservice.dto.EmployeeDto;
import com.nitin.employeeservice.entiry.Employee;
import com.nitin.employeeservice.exception.ResourceNotFoundException;
import com.nitin.employeeservice.repository.EmployeeRepository;
import com.nitin.employeeservice.service.APIClient;
import com.nitin.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	ModelMapper mapper;
	
	EmployeeRepository employeeRepository;
	
	//RestTemplate restTemplate;

	// WebClient webClient;
	
	  APIClient apiClient;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		

		Employee employee = mapper.map(employeeDto, Employee.class);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto dto = mapper.map(savedEmployee, EmployeeDto.class);
		
		return dto;
	}

	@Override
	public ApiResponseDto getEmployeeById(Long id) {
		

		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Empoyee not found"));
		
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		
//		ResponseEntity<DepartmentDto> departmentResponseEntity = restTemplate
//				.getForEntity("http://localhost:8080/api/departments/"+employeeDto.getDepartmentCode(), 
//				DepartmentDto.class);
//		
//		DepartmentDto departmentDto = departmentResponseEntity.getBody();
//		
		
		/*
		 * DepartmentDto departmentDto =webClient.get()
		 * .uri("http://localhost:8080/api/departments/"+employeeDto.getDepartmentCode()
		 * +"") .retrieve() .bodyToMono(DepartmentDto.class) .block();
		 */
		
		DepartmentDto departmentDto  = apiClient.getDepartmentByCode(employeeDto.getDepartmentCode() );
		
		ApiResponseDto dto = new ApiResponseDto();
		
		dto.setEmployeeDto(employeeDto);
		dto.setDepartmentDto(departmentDto);
		
		return dto;
	}

}
