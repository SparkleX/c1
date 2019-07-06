package com.next.c1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

	private String buildFuzzSearch() {
		StringBuilder sb = new StringBuilder();
		String search = httpRequest.getParameter("$search");
		if(StringUtils.isEmpty(search)) {
			return null;
		}
		String[] columns = getSelectColumn();
		if(columns.length == 0) {
			return null;
		}
		for(String column:columns) {
			sb.append(column).append(" like '%").append(search).append("%' or ");
		}
		sb.setLength(sb.length()-4);
		return sb.toString(); 
		
	}
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
	private String[] getSelectColumn() {
		String select = httpRequest.getParameter("$select");
		String[] rt = select.split(",");
		return rt;
		
	}	
	private List<String> getFilterColumn() {
		List<String> rt = new ArrayList<>();
		for(Entry<String, String[]> entry:httpRequest.getParameterMap().entrySet()) {
			String column = entry.getKey();
			if(column.startsWith("$")) {
				continue;
			}
			rt.add(column);
		}
		return rt;
	}
	private String getFilterExpr() {
		StringBuilder sb = new StringBuilder();
		List<String> filterColumn = getFilterColumn();
		if(filterColumn.size()==0) {
			return null;
		}
		for(String column:filterColumn) {
			String value = httpRequest.getParameter(column);
			sb.append(column).append("='").append(value).append("' and ");
		}
		sb.setLength(sb.length()-4);
		return sb.toString();
	}
	public List<T> search(BaseService<T,REPO> service) {
		
		Class<T> domainType = service.getDomainType();
		String tableName = JpaUtils.getTableName(domainType);
		StringBuilder sb = new StringBuilder();
		buildSelect(sb);
		sb.append(" from ").append(tableName);
		
		
		String filter = getFilterExpr();
		String fuzzSearch = buildFuzzSearch();
		if(filter!=null || fuzzSearch !=null) {
			sb.append(" where ");
		}
		if(filter!=null) {
			sb.append(filter);
		}
		if(fuzzSearch !=null) {
			if(filter!=null) {
				sb.append(" or ");
			}
			sb.append(fuzzSearch);
		}
		
		buildOrderby(sb);
		List<T> rt = sql.select(domainType, sb.toString());
		return rt;
	}

}
