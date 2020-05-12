package org.springmvc.dao;

import java.util.List;

import org.springmvc.models.AssignTaskEntity;
import org.springmvc.models.EmployeeEntity;

public interface AssignTaskDAO {
	
	public int getProjectId(String projectName);
	
	public List<String> getEmployee(String pojectOrTaskId);
	
	public boolean addATask(EmployeeEntity employeeEntity);
	
	public List<String> getProjects();
	
	public String getTaskId(String taskdescription);
	
	public String getEmployeeId(String employeeName);

	public List<String> getEmployeeIDs(String projectID);
}
