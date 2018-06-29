package com.neon.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.neon.entity.UserEntity;

/**
 * 
 * @Title: UserMapper.java
 * @Description: 用户Mapper
 * @author: dinghaonan
 * @date: 2018年6月9日 下午4:37:36
 * @version V1.0
 */
@Mapper
public interface UserMapper {

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	UserEntity getUserById(String userId);
}
