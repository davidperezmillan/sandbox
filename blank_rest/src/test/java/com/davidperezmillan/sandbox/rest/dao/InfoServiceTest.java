package com.davidperezmillan.sandbox.rest.dao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.davidperezmillan.sandbox.rest.entities.Info;
import com.davidperezmillan.sandbox.rest.repositories.InfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@WebMvcTest
public class InfoServiceTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private InfoService InfoService;
 
    private static ObjectMapper mapper = new ObjectMapper();
 
    @Test
    public void testGetAll() throws Exception {
        List<Info> Infos = new ArrayList<>();
        Info Info = new Info();
        Info.setId(1);
        Info.setAuthor("test");
        Infos.add(Info);
        Mockito.when(InfoService.getInfos()).thenReturn(Infos);
        mockMvc.perform(get("/Infos")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].author", Matchers.equalTo("test")));
    }
    @Test
    public void testGet() throws Exception {
        List<Info> Infos = new ArrayList<>();
        Info Info = new Info();
        Info.setId(1);
        Info.setAuthor("test");
        Infos.add(Info);
        Mockito.when(InfoService.getInfos()).thenReturn(Infos);
        mockMvc.perform(get("/Infos").param("id", "1")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].author", Matchers.equalTo("test")));
    }
    
    @Test
    public void testPost() throws Exception {
        Info Info = new Info();
        Info.setId(1);
        Info.setAuthor("test");
        Mockito.doNothing().when(InfoService).saveInfo(Mockito.any(Info.class));
        String json = mapper.writeValueAsString(Info);
        mockMvc.perform(post("/Infos").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
                //.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                //.andExpect(jsonPath("$.name", Matchers.equalTo("test")));
    }
    
    @Test
    public void testDelete() throws Exception {
        Mockito.when(InfoService.deleteInfo(ArgumentMatchers.anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/Infos/{id}", ArgumentMatchers.anyInt())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        
             
    }
    
    
}