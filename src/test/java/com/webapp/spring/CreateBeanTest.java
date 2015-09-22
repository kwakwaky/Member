package com.webapp.spring;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;

public class CreateBeanTest {
	
	static Log log = LogFactory.getLog(CreateBeanTest.class);

	public static void main(String[] args) throws Exception {
		
		/*
		 * Object(객체)를 생성하는 방법 3가지
		 * 
		 * 	1. new 생성
		 * 	2. static method로 객체생성 newInstance() why Singleton
		 * 	3. FactoryBean으로 객체생성 (가장 많이 쓰인다.)
		 */
		String resourceLocations = "com/webapp/spring/createbean.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		System.out.println("ctx = " + ctx);
		
		CityDao dao = ctx.getBean(CityDao.class);
		//CityDao dao = ctx.getBean("cityDao", CityDao.class);
		//CityDao dao = (CityDao) ctx.getBean("cityDao");
		dao.print();
		
		SqlSessionFactory f = ctx.getBean(SqlSessionFactory.class); 
		//creatbean.xml에 SqlSessionFactory인 bean이 없는데 에러가 왜 안나는가?
		//SqlSession session = f.openSession();
		
		//session을 직접 가져옴
		//SqlSessionTemplate이 SqlSession으로 변환처리됨
		SqlSessionTemplate session_t = ctx.getBean(SqlSessionTemplate.class);
		SqlSession session = ctx.getBean(SqlSession.class);
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		List<Member> list = mapper.selectAll();
		log.info("size = " + list.size());
		
		//MyFactoryBean fb = new MyFactoryBean(); //Builder 역할
		//SqlSessionFactory factory = fb.getObject();
		
		//CityDao dao2 = CityDao.newInstance();
		//dao2.print();
		
		
	}

}
