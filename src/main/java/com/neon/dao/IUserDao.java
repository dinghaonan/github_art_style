package com.neon.dao;

import com.neon.entity.UserEntity;

public interface IUserDao {
	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	UserEntity getUserById(String userId);
}
