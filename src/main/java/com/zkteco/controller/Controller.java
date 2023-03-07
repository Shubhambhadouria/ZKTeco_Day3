package com.zkteco.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.entity.Department;
import com.zkteco.exceptions.DataNotFoundException;
import com.zkteco.exceptions.DepartmentNotFoundException;
import com.zkteco.service.DepartmentService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class Controller {

	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	@PostMapping("/dep")
	public Department saveDepartmentController(@Valid @RequestBody Department department){
		LOGGER.info("Inside saveDepartmentCoontroller Method");
		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/departments/{Id}")
	public Department getDepartmentById(@PathVariable("Id") Long departmentId) throws DepartmentNotFoundException{
		LOGGER.info("Inside getDepartmentByIdCoontroller Method");
		return departmentService.getDepartmentById(departmentId);
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartmentList() throws DepartmentNotFoundException{
		LOGGER.info("Inside getDepartmentListCoontroller Method");
		return departmentService.getDepartmentList();
	}
	
	@DeleteMapping("/departments/{Id}")
	public String deleteDepartmentController(@PathVariable("Id") Long departmentId) throws DepartmentNotFoundException {
		LOGGER.info("Inside deleteDepartmentController Method");
		departmentService.deleteDepartmentById(departmentId);
		return "Department is deleted";
	}
	
	@PutMapping("/departments/{id}")
	public Department updateDepartmentById(@PathVariable("id") Long departmentId,
										   @RequestBody Department department ) throws DepartmentNotFoundException {
		LOGGER.info("Inside updateDepartmentByIdController Method");
		return departmentService.updateDepartementById(departmentId,department);
	}
	
	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String deptName) throws DepartmentNotFoundException {
		LOGGER.info("Inside fetchDepartmentByNameController Method");
		return departmentService.fetchDepartementByName(deptName);
	}
	
	@Transactional
	@DeleteMapping("/departments/name/{name}")
	public String deleteDepartmentByName(@PathVariable("name") String deptName) throws DepartmentNotFoundException {
		LOGGER.info("Inside deleteDepartmentByNameController Method");
		departmentService.deleteDepartementByName(deptName);
		return deptName+"Department deleted successfully!";
	}
	
}
