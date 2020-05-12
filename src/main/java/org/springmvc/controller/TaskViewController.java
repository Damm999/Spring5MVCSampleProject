package org.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springmvc.models.AssignTaskEntity;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.ProjectDetail;
import org.springmvc.models.TasksEntity;
import org.springmvc.services.TaskViewService;

@Controller
public class TaskViewController {
	@Autowired
	TaskViewService taskViewService;
	
	@RequestMapping(value = "/task")
	public String hello( @ModelAttribute("assignTaskEntity") EmployeeEntity employeeEntity) {
		/*List<TasksEntity> taskedEntites = taskViewService.getAllTasks();
		model.addAttribute("tasks",taskedEntites);
		model.addAttribute("employees",taskViewService.getTaskedEmployees(taskedEntites));*/
		return "TaskView";
	}
	
	

}
