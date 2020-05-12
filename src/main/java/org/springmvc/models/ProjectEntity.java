package org.springmvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class ProjectEntity {

	@Id
	@Column(name = "Proj_id")
	private int proj_Id;

	@Column(name = "Project_Name")
	private String proj_Name;

	public int getProj_Id() {
		return proj_Id;
	}

	public void setProj_Id(int proj_Id) {
		this.proj_Id = proj_Id;
	}

	public String getProj_Name() {
		return proj_Name;
	}

	public void setProj_Name(String proj_Name) {
		this.proj_Name = proj_Name;
	}

}
