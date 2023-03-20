package com.nitin.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitin.employeeservice.entiry.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
