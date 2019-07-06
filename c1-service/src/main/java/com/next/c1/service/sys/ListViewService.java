package com.next.c1.service.sys;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.next.c1.schema.choose.List;
import com.next.c1.schema.table.Table;

@Service
public class ListViewService {
	@Value("classpath:domain/*")
	private Resource[] resources;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	JAXBContext jaxbContext;
	Unmarshaller jaxbUnmarshaller;

	@PostConstruct
	public void init() throws JAXBException {
		jaxbContext = JAXBContext.newInstance(List.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	}
	public List getData(String table)  {
		Resource resource = resourceLoader.getResource("classpath:list/"+table+".list.xml");
		try (InputStream is = resource.getInputStream()) {
			List rt = (List) jaxbUnmarshaller.unmarshal(is);
			return rt;
		}catch (Exception ex){
			throw new RuntimeException(ex);			
		}		
	}
}
