package com.librarytest;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ViewControllerTest extends TestConfig {
    @Test
    public void checkHomePageRendered() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("home")).andExpect(MockMvcResultMatchers.forwardedUrl("/views/home.jsp"));
    }   

    @Test
    public void checkViewTableRendered() throws Exception{
      mockMvc.perform(MockMvcRequestBuilders.get("/view")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("viewbookuser")).andExpect(MockMvcResultMatchers.forwardedUrl("/views/viewbookuser.jsp")).andExpect(MockMvcResultMatchers.model().size(1)).andExpect(MockMvcResultMatchers.model().attributeExists("books"));
    }

}
