package com.neon.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.neon.dao.IBaseDao;
import com.neon.mapper.BaseMapper;
import com.neon.util.MapConvertUtil;

public class BaseDaoImpl<T> implements IBaseDao {
	@Autowired
	private BaseMapper<T> mapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectAll() {
		final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		// 查询sql
		final StringBuffer sb = assembleSelectSql(entityClass);
		System.out.println(sb.toString());
		final List<Map<String, Object>> modelList = mapper.selectAll(sb.toString());
		final List<T> beanList = returnEntityListByClass(entityClass, modelList);
		return beanList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectListByColumn(final Map<String, Object> columnMaps) {
		final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		// 查询sql
		final StringBuffer sb = assembleSelectSql(entityClass);

		// 根据maps 获取查询条件
		final Iterator<Entry<String, Object>> iterator = columnMaps.entrySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			if (0 == i) {
				sb.append(" where ");
			}
			final Entry<String, Object> next = iterator.next();
			sb.append(" " + next.getKey() + " = " + next.getValue());
			i++;
		}
		System.out.println(sb.toString());
		final List<Map<String, Object>> modelList = mapper.selectAll(sb.toString());
		final List<T> beanList = returnEntityListByClass(entityClass, modelList);

		return beanList;
	}

	@Override
	public <T> String insetEntity(final T e) {

		StringBuffer sb = null;
		try {
			sb = assembleInsertSql(e);
		} catch (final Exception e1) {
			e1.printStackTrace();
		}
		mapper.insertEntity(sb.toString());
		return null;
	}

	// 根据类返回实体对象
	private List<T> returnEntityListByClass(final Class<T> entityClass, final List<Map<String, Object>> modelList) {
		final List<T> beanList = new ArrayList<T>();
		for (final Map<String, Object> map : modelList) {
			T bean = null;
			try {
				bean = entityClass.newInstance();
				BeanUtils.populate(bean, map);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
			beanList.add(bean);
		}
		return beanList;
	}

	// 根据类型拼接查询sql
	private StringBuffer assembleSelectSql(final Class<T> entityClass) {
		final Field[] fields = entityClass.getDeclaredFields();

		final StringBuffer sb = new StringBuffer("SELECT ");

		// 拼接数据库字段
		for (final Field field : fields) {
			final String column = field.getName();
			sb.append(" " + MapConvertUtil.switchParam(column) + " as " + column + ",");
		}
		// 去掉最后一个 ',' 号
		sb.deleteCharAt(sb.length() - 1);
		// 获取表名
		final String[] classArr = entityClass.getName().split("\\.");
		String tableName = classArr[classArr.length - 1];
		tableName = tableName.replace("Entity", "");
		tableName = tableName.substring(0, 1).toLowerCase() + tableName.substring(1);
		sb.append(" from " + MapConvertUtil.switchParam(tableName));
		return sb;
	}

	// 根据类型拼接新增sql
	private StringBuffer assembleInsertSql(final Object o) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// 获取表名
		final String[] classArr = o.getClass().getName().split("\\.");
		String tableName = classArr[classArr.length - 1];
		tableName = tableName.replace("Entity", "");

		final StringBuffer sb = new StringBuffer("INSERT INTO " + tableName);
		// 顺序存储字段
		final List<Field> columnsList = new ArrayList<Field>();

		sb.append("(");
		// 获取字段属性
		final Field[] fields = o.getClass().getDeclaredFields();
		for (final Field field : fields) {
			final String column = field.getName();
			columnsList.add(field);
			sb.append(" " + MapConvertUtil.switchParam(column) + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(") VALUES ( ");
		// 循环属性 获取属性内容
		for (final Field field : columnsList) {
			// 属性名
			final String column = field.getName();
			// 属性类型
			final String type = field.getGenericType().toString();

			final String getMethodColumn = column.substring(0, 1).toUpperCase() + column.substring(1);
			final Method method = o.getClass().getDeclaredMethod("get" + getMethodColumn);
			final Object value = method.invoke(o);
			// 获取到的属性内容是否为空 判断标识
			boolean isNull = true;
			if (null == value) {
				isNull = true;
			} else {
				isNull = false;
			}
			if (type.equals("String")) {

				if (isNull) {
					sb.append("'',");
				} else {
					sb.append("'" + value + "',");
				}

			} else if (type.equals("int") || type.equals("Integer")) {

				if (isNull) {
					sb.append("0,");
				} else {
					sb.append("" + value + ",");
				}

			} else if (type.equals("Short")) {

				if (isNull) {
					sb.append("0,");
				} else {
					sb.append("" + value + ",");
				}

			} else if (type.equals("Double")) {

				if (isNull) {
					sb.append("0,");
				} else {
					sb.append("" + value + ",");
				}

			} else {
				if (isNull) {
					sb.append("NULL,");
				} else {
					sb.append("'" + value + "',");
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(");");
		return sb;
	}
}
