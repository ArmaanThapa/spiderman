package com.xworkzcm.main.UserController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xworkzcm.main.dto.LoginDTO;
import com.xworkzcm.main.serviceapi.UserLoginApi;
import com.xworkzcm.main.utils.Error;

@Controller
@RequestMapping("/user")
public class LoginController {

	public LoginController() {
		System.out.println("Created    " + this.getClass().getSimpleName());
	}

	@Autowired
	UserLoginApi userLoginApi;
	Error error = new Error();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@Valid @ModelAttribute("loginDto") LoginDTO loginDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {

			return "Login";
		} else {
			System.out.println(loginDTO.getEmail() + "and " + loginDTO.getPassword());
			error = userLoginApi.findUserByEmail(loginDTO.getEmail());
			if (error.isSuccess()) {
				error = userLoginApi.saveUserLogin(loginDTO);
				if (!(error.isSuccess())) {

					model.addAttribute("message", "wrong password");
					return "Login";
				}

				else {
					model.addAttribute("message", error.getMessage());
					return "Login";
				}

			} else {
				model.addAttribute("message", error.getMessage());
				return "Login";
			}

		}
	}

}
