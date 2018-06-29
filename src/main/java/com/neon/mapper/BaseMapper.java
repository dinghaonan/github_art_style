package com.neon.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Title: UserMapper.java
 * @Description: 用户Mapper
 * @author: dinghaonan
 * @date: 2018年6月9日 下午4:37:36
 * @version V1.0
 */
@Mapper
public interface BaseMapper<T> {

	/**
	 * 注入sql 执行 查询方法
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> selectAll(@Param("sql") String sql);

	/**
	 * 注入sql 执行新增方法
	 * 
	 * @param sql
	 * @return
	 */
	int insertEntity(@Param("sql") String sql);
}
