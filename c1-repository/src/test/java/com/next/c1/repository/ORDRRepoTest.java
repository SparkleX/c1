package com.next.c1.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoORDR;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ORDRRepoTest extends BaseRepoTest<DoORDR, ORDRRepository> {
	
}
