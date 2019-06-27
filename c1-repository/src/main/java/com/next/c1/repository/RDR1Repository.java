package com.next.c1.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.next.c1.domain.DoRDR1;
import com.next.jpatis.spring.SQL;

public interface RDR1Repository extends BaseRepository<DoRDR1, Integer> {

	@SQL("select * from RDR1 where parentId = :id")
	List<DoRDR1> findByOrderId(@Param("id")Integer id);

}
