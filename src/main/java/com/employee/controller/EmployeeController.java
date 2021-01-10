package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;



@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeDAO employeedao;
	
	
	@GetMapping("/getEmployees")
    public List<Employee> getAllEmployee() {
        return employeedao.findAll();
    }
	
	
	@GetMapping("/getEmployees/{id}")	
	public Optional<Employee> getEmployeeById(@PathVariable(value = "id") int id) 
	{
		return employeedao.findById(id);
		
	}
	
	
	@PutMapping(path="/updateEmployee")
	public String updateEmployee(@RequestBody Employee employee) 
	{
		employeedao.save(employee);
		return "employee updated";
	}
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable int id)
	{

		Optional<Employee> e = employeedao.findById(id);
		if(e.isPresent()) {
			employeedao.delete(e.get());
			return "Employee is deleted with id "+id;
		}else {
			throw new RuntimeException("Employee not found with id : "+id);
		}
	}
	
	@PostMapping(path="/saveEmployee")
	public String addEmployee(@RequestBody List<Employee> employee) 
	{
		employeedao.saveAll(employee);
		return "employee added : "+employee.size();
	}
}
