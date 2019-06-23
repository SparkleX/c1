package com.next.c1.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.next.c1.service.MetadataService;
@RunWith(SpringRunner.class)
@WebMvcTest(MetadataController.class)
public class MetadataControllerTest{
	@Autowired
	private MockMvc mvc;

	@MockBean
	private MetadataService mockService;
	
	@Test
	public void getAllMetadataTest() throws Exception 	{
		BDDMockito.given(this.mockService.getAllMetadata()).willReturn(null);
		this.mvc.perform(MockMvcRequestBuilders.get("/api/metadata/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
		Mockito.verify(this.mockService, Mockito.times(1)).getAllMetadata();
	}

}
