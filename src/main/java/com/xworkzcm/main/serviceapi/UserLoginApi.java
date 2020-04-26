package com.xworkzcm.main.serviceapi;

import com.xworkzcm.main.dto.LoginDTO;
import com.xworkzcm.main.utils.Error;

public interface UserLoginApi {

	Error saveUserLogin(LoginDTO loginDTO);

	Error findUserByEmail(String email);

}
