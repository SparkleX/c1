package com.next.jpatis.core;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
final public class OrmAccess extends JdbcAccess implements SqlConnection {
	public OrmAccess(Connection conn) {
		super(conn);
	}

	public StringBuilder appendFieldName(StringBuilder sb, String field) {
		return sb.append(field);
	}

	public void fieldListSql(Object o, StringBuilder fieldList, StringBuilder paramList, ArrayList<Object> values,
			boolean forUpdate) throws JpatisException {
		try {
			Class<?> objectClass = o.getClass();
			boolean moveLast = false;
			List<Field> props = JpaUtils.getFields(objectClass);
			for (Field field : props) {
				String fieldName = JpaUtils.getFieldName(field);
				moveLast = true;
				if (forUpdate) {
					appendFieldName(fieldList, fieldName).append("=?,");
				} else {
					appendFieldName(fieldList, fieldName).append(",");
					paramList.append("?,");
				}
				Object value = field.get(o);
				values.add(value);
			}
			if (this.isSupportUserDefinedField(o.getClass())) {
				UserFieldsSupport udfObject = (UserFieldsSupport) o;
				for (Entry<String, Object> entry : udfObject.getUserFields().entrySet()) {
					String fieldName = entry.getKey();

					moveLast = true;
					if (forUpdate) {
						appendFieldName(fieldList, fieldName).append("=?,");
					} else {
						appendFieldName(fieldList, fieldName).append(",");
						paramList.append("?,");
					}
					values.add(entry.getValue());
				}
			}
			if (moveLast)
				fieldList.setLength(fieldList.length() - 1);
			if (forUpdate == false)
				paramList.setLength(paramList.length() - 1);
		} catch (Exception ex) {
			throw new JpatisException(ex);
		}
	}

	public OrmAccessParam insertSql(Object o) {
		OrmAccessParam rt = new OrmAccessParam();
		StringBuilder sql = new StringBuilder();
		String tableName = JpaUtils.getTableName(o.getClass());
		String quotedTableName = this.quote(tableName);
		sql.append("insert into ").append(quotedTableName).append("(");

		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		fieldListSql(o, s1, s2, rt.param, false);
		sql.append(s1);

		sql.append(") values (");
		sql.append(s2);
		/*
		 * if(values.length() == 0) { throw new SQLException("No fields to insert"); }
		 * values.setLength(values.length()-1); sql.append(values);
		 */
		sql.append(")");
		rt.sql = sql.toString();
		return rt;
	}

	private String quote(String tableName) {
		return new StringBuilder().append(tableName).toString();
	}

	public OrmAccessParam updateSql(Object entity, OrmAccessParam rtWhere) {

		OrmAccessParam rt = new OrmAccessParam();
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(JpaUtils.getTableName(entity.getClass()));

		sql.append(" set ");
		StringBuilder s1 = new StringBuilder();
		fieldListSql(entity, s1, null, rt.param, true);

		sql.append(s1);
		rt.sql = sql.toString() + " " + rtWhere.sql;
		rt.param.addAll(rtWhere.param);
		return rt;
	}
	public OrmAccessParam sqlWhereById(Class<?> clazz, Object id) throws JpatisException {
		try {
			OrmAccessParam rt = new OrmAccessParam();
			rt.param = new ArrayList<>();
			IdClass aIdClass = clazz.getAnnotation(IdClass.class);

			if (aIdClass == null) {
				rt.param.add(id);
			}
			StringBuilder sql = new StringBuilder();
			sql.append("where ");
			List<Field> fields = JpaUtils.getFields(clazz);
			for (Field field : fields) {
				
				Id aid = field.getAnnotation(Id.class);
				if (aid == null) {
					continue;
				}
				String column = JpaUtils.getFieldName(field);
				this.appendFieldName(sql, column).append("=? and ");
				if (aIdClass != null) {
					Class<?> idClass = aIdClass.value();
					Field f = idClass.getDeclaredField(field.getName());
					f.setAccessible(true);
					Object value = f.get(id);
					rt.param.add(value);
				}
			}
			sql.setLength(sql.length() - 4);
			rt.sql = sql.toString();
			return rt;
		} catch (Exception ex) {
			throw new JpatisException(ex);
		}
	}
	public OrmAccessParam sqlWhereByEntity(Class<?> clazz, Object entity) throws JpatisException {
		try {
			OrmAccessParam rt = new OrmAccessParam();
			rt.param = new ArrayList<>();
			/*IdClass aIdClass = clazz.getAnnotation(IdClass.class);

			if (aIdClass == null) {
				rt.param.add(object);
			}*/
			StringBuilder sql = new StringBuilder();
			sql.append("where ");
			List<Field> fields = JpaUtils.getFields(clazz);
			for (Field field : fields) {
				
				Id id = field.getAnnotation(Id.class);
				if (id == null) {
					continue;
				}
				String column = JpaUtils.getFieldName(field);
				this.appendFieldName(sql, column).append("=? and ");
				Object value = field.get(entity);
				rt.param.add(value);
				/*if (aIdClass != null) {
					Class<?> idClass = aIdClass.value();
					Field field = idClass.getDeclaredField(prop.getName());
					field.setAccessible(true);
					Object value = field.get(object);
					rt.param.add(value);
				}*/
			}
			sql.setLength(sql.length() - 4);
			rt.sql = sql.toString();
			return rt;
		} catch (Exception ex) {
			throw new JpatisException(ex);
		}
	}

	/*
	 * public OrmAccessParam whereSqlByKey(Object o) throws Exception { String
	 * tableName = getTableName(o.getClass()); MetaTable metaTable =
	 * this.env.getMetaTable(tableName); OrmAccessParam rt = new OrmAccessParam();
	 * StringBuilder sql = new StringBuilder(); sql.append("where ");
	 * 
	 * for(String column:metaTable.getPrimaryKey().getColumns()) {
	 * this.appendFieldName(sql, column).append("=? and "); MetaColumn metaCol =
	 * metaTable.getColumns().getColumn(column); Object value =
	 * MoUtil.getProperty(o, metaCol.getColumnName()); rt.param.add(value); }
	 * sql.setLength(sql.length()-4); rt.sql = sql.toString(); return rt; }
	 */

	// @Override
	public void insert(Object entity) throws JpatisException {
		OrmAccessParam p = insertSql(entity);
		super.exec(p.sql, p.param.toArray());
	}

	// @Override
	public void update(Object entity) throws JpatisException {
		OrmAccessParam p = sqlWhereByEntity(entity.getClass(), entity);

		p = updateSql(entity, p);
		super.exec(p.sql, p.param.toArray());
		if (super.getUpdateCount() == 0) {
			throw new JpatisException("No record updated");
		}
	}

	@Override
	public void updateById(Object id, Object entity) {
		OrmAccessParam p = sqlWhereById(entity.getClass(), id);
		p = updateSql(entity, p);
		super.exec(p.sql, p.param.toArray());
		if (super.getUpdateCount() == 0) {
			throw new JpatisException("No record updated");
		}
	}

	@Override
	public void delete(Object o) {
		OrmAccessParam p = sqlWhereByEntity(o.getClass(), o);
		p.sql = "delete from " + JpaUtils.getTableName(o.getClass()) + " " + p.sql;
		super.exec(p.sql, p.param.toArray());
	}
	
	@Override
	public void deleteById(Object id, Class<?> entityClass) {
		OrmAccessParam p = sqlWhereById(entityClass, id);
		p.sql = "delete from " + JpaUtils.getTableName(entityClass) + " " + p.sql;
		super.exec(p.sql, p.param.toArray());
		
	}	
	@Override
	public <T> Optional<T> findById(Class<T> clazz, Object id) {
		OrmAccessParam orm;
		orm = sqlWhereById(clazz, id);
		String tableName = JpaUtils.getTableName(clazz);
		String sql = "select * from " + this.quote(tableName) + " " + orm.sql;
		Optional<T> rt = loadOneEx(clazz, false, sql, orm.param.toArray());
		return rt;
	}

	@Override
	public <T> ArrayList<T> selectAll(Class<T> cls) throws JpatisException {
		String tableName = JpaUtils.getTableName(cls);
		return load(cls, "select * from " + this.quote(tableName));
	}

	public <T> Optional<T> loadOneEx(Class<T> cls, boolean lock, String sql, Object... values)
			throws JpatisException {
		ArrayList<T> rt = loadEx(cls, lock, sql, values);
		if (rt.size() == 0) {
			return Optional.empty();
		}
		return Optional.of(rt.get(0));
	}

	// @Override
	public <T> Optional<T> loadOne(Class<T> cls, String sql, Object... values) throws JpatisException {
		return loadOneEx(cls, false, sql, values);
	}

	// @Override
	public <T> ArrayList<T> load(Class<T> cls, String sql, Object... values) throws JpatisException {
		return (ArrayList<T>) loadEx(cls, false, sql, values);
	}

	private boolean isSupportUserDefinedField(Class<?> clz) {
		return UserFieldsSupport.class.isAssignableFrom(clz);
	}


	List<Object[]> loadEx(boolean lock, String sql, Object... values) throws JpatisException {
		try {
			List<Object[]> rt = new ArrayList<Object[]>();

			if (lock)
				sql += " for update ";

			sql = normalizeSql(sql);

			ResultSet rs = query(sql, values);
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					Object value = normallizeDataType(rs, i);
					row[i - 1] = value;
				}
				rt.add(row);
			}
			return rt;
		} catch (Exception ex) {
			throw new JpatisException(ex);
		}
	}



	List<Field> getColumnMapping(Class<?> cls, ResultSet rs) throws JpatisException {
		try {
			ArrayList<Field> rt = new ArrayList<>();
			HashMap<String, Field> map = new HashMap<>();
			List<Field> descs = JpaUtils.getFields(cls);

			for (Field field : descs) {
				String colName = JpaUtils.getFieldName(field).toUpperCase();
				map.put(colName, field);
			}
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				String dbName = rs.getMetaData().getColumnName(i);
				Field d = map.get(dbName.toUpperCase());
				if (d == null) {
					if (dbName.startsWith("U_") == false) {
						log.error(String.format("Column %s is not mapped", dbName));
					}
				} else {
					d.setAccessible(true);
				}
				rt.add(d);
			}
			return rt;
		} catch (SQLException ex) {
			throw new JpatisException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> ArrayList<T> loadEx(Class<T> cls, boolean lock, String sql, Object... values) throws JpatisException {
		try {
			List<Object[]> rows = loadEx(lock, sql, values);
			if (cls == null || cls == Object[].class) {
				return (ArrayList<T>) rows;
			}
			ResultSet rs = super.getResultSet();
			boolean supportUdf = isSupportUserDefinedField(cls);
			ArrayList<T> rt = new ArrayList<T>();
			int columnCount = rs.getMetaData().getColumnCount();

			List<Field> map = getColumnMapping(cls, rs);

			for (Object[] row : rows) {
				T o = this.newObject(cls);
				for (int i = 1; i <= columnCount; i++) {
					Object value = row[i - 1];
					Field desc = map.get(i - 1);
					if (desc != null) {
						try {
							desc.set(o, value);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new RuntimeException(e);
						}
					} else {
						if (supportUdf) {
							UserFieldsSupport dmo = (UserFieldsSupport) o;
							String colName = rs.getMetaData().getColumnLabel(i);
							dmo.setUserField(colName, value);
						} else {
							throw new RuntimeException();
						}
					}
				}
				rt.add(o);
			}
			return rt;
		} catch (SQLException ex) {
			throw new JpatisException(ex);
		}
	}

	private String normalizeSql(String sql) {
		// SqlParser parser = new SqlParser();
		// SelectStmtContext stmt = parser.parse(sql);
		// String normalSql = parser.normalize(stmt);
		return sql;

	}

	private Object normallizeDataType(ResultSet rs, int col) throws JpatisException {
		try {
			Object value = rs.getObject(col);
			if (value == null)
				return null;
			Class<?> clazz = value.getClass();

			if (clazz == byte[].class) {
				return null;
			}
			if (clazz == String.class || clazz == Timestamp.class || clazz == Integer.class
					|| clazz == BigDecimal.class) {
				return value;
			}
			if (clazz == Short.class || clazz == Long.class) {
				return new Integer(value.toString());
			}

			if (java.sql.Clob.class.isAssignableFrom(clazz)) {
				return rs.getString(col);
			}
			if (java.sql.Blob.class.isAssignableFrom(clazz)) {
				return rs.getString(col);
			}
			throw new RuntimeException(String.format("Unsupported Type %s ", clazz.getName()));
		} catch (SQLException ex) {
			throw new JpatisException(ex);
		}

	}

	public boolean isObjectLinked(Object o) throws Exception {
		return o.getClass().getName().contains("$$");
	}

	private <T> T newObject(Class<T> cls) {
		try {
			T o = cls.newInstance();
			return o;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}

	public void close() throws SQLException {
		super.close();
	}

	// @Override
	public void insertAll(List<?> array) throws Exception {
		for (Object o : array) {
			this.insert(o);
		}
	}

	// @Override
	public void deleteAll(List<?> list) throws Exception {
		for (Object o : list) {
			this.delete(o);
		}
	}

	/*
	 * @Override public List<Object[]> load(OrmQueryBuilder qb, Object ...param)
	 * throws Exception { return this.load(Object[].class, qb.toString(), param);
	 * 
	 * }
	 */
	/*
	 * public void sqlSelectAll(StringBuilder sql, MetaColumnCollection columns)
	 * throws Exception { for (MetaColumn field : columns) { this.quote(sql,
	 * field.getColumnName()); sql.append(","); } sql.setLength(sql.length() - 1); }
	 */
	/*
	 * private void quote(StringBuilder sql, String name) {
	 * sql.append("\"").append(name).append("\""); }
	 */
	// @Override
	public <T> List<T> batchLoadByKey(Class<T> clazz, Set<Object> keys) throws Exception {
		StringBuilder sql = new StringBuilder();
		String tableName = JpaUtils.getTableName(clazz);
		sql.append("select *");
		sql.append(" from ").append(quote(tableName));
		sql.append(" where ");

		String colName = this.getIdColumns(clazz).get(0);
		sql.append("\"").append(colName).append("\" in (");
		for (int i = 0; i < keys.size(); i++) {
			sql.append("?,");
		}
		sql.setLength(sql.length() - 1);
		sql.append(")");

		Object[] keyArray = keys.toArray(new Object[keys.size()]);
		return this.load(clazz, sql.toString(), keyArray);
	}

	private List<String> getIdColumns(Class<?> clazz) throws Exception {
		List<String> rt = new ArrayList<String>();
		List<Field> props = JpaUtils.getFields(clazz);
		for (Field prop : props) {
			Id aId = prop.getAnnotation(Id.class);
			if (aId != null) {
				Column aColumn = prop.getAnnotation(Column.class);
				rt.add(aColumn.name());
			}
		}
		if (rt.size() == 0) {
			throw new Exception(String.format("no @Id defined for class %s ", clazz.getName()));
		}

		return rt;
	}

	// @Override
	public <T> T loadExistsOne(Class<T> clazz) throws Exception {
		ArrayList<T> list = this.selectAll(clazz);
		if (list.size() != 1) {
			throw new RuntimeException();
		}
		return list.get(0);
	}

	// @Override
	public List<Object[]> load(String qb, Object... param) throws Exception {
		return this.load(Object[].class, qb.toString(), param);
	}

	@Override
	public <T> List<T> select(Class<T> sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}






	/*
	 * @Override public <T> List<T> load(Class<T> cls, OrmQueryBuilder qb, Object...
	 * values) throws Exception { String sql = qb.toString(); return this.load(cls,
	 * sql, values); }
	 */

}
