package org.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.ProjectDetail;
import org.springmvc.models.TasksEntity;
import org.springmvc.services.AssignTaskService;
import org.springmvc.services.TaskViewService;

@RestController
public class TaskViewRestController {

	@Autowired
	TaskViewService taskViewService;

	@Autowired
	AssignTaskService assignTaskService;

	@RequestMapping(value = "/getTasks", method = RequestMethod.GET)
	public List<TasksEntity> getListOfTasks(@RequestParam int projId) {
		return taskViewService.getTask(projId);
	}

	@RequestMapping(value = "/getTaskedEmployees", method = RequestMethod.GET)
	public List<EmployeeEntity> getListOfEmployees(@RequestParam int taskId) {
		return taskViewService.getTaskedEmployees(taskId);
	}

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ProjectDetail getProjectdetails(@RequestParam int projId) {
		List<List<TasksEntity>> tasks = new ArrayList<>();
		List<List<EmployeeEntity>> emp = new ArrayList<>();

		List<String> noOfProject = assignTaskService.getProjects();
		if (projId == 0) {
			
			for (int i = 1; i <= noOfProject.size(); i++) {
				tasks.add(taskViewService.getTask(i));
				for (TasksEntity task : tasks.get(i - 1)) {
					emp.add(taskViewService.getTaskedEmployees(task.getTask_id()));
				}
			}
		} else {
			tasks.add(taskViewService.getTask(projId));
			for (TasksEntity task : tasks.get(0)) {
				emp.add(taskViewService.getTaskedEmployees(task.getTask_id()));
			}
		}
		int j =1, i=0;
		while(j<projId) {
			if(projId!=0 && (projId) != j) {
				noOfProject.remove(i);
				j++;
				i=0;
			}
			else {
				i++;
				j++;
			}
		}
			

		ProjectDetail proj = new ProjectDetail();

		proj.setEmployee_list(emp);
		proj.setTask_list(tasks);
		proj.setProjects(noOfProject);
		return proj;
	}
}
