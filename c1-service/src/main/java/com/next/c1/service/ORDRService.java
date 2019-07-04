package com.next.c1.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.c1.domain.DoORDR;
import com.next.c1.domain.DoRDR1;
import com.next.c1.repository.ORDRRepository;
import com.next.c1.repository.RDR1Repository;
@Service
public class ORDRService extends BaseService<DoORDR, ORDRRepository> {
	@Autowired
	RDR1Repository repoRDR1;
	
	@Override
	public DoORDR change(DoORDR data, String table, String column, Integer row) {
		DoORDR rt = data;
		for(DoRDR1 line:data.getRDR1())	{
			if(line.getPrice()==null) {
				line.setPrice(BigDecimal.ZERO);
			}
			if(line.getQuantity()==null) {
				line.setQuantity(BigDecimal.ZERO);
			}
			line.setLineTotal(line.getPrice().multiply(line.getQuantity()));
		}
		BigDecimal total = data.getRDR1().stream().map(DoRDR1::getLineTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		rt.setDocTotal(total);
		return rt;
	}
}
