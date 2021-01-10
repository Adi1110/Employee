package com.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Employee;


public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

}
