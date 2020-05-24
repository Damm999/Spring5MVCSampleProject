package org.springmvc.models;

import java.util.Arrays;

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
public class EmployeeEntity implements Comparable<EmployeeEntity> {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((emp_name == null) ? 0 : emp_name.hashCode());
		result = prime * result + Arrays.hashCode(employees);
		result = prime * result + id;
		result = prime * result + ((projectEntity == null) ? 0 : projectEntity.hashCode());
		result = prime * result + projectName;
		result = prime * result + ((taskEntity == null) ? 0 : taskEntity.hashCode());
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
		EmployeeEntity other = (EmployeeEntity) obj;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (emp_name == null) {
			if (other.emp_name != null)
				return false;
		} else if (!emp_name.equals(other.emp_name))
			return false;
		if (!Arrays.equals(employees, other.employees))
			return false;
		if (id != other.id)
			return false;
		if (projectEntity == null) {
			if (other.projectEntity != null)
				return false;
		} else if (!projectEntity.equals(other.projectEntity))
			return false;
		if (projectName != other.projectName)
			return false;
		if (taskEntity == null) {
			if (other.taskEntity != null)
				return false;
		} else if (!taskEntity.equals(other.taskEntity))
			return false;
		return true;
	}

	@Override
	public int compareTo(EmployeeEntity o) {
		if(getEmp_name().equals(o.getEmp_name()))
			return 0;
		else if (!getEmp_name().equals(o.getEmp_name()))
			return 1;
		return id;
	}


}
