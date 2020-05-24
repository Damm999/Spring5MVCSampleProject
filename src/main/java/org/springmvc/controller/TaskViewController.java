package org.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.services.TaskViewService;

@Controller
public class TaskViewController {
	@Autowired
	TaskViewService taskViewService;
	
	@RequestMapping(value = "/task")
	public String indexPage( @ModelAttribute("assignTaskEntity") EmployeeEntity employeeEntity) {
		return "TaskView";
	}
	
	

}
