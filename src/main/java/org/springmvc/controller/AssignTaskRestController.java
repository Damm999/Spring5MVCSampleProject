package org.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springmvc.services.AssignTaskService;

@RestController
@RequestMapping(value="/api/assign")
public class AssignTaskRestController {
	
	@Autowired
	AssignTaskService assignTaskService;

	
	@RequestMapping(value="/getEmployee", method=RequestMethod.GET)
	public List<String> getListOfEmployees(@RequestParam String pojectOrTaskId) {
		List<String> projects = assignTaskService.getEmployee(pojectOrTaskId);
		List<String> Id =  assignTaskService.getEmployeeIDs(pojectOrTaskId);
		List<String> data = new ArrayList<>();
		for (int i=0;i<projects.size();i++) {
			data.add(projects.get(i)+ " "+Id.get(i) );
		}
		return data;
	}
	
	
	@RequestMapping(value="/getProjects", method=RequestMethod.GET)
	public List<String> getProjects() {
		List<String> projects = assignTaskService.getProjects();
		return projects;
	}
}
