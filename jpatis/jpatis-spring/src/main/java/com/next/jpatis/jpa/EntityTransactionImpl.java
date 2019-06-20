package com.next.jpatis.jpa;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityTransactionImpl implements EntityTransaction
{
	final static Logger logger  = LoggerFactory.getLogger(EntityTransactionImpl.class);
	private Connection conn;
	private boolean rollbackOnly = false;
	private boolean active;
	
	EntityTransactionImpl(Connection connection)
	{
		this.conn = connection;
	}	

	@Override
	public void begin()
	{
		try 
		{
			conn.setAutoCommit(false);
			active = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public void commit()
	{
		try 
		{
			if(rollbackOnly)
			{
				conn.rollback();
			}
			else
			{
				conn.commit();
			}
			conn.setAutoCommit(true);
			active = false;
		} catch (SQLException e) 
		{
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void rollback()
	{
		try 
		{
			conn.rollback();
			conn.setAutoCommit(true);
			active = false;
		} catch (SQLException e) 
		{
			logger.error(e.getMessage(), e);
		}		
	}

	@Override
	public void setRollbackOnly()
	{
		rollbackOnly = true;
	}

	@Override
	public boolean getRollbackOnly()
	{
		return rollbackOnly;
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

}
