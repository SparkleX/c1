package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="${name}")
public class Bmo${name}
{
    <#list column as column>
	<#if isKey(column.name)>
	@Id
	</#if>
    @Column
	public ${column.javaType} get${column.name}(){return ${column.name};}
	public void set${column.name}(${column.javaType} val){${column.name}=val;}
	${column.javaType} ${column.name};
    </#list>
}
