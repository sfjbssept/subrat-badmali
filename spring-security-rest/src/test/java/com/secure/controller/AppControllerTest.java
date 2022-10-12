package com.secure.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secure.entity.Employee;

@RunWith(SpringRunner.class)
@WebMvcTest({AppController.class})
@ActiveProfiles(value = "true")
public class AppControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Value("${employee.get.url}")
	String geturl;
	
	@Value("${employee.post.url}")
	String posturl;
	
	@Value("${employee.put.url}")
	String puturl;
	
	@Value("${employee.delete.url}")
	String deleteurl;
	
	@Value("${user.user1.username}")
	String username1;
	
	@Value("${user.user1.password}")
	String password1;
	
	@Value("${user.user1.username}")
	String admin_name1;
	
	@Value("${user.user1.password}")
	String admin_password1;
	
	@Test
	public void testEmployeeGet() throws Exception {
		ResultActions responseEntity = processApiRequest(geturl,HttpMethod.GET,null,null,username1,password1);
		responseEntity.andExpect(status.isOk());
		//ObjectMapper mapper = new ObjectMapper();
		String result = responseEntity.andReturn().getResponse().getContentAsString();
		assertEquels("get employee",result);
		
	}
	
	@Test
	public void testEmployeePost() throws Exception {
		Employee employee = createEmployee("test", "dev");
		ResultActions responseEntity = processApiRequest(posturl, HttpMethod.POST,null, employee, admin_name1, admin_password1);
		responseEntity.andExpect(status().isOk());
		Employee result = mapper.readvalue(responseEntity.andReturn().getResponse().getContentAsString(),
				new TypeReference<Employee>() {});
		
		assertEquels("test",result.getName());
		assertEquels("dev",result.getName());
		
				
		
	}

	}

	private ResultActions processApiRequest(String api, HttpMethod methodType, String name, Employee employee,
			String username, String password) {
		
		ResultActions response = null;
		String secret = "Basic" +Base64Utils.encodeToString((username:+password).getBytes());
		try {
			switch(methodType) {
			case GET :
				//response = mockMvc.perform(get(api).header(HttpHeaders.AUTHORIZATION,secret));
				response = mockMvc.perform(get(api).with(user(username).password(password).roles("USER")));
				break;
			case POST :
				response = mockMvc.perform(
						post(api).header(HttpHeaders.AUTHORIZATION,secret).contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(employee)).accept(MediaType.APPLICATION_JSON);
						
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
			
		}
		return response;
	}
	
	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper =new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			
			return jsonContent;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private Employee createEmployee(String name, String role) {
		Employee empEmployee = new Employee(name,role);
		
		return empEmployee;
	}
	
		
		
		
		
		
	
	
	
	
	
	
	
	
	

}
