package com.next.c1.service;

import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.next.c1.repository.BaseRepository;
import com.next.jpatis.core.JpaUtils;
import com.next.jpatis.core.SqlConnection;

@Service
public class BaseSearchService<T,REPO extends BaseRepository<T,Integer>> {
	
	@Autowired
	ApplicationContext appContext;
	@Autowired
	SqlConnection sql;
	
	@Autowired
	HttpServletRequest httpRequest;

	private void buildOrderby(StringBuilder sb) {
		String order = httpRequest.getParameter("$orderby");
		if(order==null) {
			return;
		}
		sb.append(" order by").append(" ").append(order);
	}
	
	private void buildSelect(StringBuilder sb) {
		String select = httpRequest.getParameter("$select");
		sb.append("select ");
		if(select==null) {
			sb.append("* ");
			return;
		}
		sb.append(select).append(" ");
	}
	public List<T> search(BaseService<T,REPO> service) {
		
		Class<T> domainType = service.getDomainType();
		String tableName = JpaUtils.getTableName(domainType);
		StringBuilder sb = new StringBuilder();
		buildSelect(sb);
		sb.append(" from ").append(tableName);
		
		sb.append(" where 1=1 and");
		for(Entry<String, String[]> entry:httpRequest.getParameterMap().entrySet()) {
			String column = entry.getKey();
			if(column.startsWith("$")) {
				continue;
			}
			String value = entry.getValue()[0];
			sb.append(column).append("='").append(value).append("' and");
		}
		sb.setLength(sb.length()-4);
		buildOrderby(sb);
		List<T> rt = sql.select(domainType, sb.toString());
		return rt;
	}

}
