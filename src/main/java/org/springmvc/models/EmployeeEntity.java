package org.springmvc.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Emp_ID")
	private String emp_id;

	@Column(name = "Emp_Name")
	private String emp_name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Proj_ID")
	private ProjectEntity projectEntity;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	@JoinColumn(name = "Task_ID")
	private TasksEntity taskEntity;

	@Transient
	private String[] employees;

	@Transient
	private int projectName;

	public int getProjectName() {
		return projectName;
	}

	public void setProjectName(int projectName) {
		this.projectName = projectName;
	}

	public String[] getEmployees() {
		return employees;
	}

	public void setEmployees(String[] employees) {
		this.employees = employees;
	}

	public TasksEntity getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(TasksEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	/*public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}*/

	/*
	 * public String getTask_id() { return task_id; }
	 * 
	 * public void setTask_id(String task_id) { this.task_id = task_id; }
	 */

}
