package com.next.c1.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.schema.table.Table;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=MetadataService.class)
public class MetadataServiceTest {

	//@MockBean
//	private RemoteService remoteService;

	@Autowired
	private MetadataService service;

	@Test
	public void exampleTest() {
		Map<String, Table> metadatas = service.getAllMetadata();
		assertThat(metadatas.keySet().size()).isGreaterThan(0);
	}

}