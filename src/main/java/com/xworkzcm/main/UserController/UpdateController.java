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

import com.xworkzcm.main.dto.UpdateDTO;
import com.xworkzcm.main.entity.UpdateEntity;
import com.xworkzcm.main.serviceapi.UserApi;
import com.xworkzcm.main.utils.Error;

@Controller
@RequestMapping(value = "user")
public class UpdateController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	public UpdateController() {
		logger.info(this.getClass().getSimpleName());
	}

	@Autowired
	UserApi userApi;

	Error error = new Error();

	@RequestMapping(value = "/update")
	public String updateUser(@Valid @ModelAttribute("updateDTO") UpdateDTO updateDTO, BindingResult result,
			HttpServletRequest request, Model model) {
		String ipAddress = request.getRemoteAddr();
		updateDTO.setIpAddress(ipAddress);

		if (result.hasErrors()) {
			return "Update";

		}

		else {
			error = userApi.findUserByEmail(updateDTO);
			logger.info(error.isSuccess() + "insider controller class");
			if (error.isSuccess()) {
				logger.info("email found in update controller");
				UpdateEntity updateEntity = userApi.setPassword(updateDTO);
				if (updateDTO != null) {
					model.addAttribute("message", updateEntity.getPassword());
					return "Update";
				}

			} else {
				logger.info("inde user not found controller");
				model.addAttribute("message", error.getMessage());
				return "Update";
			}

		}

		return "Update";

	}

}
