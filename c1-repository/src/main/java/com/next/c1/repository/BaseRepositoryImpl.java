package com.next.c1.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.transaction.annotation.Transactional;

import com.next.jpatis.core.JpaUtils;
import com.next.jpatis.core.SqlConnection;
import com.next.jpatis.spring.JpatisRepositoryImpl;

@Transactional
public class BaseRepositoryImpl<T,ID> extends JpatisRepositoryImpl<T,ID> implements BaseRepository<T,ID> {
	private String tableName;

	public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.tableName = JpaUtils.getTableName(super.type);
	}
	@SuppressWarnings("unchecked")
	public ID getFirst() {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select min(id) from %s", tableName));
		return (ID) rt.get(0)[0];
	}
	@SuppressWarnings("unchecked")
	public ID getLast() {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select max(id) from %s", tableName));
		return (ID) rt.get(0)[0];
	}
	@SuppressWarnings("unchecked")
	public ID getNext(ID id) {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select min(id) from %s where id>?", tableName), id);
		return (ID) rt.get(0)[0];
	}	
	@SuppressWarnings("unchecked")
	public ID getPrev(ID id) {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select max(id) from %s where id<?", tableName),id);
		return (ID) rt.get(0)[0];
	}
}