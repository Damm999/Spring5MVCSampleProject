package org.springmvc.services;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.TaskViewDAO;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.TasksEntity;

@Service
@Transactional
public class TaskViewServiceImp implements TaskViewService {

	@Autowired
	TaskViewDAO taskViewDAO;

	@Override
	public List<TasksEntity> getAllTasks() {
		return taskViewDAO.getAllTasks();
	}

	@Override
	public HashMap<Integer, ?> getTaskedEmployees(List<TasksEntity> tasksEntities) {
		return taskViewDAO.getTaskedEmployees(tasksEntities);
	}

	@Override
	public List<EmployeeEntity> getTaskedEmployees(int id) {
		return taskViewDAO.getTaskedEmployees(id);
	}

	@Override
	public List<TasksEntity> getTask(int projId) {
		return taskViewDAO.getTask(projId);
	}

}
