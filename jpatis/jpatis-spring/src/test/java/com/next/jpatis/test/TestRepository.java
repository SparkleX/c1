package com.next.jpatis.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.jpatis.test.domain.Order;
import com.next.jpatis.test.repository.OrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestRepository {
	
	@Autowired
	protected OrderRepository orderRepo;

	@Test
	public void testCrud() throws Exception {
		Order entity = new Order();
		entity.setId(1);
		entity.setBpCode("C001");
		this.orderRepo.insert(entity);
		assertThat(this.orderRepo.findById(1).get().getBpCode()).isEqualTo("C001");
		entity.setBpCode("C002");
		this.orderRepo.update(entity);
		assertThat(this.orderRepo.findById(1).get().getBpCode()).isEqualTo("C002");
		
		this.orderRepo.delete(entity);
		assertThat(this.orderRepo.findById(1).isPresent()).isEqualTo(false);
		
	}
	@Test
	public void testCrudById() throws Exception {
		Order entity = new Order();
		entity.setId(1);
		entity.setBpCode("C001");
		this.orderRepo.insert(entity);
		assertThat(this.orderRepo.findById(1).get().getBpCode()).isEqualTo("C001");
		
		entity.setBpCode("C002");
		this.orderRepo.updateById(1, entity);
		assertThat(this.orderRepo.findById(1).get().getBpCode()).isEqualTo("C002");
		
		this.orderRepo.deleteById(1);
		assertThat(this.orderRepo.findById(1).isPresent()).isEqualTo(false);
		
	}	
	@Test
	public void testFind() throws Exception {
		List<Order> rt = this.orderRepo.findAll();
		assertThat(rt.size()).isGreaterThan(0);
		
		Optional<Order> entity = this.orderRepo.findById(99);
		assertThat(entity.isPresent()).isEqualTo(false);
		
		
	}
	
	@Test
	public void testQuery() throws Exception {
		List<Order> rt = this.orderRepo.findByBpCode("C100");
		assertThat(rt.size()).isEqualTo(1);
		
		Order entity = this.orderRepo.findOneByBpCode("C100");
		assertThat(entity.getId()).isEqualTo(100);
		
		rt = this.orderRepo.findByNamedParam(100,"C100");
		assertThat(rt.size()).isEqualTo(1);
		
		List<Object[]> array = this.orderRepo.findBySelect();
		assertThat(array.size()).isEqualTo(2);
		assertThat(array.get(0)[0]).isEqualTo("C100");
	}
}
