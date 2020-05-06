package com.xworkzcm.main.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xworkzcm.main.dto.UserDTO;
import com.xworkzcm.main.serviceapi.UserApi;

@Controller
@RequestMapping(value = "/user")
public class UserRegister {
	
	
	private static final Logger logger = Logger.getLogger(UserRegister.class);


	public UserRegister() {
		logger.info("Created   " + this.getClass().getSimpleName());
	}

	@Autowired
	UserApi userApi;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result, Model model,HttpServletRequest request) {
		
		String ipAddress=request.getRemoteAddr();
		userDTO.setIpAddress(ipAddress);
		if (result.hasErrors()) {
			return "Register";
		} else {

			try {
				com.xworkzcm.main.utils.Error error = userApi.validateUserByUserId(userDTO.getUserId());
				if (error.isSuccess()) {

				} else {
					model.addAttribute("message", error.getMessage());
					return "Register";

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				com.xworkzcm.main.utils.Error error = userApi.validateEmail(userDTO.getEmail());
				if (error.isSuccess()) {

					try {

						boolean valid = userApi.userRegister(userDTO);
						if (valid == true) {
							model.addAttribute("message", "Register Successfully");
							return "Register";

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					model.addAttribute("message", error.getMessage());
					return "Register";

				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

		return "Register";

	}

}
