package com.xworkzcm.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.xworkzcm.main.dto.LoginDTO;
import com.xworkzcm.main.dto.UpdateDTO;
import com.xworkzcm.main.dto.UserDTO;

@Controller
public class Main {

	@GetMapping("/")
	public String mainMethod() {
		return "Home";
	}

	@GetMapping("/register")
	public String registerHome(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
		Map<String, String> courseMap = new HashMap<String, String>();
		courseMap.put("Development", "Development");
		courseMap.put("Testing", "Testing");
		model.addAttribute("courses", courseMap);
		return "Register";
	}

	@GetMapping("/login")
	public String loginHome(@ModelAttribute("loginDto") LoginDTO loginDTO, Model model) {
		return "Login";

	}
	
	@GetMapping("/update")
	public String upadteHome(@ModelAttribute("updateDTO") UpdateDTO updateDTO, Model model) {
		return "Update";

	}
	
	@GetMapping("/home")
	public String homejsp() {
		return "Home";

	}

}
