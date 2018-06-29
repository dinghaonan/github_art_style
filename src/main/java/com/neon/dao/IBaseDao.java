package com.neon.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {

	/**
	 * 查询全部集合
	 * 
	 * @return
	 */
	List<?> selectAll();

	/**
	 * 根据条件查询集合
	 * 
	 * @param columnMaps
	 *            <数据库字段,条件内容>
	 * @return
	 */
	List<?> selectListByColumn(Map<String, Object> columnMaps);

	/**
	 * 新增数据
	 * 
	 * @param <T>
	 * 
	 * @param t
	 *            实体
	 * @return
	 */
	<T> String insetEntity(T e);
}
