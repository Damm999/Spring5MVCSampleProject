package org.springmvc.dao;

import java.util.HashMap;
import java.util.List;

import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.TasksEntity;

public interface TaskViewDAO {
	

	public List<TasksEntity> getAllTasks();
	
	public HashMap<Integer, ?> getTaskedEmployees(List<TasksEntity> tasksEntities);
	
	public  List<EmployeeEntity> getTaskedEmployees(int id);

	public List<TasksEntity> getTask(int projId);

	
}
