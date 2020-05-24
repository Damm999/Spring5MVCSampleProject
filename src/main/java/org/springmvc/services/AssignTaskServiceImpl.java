package org.springmvc.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.AssignTaskDAO;
import org.springmvc.exceptions.InvalidFormDataException;
import org.springmvc.models.EmployeeEntity;

@Service
@Transactional
public class AssignTaskServiceImpl implements AssignTaskService {
	
	@Autowired
	AssignTaskDAO assignTaskDAO;

	@Override
	public int getProjectId(String projectName) {
		return 0;
	}

	@Override
	public List<String> getEmployee(String pojectOrTaskId) {
		return assignTaskDAO.getEmployee(pojectOrTaskId);
	}


	@Override
	public List<String> getProjects() {
		return assignTaskDAO.getProjects();
	}

	@Override
	public boolean addATask(EmployeeEntity employeeEntity) {
		return assignTaskDAO.addATask(employeeEntity);
	}

	@Override
	public String getTaskId(String taskdescription) {
		return assignTaskDAO.getTaskId(taskdescription);
	}

	@Override
	public String getEmployeeId(String employeeName) throws InvalidFormDataException {
		return assignTaskDAO.getEmployeeId(employeeName);
	}

	@Override
	public List<String> getEmployeesForProject(String projectID) {
		return assignTaskDAO.getEmployeesForProject(projectID);
	}
	
	
}
