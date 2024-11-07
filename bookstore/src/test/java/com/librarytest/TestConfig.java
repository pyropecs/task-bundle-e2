package com.librarytest;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.Assert;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:home-servlet.xml" )
@WebAppConfiguration
public class TestConfig {


    @Autowired
    private WebApplicationContext webApplicationContext;

    
    protected  MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void checkWhetherApplcationContextIsPresent(){
      ServletContext context =  webApplicationContext.getServletContext();
      Assert.assertNotNull(context);
      Assert.assertTrue(context instanceof MockServletContext);
      // Assert.assertNotNull(webApplicationContext.getBean("bookController"));
    }

 
    
}
