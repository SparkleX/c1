package com.next.jpatis.jpa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class QueryParser 
{
	Pattern pattern = Pattern.compile(":[a-zA-Z0-9]+");
	public QueryParamResult parse(String sql)
	{
		QueryParamResult rt = new QueryParamResult();
		Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) 
        {
        	String name = matcher.group().substring(1);
        	rt.getParams().add(name);
        }
        String out = matcher.replaceAll("?");
        rt.setSqlWoParamName(out);
        
        if(out.equals(sql))
        {
        	int count = StringUtils.countMatches(sql, '?');
        	for(int i=0;i<count;i++)
        	{
        		rt.getParams().add(null);	
        	}
        	rt.setNoName(true);
        }
        rt.mappping();
		return rt;
	}
}
