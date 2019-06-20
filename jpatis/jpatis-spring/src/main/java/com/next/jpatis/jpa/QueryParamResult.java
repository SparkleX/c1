package com.next.jpatis.jpa;

import java.util.ArrayList;
import java.util.List;

public class QueryParamResult 
{
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	public String getSqlWoParamName() {
		return sqlWoParamName;
	}
	public void setSqlWoParamName(String sqlWoParamName) {
		this.sqlWoParamName = sqlWoParamName;
	}
	List<String> params = new ArrayList<>();
	String sqlWoParamName;
	public boolean isNoName() {
		return noName;
	}
	private boolean noName;
	public void mappping() 
	{
		
	}
	public void setNoName(boolean val) {
		this.noName = val;
		
	}
}
