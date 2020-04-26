package com.xworkzcm.main.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkzcm.main.daoapi.UserLoginDaoApi;
import com.xworkzcm.main.dto.LoginDTO;
import com.xworkzcm.main.entity.UserLoginEntity;
import com.xworkzcm.main.serviceapi.UserLoginApi;
import com.xworkzcm.main.utils.Error;

@Service
public class UserLoginServiceImpl implements UserLoginApi {

	public UserLoginServiceImpl() {
		System.out.println("Created  " + this.getClass().getSimpleName());
	}

	@Autowired
	UserLoginDaoApi daoApi;

	Error error = new Error();

	public Error saveUserLogin(LoginDTO loginDTO) {
		UserLoginEntity entity = new UserLoginEntity();
		BeanUtils.copyProperties(loginDTO, entity);
		error = daoApi.checkPassword(entity);
		return error;
	}

	public Error findUserByEmail(String email) {
		try {
			error = daoApi.loginDao(email);
			return error;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;

	}

}
