package com.webapp.idgenerator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;



public class OracleIdSequenceTest {
	
	static Log log = LogFactory.getLog(OracleIdSequenceTest.class);

	public static void main(String[] args) throws SQLException, IOException {
		//jdbc();
		//jdbcTemplate();
		//sqlSession();
		SqlSessionTemplate();
	}
	
	static void SqlSessionTemplate() throws SQLException {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("oracle");
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		
		Connection con = ds.getConnection();
		log.info("DB = " + con.getMetaData().getDatabaseProductName());
		log.info("DB = " + con.getMetaData().getDatabaseProductVersion());
		log.info("DB = " + con.getMetaData().getDatabaseMajorVersion());
		log.info("DB = " + con.getMetaData().getDatabaseMinorVersion());
	
		MemberMapper mapper = ctx.getBean(MemberMapper.class);
		
		Member member = ctx.getBean(Member.class);
		
		member.setEmail(member.getEmail() + (int)(Math.random()*1000));
		log.info(member.getRegdate());
			
		mapper.insert(member);
		log.info("id = " + member.getId());
	}
	
	static void sqlSession() throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis_config.xml");
		//SqlSessionFactory factory = builder.build(inputStream, "oracle");
		SqlSessionFactory factory = builder.build(inputStream, "mysql");
		
		SqlSession session = factory.openSession(false);
		
		String statement = null;
		
		/*
		 * ibatis CRUD ==> 매퍼 파일(xml)
		 */
		Member member =  session.selectOne("com.webapp.mapper.MemberMapper.selectById", 1000); // namespace . id 
		if (member != null)
			log.info("id = " + member.getId());
		
		List<Member> list = session.selectList("com.webapp.mapper.MemberMapper.selectAll");
		log.info("member size = " + list.size());
		
		member = new Member();
		member.setEmail("hong@naver.com" + (int)(Math.random()*1000));
		member.setPassword("1234");
		member.setName("홍길동");
		member.setRegdate(new Date());
		
		session.insert("com.webapp.mapper.MemberMapper.insert", member);
//		session.delete("com.webapp.mapper.memberMapper.delete", null);
//		session.update("com.webapp.mapper.memberMapper.update", null);
		
		/*
		 * Mapper interface CRUD : Mapper인터페이스로 query문 쉽게 작성할 수 있음
		 * ==> Mapper Interface + 매퍼파일
		 */
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		list = mapper.selectAll();
		log.info("member size = " + list.size());
		member = mapper.selectById(1000);
		if (member != null)
			log.info("id = " + member.getId());
		
		member = new Member();
		member.setEmail("hong@naver.com" + (int)(Math.random()*1000));
		member.setPassword("1234");
		member.setName("홍길동");
		member.setRegdate(new Date());
		
		mapper.insert(member);
//		
//		dao.insert(null);
//		dao.deleteByIdWithEmail(100, null);
//		dao.update(null);
//		
//		session.rollback();
		session.commit();
	
		
	}
	
	static void jdbcTemplate() {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("oracle");
		ctx.load("classpath:spring/beans.xml");
		ctx.refresh();
		
		JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
		
		String sql = "select member_id_seq.nextval from dual";
		int seq = template.queryForObject(sql, Integer.class);
		log.info("seq = " + seq);
		
		String insert = "insert into member " +
				"(id, email, password, name, regdate) " +
				"values " +
				"(?, ?, 'yyy', 'xxx', '2015/08/11') ";
		
		template.update(insert, seq, "xxx@" + seq);
		
		
	}
		
	static void jdbc() throws SQLException {
	
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(); 
		ctx.getEnvironment().setActiveProfiles("oracle");
		ctx.load("classpath:spring/beans.xml");
		ctx.refresh();
		
		DriverManagerDataSource ds = ctx.getBean(DriverManagerDataSource.class);
		log.info(ds.getUrl());
		
		Connection con = ds.getConnection();
		
		// 1. select member_id_seq.nextval from dual;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select member_id_seq.nextval from dual");
		rs.next();
		int seq = rs.getInt(1);
		log.info("seq = " + seq);
		
		String insert = "insert into member " +
						"(id, email, password, name, regdate) " +
						"values " +
						"(?, ?, 'yyy', 'xxx', '2015/08/11') ";
		
		PreparedStatement pstmt = con.prepareStatement(insert);
		pstmt.setInt(1, seq);
		pstmt.setString(2, "xxx@xxx.com" + seq);
		pstmt.executeUpdate();
		
		con.close();
		
		
		
		// 2. inser into member
		// (id, email, password, name, regdate)
		// values
		// (1004, 'xxx11@yyy.com', '1234', '홍길동', 2015/08/13) 
	}

}
