package com.next.c1.service;

import java.io.IOException;
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

import com.next.c1.schema.table.Table;

@Service
public class MetadataService {
	@Value("classpath:domain/*")
	private Resource[] resources;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	JAXBContext jaxbContext;
	Unmarshaller jaxbUnmarshaller;

	@PostConstruct
	public void init() throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Table.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	}
	public Table getMetadata(String table)  {
		Resource resource = resourceLoader.getResource("classpath:domain/"+table+".xml");
		try (InputStream is = resource.getInputStream()) {
			Table rt = (Table) jaxbUnmarshaller.unmarshal(is);
			return rt;
		}catch (Exception ex){
			throw new RuntimeException(ex);			
		}		
	}
	public Map<String, Table> getAllMetadata() {
		try {
			Map<String, Table> rt = new HashMap<>();
			for (Resource resource : resources) {
				try (InputStream is = resource.getInputStream()) {
					Table table = (Table) jaxbUnmarshaller.unmarshal(is);
					rt.put(table.getId(), table);
				}
			}
			return rt;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
