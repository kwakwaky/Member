package com.webapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutContoller {
	
	static Log log = LogFactory.getLog(LogoutContoller.class);
	
	@RequestMapping("/logout")
	public  String logout(HttpSession session) {
		log.info("logout success ...");
		session.invalidate();
		
		return "redirect:/";
	}

	
}
