package com.webapp.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleController {
	
	@Autowired
	LocaleResolver localeResolver;
	
	@RequestMapping("/locale/{language:[a-z][a-z]}") // 정규표현식 : 소문자 2자리만
	public void setLocale(HttpServletRequest request,
						  HttpServletResponse response,
						  PrintWriter out,
						  @PathVariable String language) { // 어노테이션 PathVariable 안 줄경우(디폴트) 파라미터로 language를 찾는다.
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		out.println("locale changed = " + locale.getLanguage());
	}

}
