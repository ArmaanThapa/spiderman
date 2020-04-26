package com.xworkzcm.main.serviceapi;

import com.xworkzcm.main.dto.UserDTO;
import com.xworkzcm.main.utils.Error;

public interface UserApi {

	boolean userRegister(UserDTO userDTO);

	Error validateUserByUserId(String userId);

	Error validateEmail(String email);

}
