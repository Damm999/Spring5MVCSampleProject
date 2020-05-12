package org.springmvc.services;

import java.util.List;

import org.springmvc.exceptions.InvalidFormDataException;
import org.springmvc.models.EmployeeEntity;

public interface AssignTaskService {
	public int getProjectId(String projectName);

	public List<String> getEmployee(String pojectOrTaskId);
	
	public List<String> getEmployeeIDs(String projectID);

	public boolean addATask(EmployeeEntity employeeEntity);

	public List<String> getProjects();

	public String getTaskId(String taskdescription);

	public String getEmployeeId(String employeeName) throws InvalidFormDataException;
}
