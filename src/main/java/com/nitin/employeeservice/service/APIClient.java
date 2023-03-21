package com.nitin.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nitin.employeeservice.dto.DepartmentDto;

@FeignClient(url = "http://localhost:8080", name = "department" )
public interface APIClient {
	
	
	 @GetMapping("api/departments/{departmentCode}")
	 DepartmentDto getDepartmentByCode(@PathVariable String departmentCode);

}
