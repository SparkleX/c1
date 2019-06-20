package com.next.jpatis.core;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JpatisTest {
	Connection conn;
    @Before
    public void testBeforeClass() throws Exception{
    	Class.forName("org.h2.Driver");
    	conn = DriverManager.getConnection("jdbc:h2:mem:test");
    	Statement stat = conn.createStatement();
    	stat.execute("CREATE TABLE a(a integer NOT NULL,b varchar(25), c TIMESTAMP, d decimal(21,8), primary key (a));");
    	stat.execute("insert into a(a,b,c,d) values(1,'a',null,null)");
    }
	@Test
	public void test() {
		SqlConnection sqlConnection = new OrmAccess(conn);
		List<A> aList = sqlConnection.selectAll(A.class);
		assertTrue(aList.size()==1);
	}

}
