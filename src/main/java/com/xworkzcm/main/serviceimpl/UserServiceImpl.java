package com.xworkzcm.main.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xworkzcm.main.daoapi.UserDaoApi;
import com.xworkzcm.main.dto.UpdateDTO;
import com.xworkzcm.main.dto.UserDTO;
import com.xworkzcm.main.entity.UpdateEntity;
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
		System.out.println("password    " + randomPassword);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(randomPassword);

		userEntity.setPassword(encodePassword);

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

	public Error findUserByEmail(UpdateDTO updateDTO) {
		
		
		Error error=userDaoApi.findUserByEmailID(updateDTO);
		System.out.println(error.isSuccess());
		
		
		return error;
		
		
	}

	public UpdateEntity setPassword(UpdateDTO updateDTO) {
		
		UserEntity userEntity=new UserEntity();
		UpdateEntity updateEntity=new UpdateEntity();
		
		String randomPassword = PasswordGeneration.generatePassword(8);
		updateEntity.setPassword(randomPassword);
		
		System.out.println("password    " + randomPassword);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(randomPassword);
		userEntity.setPassword(encodePassword);
		BeanUtils.copyProperties(updateDTO, userEntity);
		
		try {
			error=userDaoApi.passwordUpdate(userEntity);
			if(error.isSuccess())
			{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateEntity;
	}

}
