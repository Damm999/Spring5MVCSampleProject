package Spring201.org.springmvc.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springmvc.controller.TaskViewController;
import org.springmvc.controller.TaskViewRestController;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.ProjectDetail;
import org.springmvc.models.TasksEntity;
import org.springmvc.services.AssignTaskService;
import org.springmvc.services.TaskViewService;

public class TaskViewRestControllerTest {

	@Mock
	private TaskViewService taskViewService;

	@Mock
	AssignTaskService assignTaskService;

	@InjectMocks
	private TaskViewRestController taskViewRestController;

	private MockMvc mockMvc;

	/*
	 * @Autowired private WebApplicationContext context;
	 */

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(taskViewRestController).build();
		// mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testListOfEmployees() throws Exception {

		List<EmployeeEntity> empList = new ArrayList<>();
		EmployeeEntity employeeEntity1 = new EmployeeEntity();
		TasksEntity tasksEntity = new TasksEntity();
		tasksEntity.setProj_id(1);
		tasksEntity.setDescription("Test Description");
		tasksEntity.setStartDate(new Date());
		tasksEntity.setEndDate(new Date());
		employeeEntity1.setTaskEntity(tasksEntity);
		String[] emp = { "Raghav", "Suresh" };
		employeeEntity1.setEmployees(emp);

		EmployeeEntity employeeEntity2 = new EmployeeEntity();
		TasksEntity tasksEntity2 = new TasksEntity();
		tasksEntity2.setProj_id(1);
		tasksEntity2.setDescription("Test Description");
		tasksEntity2.setStartDate(new Date());
		tasksEntity2.setEndDate(new Date());
		employeeEntity2.setTaskEntity(tasksEntity2);
		String[] emp2 = { "Raghav", "Suresh" };
		employeeEntity1.setEmployees(emp2);

		empList.add(employeeEntity1);
		empList.add(employeeEntity2);

		System.out.println("employees: " + taskViewService.getTaskedEmployees(1).addAll(empList));

		Mockito.when(taskViewService.getTaskedEmployees(1)).thenReturn(empList);

		mockMvc.perform(get("/getTaskedEmployees?taskId=1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json"));

	}

	@Test
	public void testProjectDetails() throws Exception {

		ProjectDetail proj = new ProjectDetail();

		List<List<EmployeeEntity>> employee_list = new ArrayList<>();
		List<EmployeeEntity> emp_list = new ArrayList<>();
		emp_list.add(new EmployeeEntity());
		employee_list.add(emp_list);
		proj.setEmployee_list(employee_list);

		List<String> project = new ArrayList<>();
		project.add("MS");
		proj.setProjects(project);

		List<List<TasksEntity>> task_list = new ArrayList<>();
		List<TasksEntity> tsk_list = new ArrayList<>();
		tsk_list.add(new TasksEntity());
		task_list.add(tsk_list);
		proj.setTask_list(task_list);

		Mockito.when(assignTaskService.getProjects()).thenReturn(project);

		Mockito.when(taskViewService.getTaskedEmployees(1)).thenReturn(employee_list.get(0));

		Mockito.when(taskViewService.getTask(1)).thenReturn(task_list.get(0));

		mockMvc.perform(get("/project?projId=1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith("application/json"));

	}
	
	@Test
	public void getListOfTask() throws Exception {
		List<TasksEntity> tasksEntities = new ArrayList<>();
		tasksEntities.add(new TasksEntity());
 		Mockito.when(taskViewService.getTask(1)).thenReturn(tasksEntities);

		mockMvc.perform(get("/getTasks?projId=1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith("application/json"));

	}

}
