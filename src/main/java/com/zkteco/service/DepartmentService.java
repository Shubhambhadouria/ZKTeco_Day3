package com.zkteco.service;

import java.util.List;

import com.zkteco.entity.Department;
import com.zkteco.exceptions.DataNotFoundException;
import com.zkteco.exceptions.DepartmentNotFoundException;

public interface DepartmentService {

	public Department saveDepartment(Department department);
	
	public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;
	
	public List<Department> getDepartmentList() throws DepartmentNotFoundException;
	
	public void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException;

	public Department updateDepartementById(Long departmentId, Department department) throws DepartmentNotFoundException;

	public Department fetchDepartementByName(String name) throws DepartmentNotFoundException;

	public void deleteDepartementByName(String deptName) throws DepartmentNotFoundException;
	
	
}
