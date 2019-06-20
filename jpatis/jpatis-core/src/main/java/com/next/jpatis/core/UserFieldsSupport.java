package com.next.jpatis.core;

import java.util.Map;

public interface UserFieldsSupport 
{
	public void setUserField(String field, Object value);
	Map<String, Object> getUserFields();
}
