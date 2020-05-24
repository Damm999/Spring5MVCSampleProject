package org.springmvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.TasksEntity;

@Repository
public class TaskViewDAOImpl extends AbstractDao<Integer, TasksEntity> implements TaskViewDAO {

	Logger log = Logger.getLogger(TaskViewDAOImpl.class);
	

	/* 
	 * Method to get All Task created for different projects
	 */
	@Override
	public List<TasksEntity> getAllTasks() {
		log.info("Getting all the tasks..");
		List<TasksEntity> tasksEntities = new ArrayList<>();
		
		try {
		Query<TasksEntity> query = getSession().createQuery("From TasksEntity", TasksEntity.class);
		tasksEntities = query.getResultList();
		}catch (Exception e) {
			log.error("Exception occured while geting all tasks: "+e.getMessage());
		}

		return tasksEntities;
	}

	/* 
	 * Method to get List of All Employees based on Task id for every proeject 
	 */
	@Override
	public HashMap<Integer, ?> getTaskedEmployees(List<TasksEntity> tasksEntities) {
		log.info("Getting empolyees assigned to task for every project...");
		HashMap<Integer, ArrayList<EmployeeEntity>> employees = new HashMap<>();
		try {
		
		int count = ((Long) getSession().createQuery("select count(*) from EmployeeEntity").uniqueResult()).intValue();
		getSession().getTransaction().commit();
		
		getSession().beginTransaction();
		List<EmployeeEntity> employeeEntites = new ArrayList<>();
		Query<EmployeeEntity> query2 = getSession().createQuery("From EmployeeEntity", EmployeeEntity.class);
		employeeEntites = query2.getResultList();
		
		getSession().getTransaction().commit();

	

		for (int i = 0; i < tasksEntities.size(); i++) {
			int j = 0;
			ArrayList<EmployeeEntity> emp = new ArrayList<>();
			int id = tasksEntities.get(i).getTask_id();
			while (j < count) {
				try  {
					if (id == (employeeEntites.get(j).getTaskEntity().getTask_id())) {
						emp.add(employeeEntites.get(j));
					}
					j++;
				
				}catch (NumberFormatException e) {
					System.err.println("Caught Number format Exception: "+e.getCause());
					
					j++;
				}
				
			}
			employees.put(id, emp);
		}
		getSession().beginTransaction();
		}catch (Exception e) {
			log.error("Exception Occured while getting employees who has taks assigned: "+e.getMessage());
		}
		return employees;
	}

	/* (non-Javadoc)
	 * Method to get employees list based on task id.
	 */
	@Override
	public List<EmployeeEntity> getTaskedEmployees(int taskId) {

		log.info("Getting employees based on task...");
		List<EmployeeEntity> employeeEntites = new ArrayList<>();
		try {
		Query<EmployeeEntity> query1 = getSession().createQuery("From EmployeeEntity where task_id=:id", EmployeeEntity.class).setParameter("id", String.valueOf(taskId));
		employeeEntites = query1.getResultList();
		
		getSession().getTransaction().commit();

		/*HashMap<Integer, ArrayList<EmployeeEntity>> employees = new HashMap<>();*/

		
		getSession().beginTransaction();
		}catch (Exception e) {
			log.error("Exception occured while getting list of employees who has task: "+taskId+" due to: "+e.getMessage());
		}

		return employeeEntites;
	
	}

	/* 
	 * Method to get list of task for a particular project
	 */
	@Override
	public List<TasksEntity> getTask(int projId) {
		log.info("Getting taks for prjects....");
		List<TasksEntity> tasksEntities = new ArrayList<>();
		try {
		Query<TasksEntity> query = getSession().createQuery("From TasksEntity where proj_id= :pId", TasksEntity.class);
		query.setParameter("pId", projId);
		tasksEntities = query.getResultList();
		}catch (Exception e) {
			log.error("Exception occured while getting list of task assigned for prohectID: "+projId+" due to: "+e.getMessage());
		}

		return tasksEntities;
	}

}
