package com.webapp.service;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.exception.MemberNotFoundException;
import com.webapp.model.Member;



public class MemberInfoServiceTest {
	
	static Log log = LogFactory.getLog(MemberInfoServiceTest.class);

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
//		ctx.getEnvironment().setActiveProfiles("oracle" , "mybatis");
//		ctx.getEnvironment().setActiveProfiles("oracle" , "spring");
		ctx.getEnvironment().setActiveProfiles("mysql" , "mybatis");
//		ctx.getEnvironment().setActiveProfiles("mysql" , "spring");
		
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		MemberInfoService service = ctx.getBean(MemberInfoService.class);
		
		try {
			Member m = service.getMember(0);
//			Member m = service.getMember("xx");
			log.info("id = " + m.getId());
		} catch (MemberNotFoundException e) {
			log.info("로그인 아이디가 없음", e);
		}

		
	}

}
