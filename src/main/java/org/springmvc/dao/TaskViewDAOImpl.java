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
	

	@Override
	public List<TasksEntity> getAllTasks() {
		log.info("Getting all the tasks..");
		List<TasksEntity> tasksEntities = new ArrayList<>();
		Query<TasksEntity> query = getSession().createQuery("From TasksEntity", TasksEntity.class);
		tasksEntities = query.getResultList();

		return tasksEntities;
	}

	@Override
	public HashMap<Integer, ?> getTaskedEmployees(List<TasksEntity> tasksEntities) {
		log.info("Getting empolyees assigned to task for every project...");
		
		int count = ((Long) getSession().createQuery("select count(*) from EmployeeEntity").uniqueResult()).intValue();
		getSession().getTransaction().commit();
		
		getSession().beginTransaction();
		List<EmployeeEntity> employeeEntites = new ArrayList<>();
		Query<EmployeeEntity> query2 = getSession().createQuery("From EmployeeEntity", EmployeeEntity.class);
		employeeEntites = query2.getResultList();
		
		getSession().getTransaction().commit();

		HashMap<Integer, ArrayList<EmployeeEntity>> employees = new HashMap<>();

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

		return employees;
	}

	@Override
	public List<EmployeeEntity> getTaskedEmployees(int taskId) {

		log.info("Getting employees based on task...");
		Query<EmployeeEntity> query1 = getSession().createQuery("From EmployeeEntity where task_id=:id", EmployeeEntity.class).setParameter("id", String.valueOf(taskId));
		List<EmployeeEntity> employeeEntites = query1.getResultList();
		
		getSession().getTransaction().commit();

		/*HashMap<Integer, ArrayList<EmployeeEntity>> employees = new HashMap<>();*/

		
		getSession().beginTransaction();

		return employeeEntites;
	
	}

	@Override
	public List<TasksEntity> getTask(int projId) {
		log.info("Getting taks for prjects....");
		List<TasksEntity> tasksEntities = new ArrayList<>();
		Query<TasksEntity> query = getSession().createQuery("From TasksEntity where proj_id= :pId", TasksEntity.class);
		query.setParameter("pId", projId);
		tasksEntities = query.getResultList();

		return tasksEntities;
	}

}
