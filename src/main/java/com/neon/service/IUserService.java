package com.neon.service;

import com.neon.bo.UserBo;

public interface IUserService {

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	UserBo getUserById(String userId);

}
