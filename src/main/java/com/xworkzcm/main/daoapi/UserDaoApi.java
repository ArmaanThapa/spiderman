package com.xworkzcm.main.daoapi;

import com.xworkzcm.main.dto.UpdateDTO;
import com.xworkzcm.main.entity.UpdateEntity;
import com.xworkzcm.main.entity.UserEntity;
import com.xworkzcm.main.utils.Error;

public interface UserDaoApi {

	boolean saveRegister(UserEntity userEntity);

	Error findByUserId(String userId);

	Error findUserByEmail(String email);

	Error findUserByEmailID(UpdateDTO updateDTO);

	Error passwordUpdate(UserEntity userEntity);

}
