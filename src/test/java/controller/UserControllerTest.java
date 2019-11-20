package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nitin.controller.UserController;
import com.nitin.model.User;
import com.nitin.service.impl.UserServiceImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes=UserController.class)
public class UserControllerTest {


	 @Autowired
	  private WebApplicationContext webApplicationContext;
	
	 @MockBean
		private UserServiceImpl userService;
	 
	MockMvc mvc;
	
    //@Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userService = new UserServiceImpl();
    }

    //@Test
    public void test_getUsers() throws Exception {
    	List<User> users = new ArrayList<User>(Arrays.asList(new User(1,"test",100,1)));
    	Mockito.when(
    			userService.findAll()).thenReturn(users);

    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/users").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
    }


}
