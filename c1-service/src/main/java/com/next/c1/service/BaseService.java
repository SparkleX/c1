package com.next.c1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import com.next.c1.reflect.FieldUtilsEx;
import com.next.c1.repository.BaseRepository;
import com.next.c1.repository.ORDRRepository;
import com.next.c1.schema.table.Array;
import com.next.c1.schema.table.Table;
import com.next.jpatis.core.JpatisUtils;

public class BaseService<T,REPO extends BaseRepository<T,Integer>> {
	
	@Autowired
	protected REPO repository;
	@Autowired
	MetadataService metadataService;
	@Autowired
	ApplicationContext appContext;
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
	
	protected Table getMetadata() {
		String table = this.getMdTable();
		Table metaTable = metadataService.getMetadata(table);
		return metaTable;
	}
	
	@SuppressWarnings("unchecked")
	public T get(Integer id) {
		T rt = repository.findById(id).get();
		Table metaTable = getMetadata();
		for(Array array:metaTable .getArray()) {
			BaseRepository<?, Integer> baseRepo = (BaseRepository<?, Integer>) getRepository(array.getType());
			List<?> ar = baseRepo.findByParentId(id);
			FieldUtilsEx.writeField(rt, array.getId(), ar);
		}
		return rt;
	}
	
	protected BaseRepository<?,?> getRepository(String tableName) {
		String className = ORDRRepository.class.getName();
		String newClassName = className.replaceAll("ORDR", tableName);
		Class<?> clazz;
		try {
			clazz = Class.forName(newClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return (BaseRepository<?, ?>) appContext.getBean(clazz);
	}
	@SuppressWarnings("unchecked")
	public T init() {
		Class<T> clazz = (Class<T>) GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseService.class)[0];
		T rt;
		try {
			rt = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		JpatisUtils.initWithDefault(rt);
		return rt;
	}	
	@SuppressWarnings("unchecked")
	public void create(T o) {
		Integer id = this.repository.newId();
		FieldUtilsEx.writeField(o, "id", id);
		repository.insert(o);;
		Table metaTable = getMetadata();
		for(Array metaArray:metaTable .getArray()) {
			List<?> oArray = (List<?>) FieldUtilsEx.readField(o, metaArray.getId());
			if(oArray==null) {
				continue;
			}
			BaseRepository<Object, Integer> baseRepo = (BaseRepository<Object, Integer>) getRepository(metaArray.getType());
			for(Object line:oArray) {
				Integer newId = baseRepo.newId();
				FieldUtilsEx.writeField(line, "parentId", id);
				FieldUtilsEx.writeField(line, "id", newId);
				baseRepo.insert(line);
			}			
		}	
		
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
