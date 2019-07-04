package com.next.c1.service;

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
			Integer a = line.getItemId();
			Integer b = 4-a;
			line.setItemId(b);
		}
		data.getRDR1().remove(0);
		return rt;
	}
}
