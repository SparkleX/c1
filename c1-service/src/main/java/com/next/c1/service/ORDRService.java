package com.next.c1.service;

import java.util.List;

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
	public DoORDR get(Integer id) {
		DoORDR doORDR = super.get(id);
		List<DoRDR1> listRDR1 = repoRDR1.findByOrderId(id);
		doORDR.setRDR1(listRDR1);
		return doORDR;
	}
	@Override
	public DoORDR change(DoORDR data, String table, String column, Integer row) {
		DoORDR rt = data;
		for(DoRDR1 line:data.getRDR1())	{
			Integer a = line.getItemId();
			System.out.println("a:"+line.getItemId());
			Integer b = 3-a;
			line.setItemId(b);
			System.out.println(line.getItemId());
		}
		return rt;
	}
}
