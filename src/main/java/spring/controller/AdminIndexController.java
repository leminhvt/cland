package spring.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.model.bean.User;
import spring.model.dao.UserDAO;

@Controller
public class AdminIndexController {
	@Autowired
	private UserDAO userDAO;
	
	
	
	@GetMapping("admincp")
	public String index(Principal principal,HttpSession session) {
		return "cland.admin.index.index";
	}

}
