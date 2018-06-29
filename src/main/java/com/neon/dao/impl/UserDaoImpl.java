package com.neon.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neon.dao.IUserDao;
import com.neon.entity.UserEntity;
import com.neon.mapper.UserMapper;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements IUserDao {
	@Autowired
	private UserMapper mapper;

	@Override
	public UserEntity getUserById(final String userId) {

		final Map<String, Object> columnMaps = new HashMap<String, Object>();
		columnMaps.put("USER_ID", "1");

		final List<UserEntity> list = selectListByColumn(columnMaps);
		final UserEntity user = new UserEntity();
		user.setUserId(4);
		user.setUserName("名称");
		user.setUserCode("1");
		this.insetEntity(user);
		return mapper.getUserById(userId);
	}

}
