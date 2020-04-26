package com.xworkzcm.main.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkzcm.main.daoapi.UserDaoApi;
import com.xworkzcm.main.dto.UserDTO;
import com.xworkzcm.main.entity.UserEntity;
import com.xworkzcm.main.serviceapi.UserApi;
import com.xworkzcm.main.utils.Error;
import com.xworkzcm.main.utils.PasswordGeneration;

@Service
public class UserServiceImpl implements UserApi {

	public UserServiceImpl() {
		System.out.println("created" + this.getClass().getSimpleName());
	}

	@Autowired
	UserDaoApi userDaoApi;

	Error error = new Error();

	public boolean userRegister(UserDTO userDTO) {

		userDTO.setNoOfAttemp(0);

		UserEntity userEntity = new UserEntity();
		String randomPassword = PasswordGeneration.generatePassword(8);
		userEntity.setPassword(randomPassword);

		System.out.println("randomPassword  " + randomPassword);

		BeanUtils.copyProperties(userDTO, userEntity);

		try {

			boolean valid = userDaoApi.saveRegister(userEntity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public Error validateUserByUserId(String userId) {

		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userId);

		try {

			error = userDaoApi.findByUserId(userEntity.getUserId());
			return error;

		} catch (Exception e) {

		}
		return error;
	}

	public Error validateEmail(String email) {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(email);

		try {
			error = userDaoApi.findUserByEmail(userEntity.getEmail());
			return error;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return error;
	}

}
