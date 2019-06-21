package com.next.c1.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.next.c1.service.BaseService;
import com.next.jpatis.core.JpaUtils;
@Ignore
public class BaseControllerTest<T, SERVICE extends BaseService<T,?>>{
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper json;
	@MockBean
	private SERVICE mockService;
	
	@Test
	public void testGet() throws Exception 	{
		Class<T> domainClass = getDomainClass();
		T o = domainClass.newInstance();
		String table = getTableName();
		BDDMockito.given(this.mockService.get(1)).willReturn((T) o);
		String str = this.json.writeValueAsString(o);
		this.mvc.perform(MockMvcRequestBuilders.get("/api/"+table+"/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(str));
		Mockito.verify(this.mockService, Mockito.times(1)).get(1);
	}
	@Test
	public void testSearch() throws Exception{
		List<T> ret = new ArrayList<>();
		String table = getTableName();
		BDDMockito.given(this.mockService.search()).willReturn(ret);
		String str = this.json.writeValueAsString(ret);
		this.mvc.perform(MockMvcRequestBuilders.get("/api/"+table)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(str));
		Mockito.verify(this.mockService, Mockito.times(1)).search();
	}
	
	@Test
	public void testCreate() throws Exception{
		Class<T> domainClass = getDomainClass();
		T o = domainClass.newInstance();
		String str = this.json.writeValueAsString(o);		
		String table = getTableName();
		this.mvc.perform(
				MockMvcRequestBuilders.post("/api/"+table)
				.accept(MediaType.APPLICATION_JSON)
				.content(str))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
		
		ArgumentCaptor<T> argument = ArgumentCaptor.forClass(domainClass);
		Mockito.verify(this.mockService, Mockito.times(1)).create(argument.capture());
		assertEquals(str, this.json.writeValueAsString(argument.getValue()));		

	}
	
	@Test
	public void testUpdate() throws Exception{
		Class<T> domainClass = getDomainClass();
		T o = domainClass.newInstance();
		String str = this.json.writeValueAsString(o);		
		String table = getTableName();
		this.mvc.perform(
				MockMvcRequestBuilders.put("/api/"+table+"/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(str))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
		
		ArgumentCaptor<Integer> arg1 = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<T> arg2 = ArgumentCaptor.forClass(domainClass);
		Mockito.verify(this.mockService, Mockito.times(1)).update(arg1.capture(), arg2.capture());
		assertEquals(Integer.valueOf(1), arg1.getValue());	
		assertEquals(str, this.json.writeValueAsString(arg2.getValue()));		

	}
	

	@Test
	public void testDelete() throws Exception{
		String table = getTableName();
		this.mvc.perform(MockMvcRequestBuilders.delete("/api/"+table+"/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent())
				.andExpect(MockMvcResultMatchers.content().string(""));
		Mockito.verify(this.mockService, Mockito.times(1)).delete(1);
	}
	

	

	@SuppressWarnings("unchecked")
	protected Class<T> getDomainClass()	{
		return (Class<T>) GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseControllerTest.class)[0];
	}
	protected String getTableName()	{
		Class<?> clazz = getDomainClass();
		return JpaUtils.getTableName(clazz );
	}
}
