package com.webapp.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.model.Member;
import com.webapp.model.MemberList;


public class MemberListServiceTest {
	
	static Log log = LogFactory.getLog(MemberListServiceTest.class);

	public static void main(String[] args) {
		
		
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
//		ctx.getEnvironment().setActiveProfiles("oracle" , "mybatis");
//		ctx.getEnvironment().setActiveProfiles("oracle" , "spring");
		ctx.getEnvironment().setActiveProfiles("mysql" , "mybatis");
//		ctx.getEnvironment().setActiveProfiles("mysql" , "spring");
		
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		MemberListService service = ctx.getBean(MemberListService.class);
		List<Member> list = service.getListAll();
		log.info("member size = " + list.size());
		
		MemberList page = service.getList(3);
		
		log.info("total item = " + page.getTotalItem());
		log.info("pageNo = " + page.getPageNo());
		log.info("first item = " + page.getFirstItem());
		log.info("last item = " + page.getLastItem());
		log.info("total page = " + page.getTotalPage());
		
		for (Member m : page.getMembers()) {
			System.out.println(m.getId() + " / " + m.getEmail());
		}
	}

}
