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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + proj_Id;
		result = prime * result + ((proj_Name == null) ? 0 : proj_Name.hashCode());
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
		ProjectEntity other = (ProjectEntity) obj;
		if (proj_Id != other.proj_Id)
			return false;
		if (proj_Name == null) {
			if (other.proj_Name != null)
				return false;
		} else if (!proj_Name.equals(other.proj_Name))
			return false;
		return true;
	}

}
