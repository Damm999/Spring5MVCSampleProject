package org.springmvc.models;



import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class AssignTaskEntity {

	/*private String projectName;
	
	@Size(min=2, max=30, message="Description field does not meet the minium requiremnts")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@FutureOrPresent(message="Start Date cannot past the time.")
	@NotNull
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@NotNull
	private Date endDate;
	
	private String []employee;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String[] getEmployee() {
		return employee;
	}

	public void setEmployee(String[] employee) {
		this.employee = employee;
	}*/
	
}
