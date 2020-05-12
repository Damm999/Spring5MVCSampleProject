package org.springmvc.models;

import java.util.List;

public class ProjectDetail {
	
	private List<List<EmployeeEntity>> employee_list;
	private List<List<TasksEntity>> task_list;
	private List<String> projects ;
	
	public ProjectDetail(List<List<EmployeeEntity>> emp, List<List<TasksEntity>> task_list) {
		this.employee_list = emp;
		this.task_list = task_list;
	}

	public ProjectDetail() {
		// TODO Auto-generated constructor stub
	}

	public List<List<EmployeeEntity>> getEmployee_list() {
		return employee_list;
	}

	public void setEmployee_list(List<List<EmployeeEntity>> employee_list) {
		this.employee_list = employee_list;
	}

	public List<List<TasksEntity>> getTask_list() {
		return task_list;
	}

	public void setTask_list(List<List<TasksEntity>> task_list) {
		this.task_list = task_list;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}


}
