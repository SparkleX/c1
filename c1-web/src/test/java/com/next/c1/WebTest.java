package com.next.c1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoOCRD;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void exampleTest() {
		DoOCRD body = this.restTemplate.getForObject("/api/OCRD/1", DoOCRD.class);
		assertThat(body.getId()).isEqualTo(1);
	}
}