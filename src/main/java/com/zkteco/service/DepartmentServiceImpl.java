package com.zkteco.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkteco.entity.Department;
import com.zkteco.exceptions.DataNotFoundException;
import com.zkteco.exceptions.DepartmentNotFoundException;
import com.zkteco.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
			return departmentRepository.save(department);
	}
	
	@Override
	public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> opt = departmentRepository.findById(departmentId);
		
		if(!opt.isPresent()) {
			throw new DepartmentNotFoundException("Department Not Found with given Id : "+departmentId);
		}else {
			return opt.get();
		}
	}
	
	@Override
	public List<Department> getDepartmentList() throws DepartmentNotFoundException {
		List<Department> dep =  departmentRepository.findAll();
		if(dep.size()==0) {
			throw new DepartmentNotFoundException("No Departments Found in Database");
		}else {
			return dep;
		}
	}
	
	@Override
	public void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> dep = departmentRepository.findById(departmentId);
		
		if(!dep.isPresent()) {
			throw new DepartmentNotFoundException("Departments Not Found in Database with Id : "+departmentId);
		}else {
			departmentRepository.deleteById(departmentId);
		}
		
	}

	@Override
	public Department updateDepartementById(Long departmentId, Department department) throws DepartmentNotFoundException {
		
		Optional<Department> opt =  departmentRepository.findById(departmentId);
		
		if(opt.isPresent()) {
			Department dept = departmentRepository.findById(departmentId).get();
			
			if(Objects.nonNull(department.getDepartmentName()) && 
			!"".equalsIgnoreCase(department.getDepartmentName())) {
				dept.setDepartmentName(department.getDepartmentName());
			}
			
			if(Objects.nonNull(department.getDepartmentCode()) && 
					!"".equalsIgnoreCase(department.getDepartmentCode())) {
						dept.setDepartmentCode(department.getDepartmentCode());	
			}
			
			if(Objects.nonNull(department.getDepartmentAddresss()) && 
					!"".equalsIgnoreCase(department.getDepartmentAddresss())) {
						dept.setDepartmentAddresss(department.getDepartmentAddresss());;
			}
			return departmentRepository.save(dept);
		}else {
			throw new DepartmentNotFoundException("Department Not Found in Database with Id : "+departmentId);
		}
	}

	@Override
	public Department fetchDepartementByName(String name) throws DepartmentNotFoundException {
		Department dep = departmentRepository.findByDepartmentNameIgnoreCase(name);
		
		if(dep!=null) {
			return departmentRepository.findByDepartmentNameIgnoreCase(name);
		}else {
			throw new DepartmentNotFoundException("Departments does not exist with name : "+name);
		}
	}

	@Override
	public void deleteDepartementByName(String deptName) throws DepartmentNotFoundException {
		Department opt =  departmentRepository.findByDepartmentNameIgnoreCase(deptName);
		if(opt!=null) {
			departmentRepository.deleteByDepartmentName(deptName);
		}else {
			throw new DepartmentNotFoundException("Department Not Found with Name : "+deptName);
		}
	}

}
