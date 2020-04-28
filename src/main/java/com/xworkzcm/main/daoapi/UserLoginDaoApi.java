package com.xworkzcm.main.daoapi;

import com.xworkzcm.main.entity.UserLoginEntity;
import com.xworkzcm.main.utils.Error;

public interface UserLoginDaoApi {

	Error loginDao(String email);

	Error checkPassword(UserLoginEntity entity);

}
