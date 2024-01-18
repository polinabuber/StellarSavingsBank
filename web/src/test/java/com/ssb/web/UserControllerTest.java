//package com.ssb.web;
//
//import com.ssb.service.*;
//import junit.framework.TestCase;
//import org.junit.*;
//import org.junit.runner.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.test.context.*;
//import org.springframework.test.context.junit4.*;
//import org.springframework.test.context.web.*;
//import org.springframework.test.web.servlet.*;
//import org.springframework.test.web.servlet.setup.*;
//import org.springframework.web.context.*;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = WebConfiguration.class)
//public class UserControllerTest {
//
//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        mockMvc = null;
//    }
//
//
//
//    @Test
//    public void testShowRegistrationForm() throws Exception {
//        mockMvc.perform(post("/register"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("register"))
//                .andExpect(model().attributeExists("user", "account"));
//    }
//
//    @Test
//    public void testRegister() throws Exception {
//        mockMvc.perform(post("/register")
//                        .param("firstname", "John")
//                        .param("lastname", "Doe")
//                        .param("phoneNumber", "1234567890")
//                        .param("password", "password"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//}