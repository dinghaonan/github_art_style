package com.neon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neon.bo.UserBo;
import com.neon.dao.IUserDao;
import com.neon.service.IUserService;
import com.neon.util.MapConvertUtil;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;

	@Override
	public UserBo getUserById(final String userId) {
		final UserBo bo = MapConvertUtil.reflectionAttr(dao.getUserById(userId), UserBo.class);
		return bo;
	}

}
