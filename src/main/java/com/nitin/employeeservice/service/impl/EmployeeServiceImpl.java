package com.nitin.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nitin.employeeservice.dto.ApiResponseDto;
import com.nitin.employeeservice.dto.DepartmentDto;
import com.nitin.employeeservice.dto.EmployeeDto;
import com.nitin.employeeservice.entiry.Employee;
import com.nitin.employeeservice.exception.ResourceNotFoundException;
import com.nitin.employeeservice.repository.EmployeeRepository;
import com.nitin.employeeservice.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	ModelMapper mapper;
	
	EmployeeRepository employeeRepository;
	
	//RestTemplate restTemplate;

	 WebClient webClient;
	
	//  APIClient apiClient;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		

		Employee employee = mapper.map(employeeDto, Employee.class);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto dto = mapper.map(savedEmployee, EmployeeDto.class);
		
		return dto;
	}

	@Override
	@CircuitBreaker(name = "$(spring.application.name)" , fallbackMethod ="getDefaultDepertment" )
	public ApiResponseDto getEmployeeById(Long id) {
		

		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Empoyee not found"));
		
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		
//		ResponseEntity<DepartmentDto> departmentResponseEntity = restTemplate
//				.getForEntity("http://localhost:8080/api/departments/"+employeeDto.getDepartmentCode(), 
//				DepartmentDto.class);
//		
//		DepartmentDto departmentDto = departmentResponseEntity.getBody();
//		
		
		
		  DepartmentDto departmentDto =webClient.get()
		  .uri("http://localhost:8080/api/departments/"+employeeDto.getDepartmentCode()
		  +"") .retrieve() .bodyToMono(DepartmentDto.class) .block();
		 
		
		//DepartmentDto departmentDto  = apiClient.getDepartmentByCode(employeeDto.getDepartmentCode() );
		
		ApiResponseDto dto = new ApiResponseDto();
		
		dto.setEmployeeDto(employeeDto);
		dto.setDepartmentDto(departmentDto);
		
		return dto;
	}
	
	public ApiResponseDto getDefaultDepertment(Long id,Exception exception) {
		

		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Empoyee not found"));
		
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
		

		DepartmentDto departmentDto  = new DepartmentDto();
		
		departmentDto.setDepartmentCode("DXXX");
		departmentDto.setDepartmentName("Dummy");
		departmentDto.setDepartmentDescription("In case of fault");
		
		//DepartmentDto departmentDto  = apiClient.getDepartmentByCode(employeeDto.getDepartmentCode() );
		
		ApiResponseDto dto = new ApiResponseDto();
		
		dto.setEmployeeDto(employeeDto);
		dto.setDepartmentDto(departmentDto);
		
		return dto;
	}


}
