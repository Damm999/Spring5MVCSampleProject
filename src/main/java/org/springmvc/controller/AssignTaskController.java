package org.springmvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.services.AssignTaskService;

@Controller
public class AssignTaskController {
	
	@Autowired
	AssignTaskService assignTaskService;

	@RequestMapping(value = "/assign")
	public String assignTask( Model model) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		
		model.addAttribute("assignTaskEntity", employeeEntity);
		
		return "AssignTask";
	}
	
	 @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	    public String submit (
	     @Valid @ModelAttribute("assignTaskEntity") EmployeeEntity employeeEntity,
	      BindingResult result, Model model) {
		 
		 
		 if (result.hasErrors()) {
	            return "AssignTask";
	        }
		 
		 else {
			 
		 	assignTaskService.addATask(employeeEntity);
		 	 return "AssignTask";
		 }
		
	        /*if (result.hasErrors()) {
	            return "error";
	        }
	        assignTaskService.addATask(taskEntity, employee);
	        employeeMap.put(employee.getId(), employee);*/
	 
	       
	    }
	
	
}
