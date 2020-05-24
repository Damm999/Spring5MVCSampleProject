package Spring201.org.springmvc.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springmvc.controller.AssignTaskRestController;
import org.springmvc.services.AssignTaskService;

public class AssignTaskRestControllerTest {
	
	@Mock
	private AssignTaskService assignTaskService;

	@InjectMocks
	private AssignTaskRestController assignTaskRestController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(assignTaskRestController).build();
	}
	
	
	@Test
	public void testgetEmployees() throws Exception{
		
		List<String> employees = new ArrayList<>();
		
		employees.add("Ramesh M10392093");
		
		Mockito.when(assignTaskService.getEmployee("1")).thenReturn(employees);
		Mockito.when(assignTaskService.getEmployeesForProject("1")).thenReturn(employees);
		
		mockMvc.perform(get("/api/assign/getEmployee?pojectOrTaskId=1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employees.toString()))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testgetEmployees_neg() throws Exception{
		
		List<String> employees = new ArrayList<>();
		
		employees.add("Ramesh M10392093");
		
		Mockito.when(assignTaskService.getEmployee("1")).thenReturn(employees);
		Mockito.when(assignTaskService.getEmployeesForProject("1")).thenReturn(employees);
		
		mockMvc.perform(get("/api/assign/getEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employees.toString()))
		.andExpect(status().isBadRequest());
		
	}


	@Test
	public void testGetProjects() throws Exception{
		
		List<String> projects = new ArrayList<>();
		projects.add("MS OPSN");
		projects.add("UI Test");
		
		Mockito.when(assignTaskService.getProjects()).thenReturn(projects);
		
		mockMvc.perform(get("/api/assign/getProjects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(projects.toString()))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetProjects_neg() throws Exception{
		
		List<String> projects = new ArrayList<>();
		projects.add("MS OPSN");
		projects.add("UI Test");
		
		Mockito.when(assignTaskService.getProjects()).thenReturn(projects);
		
		mockMvc.perform(get("/api/getProjects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(projects.toString()))
		.andExpect(status().isNotFound());
		
	}
}
