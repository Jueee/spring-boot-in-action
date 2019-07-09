package com.example.demo;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@WebAppConfiguration // 开启Web上下文测试
public class MockMvcWebTests {

    @Autowired
    private WebApplicationContext webContext; // 注入 WebApplicationContext

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        // 设置 MockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/")) // 向 / 发起一个 GET 请求
            .andExpect(status().isOk()) // 希望该请求处理成功
            .andExpect(view().name("readingList")) // 视图的逻辑名称为 readingList
            .andExpect(model().attributeExists("books")) // 模型包含一个名为 books 的属性
            .andExpect(model().attribute("books", is(empty()))); // 模型中的 books 属性是一个空集合
    }

    @Test
    public void postBook() throws Exception {
        mockMvc.perform(post("/")   // 发送一个HTTP POST 请求提交一本新书。
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 确保内容类型设置为application/x-www-form-urlencoded，这才是运行应用程序时浏览器会发送的内容类型
                    .param("title", "BOOK TITLE")   // 设置表单域，模拟要提交的表单
                    .param("author", "BOOK AUTHOR")
                    .param("isbn", "1234567890")
                    .param("description", "DESCRIPTION"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));   //  POST 请求处理后重定向回 “/”
        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle("BOOK TITLE");
        expectedBook.setAuthor("BOOK AUTHOR");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("DESCRIPTION");
        mockMvc.perform(get("/"))   // 模型将包含新添加的图书
            .andExpect(status().isOk())
            .andExpect(view().name("readingList"))
            .andExpect(model().attributeExists("books"))
            .andExpect(model().attribute("books", hasSize(1)))
            .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
    }

}
