package com.next.jpatis.test.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.next.jpatis.spring.JpatisRepository;
import com.next.jpatis.spring.SQL;
import com.next.jpatis.test.domain.Order;


public interface OrderRepository  extends JpatisRepository<Order,Integer>{

	@SQL("select * from [Order] where bpCode=?")
	List<Order> findByBpCode(String bpCode);

	@SQL("select * from [Order] where bpCode=?")
	Order findOneByBpCode(String bpCode);
	
	@SQL("select * from [Order] where bpCode=:bpCode and id=:id")
	List<Order> findByNamedParam(@Param("id")Integer id, @Param("bpCode")String bpCode);
	
	@SQL("select bpCode from [Order]")
	List<Object[]> findBySelect();
	
}
