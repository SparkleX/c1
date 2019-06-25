package com.next.c1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.next.c1.reflect.FieldUtilsEx;
import com.next.c1.schema.table.Table;
import com.next.jpatis.spring.JpatisRepository;

public class BaseService<T,REPO extends JpatisRepository<T,Integer>> {
	
	@Autowired
	protected REPO repository;
	@Autowired
	MetadataService metadataService;

	public String getDescription(Integer id) {
		String table = this.getMdTable();
		Table metaTable = metadataService.getMetadata(table);
		String descCol = metaTable.getDescColumn();
		T data = repository.findById(id).get();
		String desc = (String) FieldUtilsEx.readField(data, descCol);
		return desc;
	}

	private String getMdTable() {
		return this.getClass().getSimpleName().substring(2);
	}

	public T get(Integer id) {
		return repository.findById(id).get();
	}
	public void create(T o) {
		repository.insert(o);;
	}
	public void update(Integer id, T entity) {
		repository.updateById(id, entity);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
		
	}

	public List<T> search() {
		return repository.findAll();
	}

}
