package com.webapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.exception.AlreadyExistingMemberException;
import com.webapp.exception.MemberNotFoundException;
import com.webapp.model.Member;



public class MemberRegisterServiceTest {
	
	static Log log = LogFactory.getLog(MemberRegisterServiceTest.class);

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		ctx.getEnvironment().setActiveProfiles("oracle" , "mybatis");
//		ctx.getEnvironment().setActiveProfiles("oracle" , "spring");
//		ctx.getEnvironment().setActiveProfiles("mysql" , "mybatis");
//		ctx.getEnvironment().setActiveProfiles("mysql" , "spring");
		
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		MemberRegisterService service = ctx.getBean(MemberRegisterService.class);
		MemberInfoService infoService = ctx.getBean(MemberInfoService.class);
		
		Member m = ctx.getBean(Member.class);
		m.setEmail(m.getEmail() + "xxx3");
		
		try {
			service.register(m);
			log.info("id = " + m.getId());
		} catch (AlreadyExistingMemberException e) {
			log.info("멤버가 이미 존재합니다.", e);
		}
		
		

		
	}

}
