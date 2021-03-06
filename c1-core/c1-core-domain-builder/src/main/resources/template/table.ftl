package com.next.c1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.next.jpatis.core.DefaultValue;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

<#assign ColumnTypeUtil=statics['compiler.ColumnTypeUtil']>


@SuppressWarnings("unused")
@Table(name="${data.id}")
public class Do${data.id}
{
	public static final String TABLE = "${data.id}";

    <#list data.column as column>
	<#if column.id == 'id'>
	@Id
	</#if>
	<#assign javaType=ColumnTypeUtil.getJavaType(column.dbType)>
	<#assign methodName=column.id?cap_first>
    @Column
	<#if column.defaultValue??>
	@DefaultValue("${column.defaultValue}")
	</#if>    
	protected ${javaType} ${column.id};
	public ${javaType} get${methodName}(){return ${column.id};}
	public void set${methodName}(${javaType} val){${column.id}=val;}
	public static final String ${column.id}_ALIAS = "${column.id}";
	<#list column.validValue as vvalue>
	public static final String ${column.id}_${ColumnTypeUtil.normalizeValidValue(vvalue.value)}_VAL = "${vvalue.id}";
    </#list>
    </#list>
    
	<#list data.array as array>
	@JsonProperty("${array.id}")
	protected List<Do${array.type}> ${array.id};
	//@ArrayTable
	@JsonIgnore	
	public List<Do${array.type}> get${array.id}() {
		if(${array.id}==null) ${array.id} = new ArrayList<>();
		return ${array.id};
	}
	@JsonIgnore
	public void set${array.id}(List<Do${array.type}> val) {
		${array.id}=val;
	}
	</#list>    
}
