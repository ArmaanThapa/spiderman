package com.xworkzcm.main.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
		logger.info("created" + this.getClass().getSimpleName());
	}

	@Autowired
	UserDaoApi userDaoApi;

	@Autowired
	private MailSender mailSender;

	Error error = new Error();

	public boolean userRegister(UserDTO userDTO) {

		userDTO.setNoOfAttemp(0);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		UserEntity userEntity = new UserEntity();
		String randomPassword = PasswordGeneration.generatePassword(8);
		logger.info("password    " + randomPassword);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(randomPassword);
		userEntity.setPassword(encodePassword);
		logger.info("randomPassword  " + randomPassword);
		BeanUtils.copyProperties(userDTO, userEntity);
		try {

			userDaoApi.saveRegister(userEntity);
			if (passwordEncoder.matches(randomPassword, encodePassword)) {
				error.setSuccess(true);
				logger.info("inside Email Api");
				logger.info(userEntity.getEmail());
				logger.info(randomPassword);
				mailMessage.setTo(userEntity.getEmail());
				mailMessage.setSubject("Regarding Registration  &  password");
				mailMessage.setText("Registration is successful and your password is:" + randomPassword
						+ "\t do not share your password with anyone." + "Thank you & Enjoy Our Service");

				try {
					mailSender.send(mailMessage);
					logger.info("E-mail Sending is successful");
					return true;
				} catch (Exception e) {
					logger.error(e.getMessage(), e);

				}

			}

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

		Error error = userDaoApi.findUserByEmailID(updateDTO);
		logger.info(error.isSuccess());

		return error;

	}

	public UpdateEntity setPassword(UpdateDTO updateDTO) {

		UserEntity userEntity = new UserEntity();
		UpdateEntity updateEntity = new UpdateEntity();
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		String randomPassword = PasswordGeneration.generatePassword(8);
		updateEntity.setPassword(randomPassword);

		logger.info("password    " + randomPassword);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(randomPassword);
		userEntity.setPassword(encodePassword);
		BeanUtils.copyProperties(updateDTO, userEntity);

		try {
			error = userDaoApi.passwordUpdate(userEntity);
			if (error.isSuccess()) {

				error.setSuccess(true);

				logger.info("inside Email Api");

				mailMessage.setTo(userEntity.getEmail());
				mailMessage.setSubject("Regarding Registration  &  password");
				mailMessage.setText("Registration is successful and your password is:" + randomPassword
						+ "\t do not share your password with anyone." + "Thank you & Enjoy Our Service");
				mailSender.send(mailMessage);
				logger.info("E-mail Sending is successful");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateEntity;
	}

}
