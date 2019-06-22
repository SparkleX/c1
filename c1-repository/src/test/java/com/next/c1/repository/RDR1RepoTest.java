package com.next.c1.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoRDR1;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RDR1RepoTest extends BaseRepoTest<DoRDR1, RDR1Repository> {
	@Test
	public void findByOrderIdTest() throws Exception {
		this.repo.findByOrderId(1);
	}
}
