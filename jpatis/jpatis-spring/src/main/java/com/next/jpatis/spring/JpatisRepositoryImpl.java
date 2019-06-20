package com.next.jpatis.spring;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.next.jpatis.core.SqlConnection;

@Repository
@Transactional
public class JpatisRepositoryImpl<T, ID> implements JpatisRepository<T, ID>, JpaRepositoryImplementation<T, ID> {
	Class<T> type;
	Class<ID> idType;
	private EntityManager em;
	public JpatisRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		type = entityInformation.getJavaType();
		idType = entityInformation.getIdType();
		this.em = entityManager;
	}

	@Override
	public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
	}

	private SqlConnection getSqlConnection() {
		return em.unwrap(SqlConnection.class);
	}

	@Override
	public void insert(T o) {
		getSqlConnection().insert(o);
	}
	@Override
	public void update(T entity) {
		getSqlConnection().update(entity);		
	}
	@Override
	public void updateById(ID id, T entity) {
		getSqlConnection().updateById(id, entity);
	}

	@Override
	public void delete(T o) {
		getSqlConnection().delete(o);

	}

	@Override
	public void deleteById(ID id) {
		getSqlConnection().deleteById(id, type);

	}
	@Override
	public List<T> findAll() {
		List<T> rt = getSqlConnection().selectAll(type);
		return rt;
	}

	@Override
	public List<T> findAll(Sort sort) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAllById(Iterable<ID> ids) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flush() {
		throw new UnsupportedOperationException();

	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteAllInBatch() {
		throw new UnsupportedOperationException();

	}

	@Override
	public T getOne(ID id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> S save(S entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<T> findById(ID id) {
		return this.getSqlConnection().findById(type, id);
	}

	@Override
	public boolean existsById(ID id) {
		return this.findById(id).isPresent();
		
	}

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}


	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();

	}

	@Override
	public <S extends T> Optional<S> findOne(Example<S> example) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<T> findOne(Specification<T> spec) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long count(Specification<T> spec) {
		throw new UnsupportedOperationException();
	}


}
