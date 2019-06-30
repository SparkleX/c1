package com.next.c1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.next.c1.reflect.FieldUtilsEx;
import com.next.c1.repository.BaseRepository;
import com.next.c1.schema.table.Table;

public class BaseService<T,REPO extends BaseRepository<T,Integer>> {
	
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
		return this.getClass().getSimpleName().substring(0,4);
	}
	public T change(T data, String table, String column, Integer line) {
		return data;
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
	public Integer getNext(Integer id) {
		return repository.getNext(id);
	}
	public Integer getPrev(Integer id) {
		return repository.getPrev(id);
	}
	public Integer getFirst() {
		return repository.getFirst();
	}
	public Integer getLast() {
		return repository.getLast();
	}	
}
