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
	public ID getLast() {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select max(id) from %s", tableName));
		return getSeekResult(rt);
	}
	public ID getNext(ID id) {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select min(id) from %s where id>?", tableName), id);
		return getSeekResult(rt);
	}
	public ID getPrev(ID id) {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select(String.format("select max(id) from %s where id<?", tableName),id);
		return getSeekResult(rt);
	}
	@SuppressWarnings("unchecked")
	public ID getSeekResult(List<Object[]> rt) {
		if(rt.size()==0) {
			return null;
		}
		return (ID) rt.get(0)[0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ID newId() {
		SqlConnection conn = getSqlConnection();
		List<Object[]> rt = conn.select("select "+tableName+"_S.nextval from dual");
		return (ID) rt.get(0)[0];
	}
	@Override
	public List<T> findByParentId(ID parentId) {
		SqlConnection conn = getSqlConnection();
		List<T> rt = conn.select(super.type, "select * from "+tableName+" where parentId = ?",parentId);
		return rt;
	}
}