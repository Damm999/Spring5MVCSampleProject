package org.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.TasksEntity;

@Repository
public class AssignTaskDAOImpl extends AbstractDao<Integer, EmployeeEntity> implements AssignTaskDAO {

	Logger log = Logger.getLogger(AssignTaskDAOImpl.class);

	/* 
	 * Method to retrieve project Id for a project
	 */
	@Override
	public int getProjectId(String projectName) {

		log.info("Finding project Id for project: " + projectName);
		int projId = 0;
		try {
			Query<Integer> query = getSession()
					.createQuery("select proj_Id from ProjectEntity where proj_Name=:projectName", Integer.class);
			query.setParameter("projectName", projectName);
			projId = query.getSingleResult();
		} catch (Exception e) {
			log.error("Exception occcured in getProjectId(projectName) method due to: " + e.getMessage());
		}
		return projId;
	}

	/* 
	 * Method to get Employees for a particular project.
	 */
	@Override
	public List<String> getEmployee(String pojectOrTaskId) {
		log.info("Getting list of employees for project id: " + pojectOrTaskId);
		List<String> listOfEmployees = new ArrayList<>();
		try {
			Query<String> query = getSession().createQuery(
					"select emp_name from EmployeeEntity where Proj_ID=:pojectOrTaskId and task_id=null ",
					String.class);
			query.setParameter("pojectOrTaskId", pojectOrTaskId);
			listOfEmployees = query.getResultList();
		} catch (Exception e) {
			log.error("Exception occured in getEmployee(projectOrTaskId) method due to: " + pojectOrTaskId);
		}
		return listOfEmployees;
	}

	/* 
	 * Method to add a task for an employee
	 */
	@Override
	public boolean addATask(EmployeeEntity employeeEntity) {
		log.info("Adding Task " + employeeEntity);

		boolean isSuccessfull = false;
		try {

			TasksEntity tskEntity = new TasksEntity();
			tskEntity.setDescription(employeeEntity.getTaskEntity().getDescription());
			tskEntity.setStartDate(employeeEntity.getTaskEntity().getStartDate());
			tskEntity.setEndDate(employeeEntity.getTaskEntity().getEndDate());
			tskEntity.setProj_id(employeeEntity.getTaskEntity().getProj_id());

			String[] employees = new String[employeeEntity.getEmployees().length];
			int i = 0;
			for (String s : employeeEntity.getEmployees()) {
				employees[i] = s.substring(s.lastIndexOf(" ") + 1);
				i++;
			}

			getSession().save(tskEntity);

			System.out.println("Task Id generated: " + tskEntity.getTask_id());
			Query<?> query = getSession()
					.createQuery("update EmployeeEntity set Task_ID=:tskId  where emp_id in :employee");
			query.setParameter("tskId", tskEntity.getTask_id());
			query.setParameterList("employee", employees);

			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			isSuccessfull = true;

		} catch (Exception e) {
			log.error("Exception occured while adding a task: " + e.getCause());
			log.error(e.getMessage());
			System.out.println("Exception occured in  add a task methdo due to: " + e.getMessage());
			isSuccessfull = false;
		}

		return isSuccessfull;
	}

	/* 
	 * Method to get all the projects.
	 */
	@Override
	public List<String> getProjects() {

		log.info("Getting all the projects....");

		List<String> acc = new ArrayList<>();
		try {
			Query<String> query = getSession().createQuery("select proj_Name from ProjectEntity", String.class);
			acc = query.getResultList();
		} catch (Exception e) {
			log.error("Exception occured in getting projects due to : " + e.getMessage());
		}
		return acc;
	}

	/* 
	 * Method to get Task id based on task description
	 */
	@Override
	public String getTaskId(String taskdescription) {
		log.info("Getting taskId .......");
		Query<Integer> query = null;
		try {
			query = getSession().createQuery("select task_id from TasksEntity where description=:desc", Integer.class);
			query.setParameter("desc", taskdescription);
		} catch (Exception e) {
			log.error("Exception occured in getting task Id for the project: " + e.getMessage());
		}

		return String.valueOf(query.getSingleResult());

	}

	/* 
	 * Method to get Employee Id based on Employee Name.
	 */
	@Override
	public String getEmployeeId(String employeeName) {
		log.info("Getting employee id ....");
		Query<String> query2 = null;
		try {
			query2 = getSession().createQuery("Select emp_id From EmployeeEntity where emp_name=:employee",
					String.class);
			query2.setParameter("employee", employeeName);
			System.out.println(query2.getSingleResult());
		} catch (Exception e) {

			log.error("Exceoption occured in gettig employee id for employee:" + employeeName + " due to: "
					+ e.getMessage());
		}
		return query2.getSingleResult();
	}

	/*
	 * Method to get all the employees based on project id
	 */
	@Override
	public List<String> getEmployeesForProject(String projectID) {

		log.info("Getting employees based on project...");
		List<String> listOfEmployees = new ArrayList<>();
		try {
			Query<String> query = getSession().createQuery(
					"select emp_id from EmployeeEntity where Proj_ID=:pojectId and task_id=null ", String.class);
			query.setParameter("pojectId", projectID);
			listOfEmployees = query.getResultList();
		} catch (Exception e) {
			log.error("Exception occured while retriving employee Id for project: " + projectID);
		}
		return listOfEmployees;
	}

}
