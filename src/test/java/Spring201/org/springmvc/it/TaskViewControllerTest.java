package Spring201.org.springmvc.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springmvc.controller.TaskViewController;
import org.springmvc.models.TasksEntity;
import org.springmvc.services.TaskViewService;

public class TaskViewControllerTest {
	
	@Mock
	private TaskViewService taskViewService;

	@InjectMocks
	private TaskViewController taskViewController;
	
	private MockMvc mockMvc;
	
	/*@Autowired
	private WebApplicationContext context;*/
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(taskViewController).build();
		//mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testList() throws Exception{
		
		List<TasksEntity> tasksEntity  = new ArrayList<>();
		
		tasksEntity.add(new TasksEntity());
		tasksEntity.add(new TasksEntity());
		tasksEntity.add(new TasksEntity());
		
		System.out.println(taskViewService.getTask(1));
		
		Mockito.when(taskViewService.getTask(1)).thenReturn(tasksEntity);
		
		mockMvc.perform(get("/task"))
		.andExpect(status().isOk())
		.andExpect(view().name("TaskView"));
		
	}

}
