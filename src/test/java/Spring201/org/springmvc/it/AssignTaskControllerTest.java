package Spring201.org.springmvc.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springmvc.controller.AssignTaskController;
import org.springmvc.models.EmployeeEntity;
import org.springmvc.models.TasksEntity;
import org.springmvc.services.AssignTaskService;

public class AssignTaskControllerTest {
	
	@Mock
	private AssignTaskService assignTaskService;

	@InjectMocks
	private AssignTaskController assignTaskController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(assignTaskController).build();
	}
	
	@Test
	public void testList() throws Exception{
		
		EmployeeEntity employeeEntity  = new EmployeeEntity();
		
		
		Mockito.when(assignTaskService.addATask(employeeEntity)).thenReturn(true);
		
		mockMvc.perform(get("/assign"))
		.andExpect(status().isOk())
		.andExpect(view().name("AssignTask"));
		
	}
	
	@Test
	public void testList_neg() throws Exception{
		
		EmployeeEntity employeeEntity  = new EmployeeEntity();
		
		
		Mockito.when(assignTaskService.addATask(employeeEntity)).thenReturn(false);
		
		mockMvc.perform(post("/assign"))
		.andExpect(status().is(200))
		.andExpect(view().name("AssignTask"));
		
	}
	
	@Test
	public void testgetEmployees() throws Exception{
		
		EmployeeEntity employeeEntity  = new EmployeeEntity();
		TasksEntity tasksEntity = new TasksEntity();
		tasksEntity.setProj_id(1);
		tasksEntity.setDescription("Test Description");
		tasksEntity.setStartDate(new Date());
		tasksEntity.setEndDate(new Date());
		employeeEntity.setTaskEntity(tasksEntity);
		String [] emp = {"Raghav","Suresh"};
		employeeEntity.setEmployees(emp);
		
		Date start = new Date();
		SimpleDateFormat format  = new SimpleDateFormat("dd-MM-yyyy");
		format.format(start);
		
		employeeEntity.getTaskEntity().setStartDate(start);
		employeeEntity.getTaskEntity().setEndDate(start);
		
		
		Mockito.when(assignTaskService.addATask(employeeEntity)).thenReturn(true);
		
		mockMvc.perform(post("/addEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeEntity.toString()))
		.andExpect(status().isOk());
		
	}

	@Test
	public void testgetEmployees_neg() throws Exception{
		
		TasksEntity tsk = new TasksEntity();
		
		Mockito.when(assignTaskService.addATask(Mockito.any(EmployeeEntity.class))).thenReturn(true);
		
		mockMvc.perform(get("/addEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(tsk.toString()))
		.andExpect(status().isMethodNotAllowed());
		
	}
}
