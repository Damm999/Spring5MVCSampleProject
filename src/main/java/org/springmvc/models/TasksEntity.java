package org.springmvc.models;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "taks")
public class TasksEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Task_ID")
	private int task_id;

	@Column(name = "Proj_ID")
	private int proj_id;

	@Column(name = "Start_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	@FutureOrPresent(message="Start Date cannot past the time.")
	@NotNull(message="Start Date cannot be null.")
	private Date startDate;

	@Column(name = "End_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	@FutureOrPresent(message="Start Date cannot past the time.")
	@NotNull(message="End Date cannot be null.")
	private Date endDate;

	@Column(name = "Description")
	@Size(min=2, max=30, message="Description field does not meet the minium requiremnts")
	private String description;

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
