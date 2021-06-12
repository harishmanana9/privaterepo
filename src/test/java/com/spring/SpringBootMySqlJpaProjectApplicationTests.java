package com.spring;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith( SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class SpringBootMySqlJpaProjectApplicationTests {

	@Autowired
    private MockMvc mockMvc;
    @Test
    public void testGetsAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testGetsAProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testProductNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/4000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }
    
    @Test
    public void testAddNewProduct() throws Exception {
        String product = "{\"productName\":\"Washing Machine\",\"price\":\"19000\",\"category\":\"Electronics\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(product)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
}
