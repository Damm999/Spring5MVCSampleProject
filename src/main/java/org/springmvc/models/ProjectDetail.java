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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_list == null) ? 0 : employee_list.hashCode());
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		result = prime * result + ((task_list == null) ? 0 : task_list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectDetail other = (ProjectDetail) obj;
		if (employee_list == null) {
			if (other.employee_list != null)
				return false;
		} else if (!employee_list.equals(other.employee_list))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		if (task_list == null) {
			if (other.task_list != null)
				return false;
		} else if (!task_list.equals(other.task_list))
			return false;
		return true;
	}


}
