package com.example.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@WebAppConfiguration // 开启Web上下文测试
public class SpringSecurityTests {

    @Autowired
    private WebApplicationContext webContext; // 注入 WebApplicationContext

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        // 设置 MockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(springSecurity()).build();
    }
    
    // 如果请求未经身份验证，重定向到登录页面
    @Test
    public void homePage_unauthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().is3xxRedirection())
               .andExpect(header().string("Location", "http://localhost/login"));
    }
    
    @Test
    @WithMockUser(username="craig", password="password", roles="READER")
    public void homePage_authenticatedUser1() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("readingList"))
            .andExpect(model().attribute("books", hasSize(0)))
            .andExpect(model().attribute("amazonID", "Jueee"));
    }
    
    @Test
    @WithUserDetails(value="craig")     // 使用craig用户
    public void homePage_authenticatedUser2() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("readingList"))
            .andExpect(model().attribute("books", hasSize(1)))
            .andExpect(model().attribute("amazonID", "Jueee"));
    }
}
