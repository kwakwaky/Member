package com.webapp.idgenerator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.webapp.mapper.IdGeneratorMapper;
import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;



public class GeneratorTableTest {
	
	static Log log = LogFactory.getLog(GeneratorTableTest.class);
	
	static GenericXmlApplicationContext ctx;

	public static void main(String[] args) throws SQLException {
		
		ctx = new GenericXmlApplicationContext();
//		ctx.getEnvironment().setActiveProfiles("oracle");
		ctx.getEnvironment().setActiveProfiles("mysql");
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		//jdbc();
		//jdbcTemplate();
		
		for (int i=0; i<100; i++) {
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					try {
						//jdbc();
						//jdbcTemplate();
						//sqlSession();
						sqlSessionTemplate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	static void sqlSessionTemplate() throws InterruptedException {
		PlatformTransactionManager tm = ctx.getBean(PlatformTransactionManager.class);
		
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		td.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		
		TransactionStatus status = tm.getTransaction(td);
		
		try {
		    memberInsert();
//			memberUpdate();
			tm.commit(status);
		} catch (BadSqlGrammarException e) {
			e.printStackTrace();
			log.info("Badsql..." + e);
		} catch (Exception e) {
			tm.rollback(status);
			e.printStackTrace();
		} 
		
	}
	
	static void memberUpdate() throws InterruptedException {
		
		Member member = new Member();
		
		member.setId(10300);
		member.setPassword("12345678");
		member.setName("yyyy");
		
		MemberMapper memberMapper = ctx.getBean(MemberMapper.class);
		
		memberMapper.update(member);
	
		
	}
	
	static void memberInsert() throws InterruptedException {
		
		Member member = ctx.getBean(Member.class);
		
		IdGeneratorMapper idGenMapper = ctx.getBean(IdGeneratorMapper.class);
		MemberMapper memberMapper = ctx.getBean("memberMapper", MemberMapper.class);
		
//		SqlSessionTemplate template = ctx.getBean(SqlSessionTemplate.class);
		
		Map<String, Object> idGen = idGenMapper.selectByName("memberId");
		int nextval = ((BigDecimal)idGen.get("nextval")).intValue();
		int incval = ((BigDecimal)idGen.get("incval")).intValue();
		int seqno = nextval + incval;
		idGen.put("nextval", seqno);
		
		Thread.sleep((int)(Math.random()*1000));
		idGenMapper.update(idGen);
		
		member.setId(seqno);
		member.setEmail(member.getEmail() + seqno);
		memberMapper.insertGenerator(member);
		
		log.info("seqno = " + seqno);
		
		
	}
	
	static void sqlSession() throws IOException, InterruptedException {
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis_config.xml");
		//SqlSessionFactory factory = builder.build(inputStream, "mysql");
		SqlSessionFactory factory = builder.build(inputStream, "mysql");
		
		SqlSession session = factory.openSession(false);
		
		IdGeneratorMapper idGeneratorMapper = session.getMapper(IdGeneratorMapper.class);
		MemberMapper memberMapper = session.getMapper(MemberMapper.class);
		
		Map<String, Object> idGen = idGeneratorMapper.selectByName("memberId");
		int nextval = ((BigDecimal)idGen.get("nextval")).intValue();
		int incval = ((BigDecimal)idGen.get("incval")).intValue();
		int seqno = nextval + incval;
		
		Thread.sleep((int)(Math.random()*3000));
		
		idGen.put("nextval", seqno);
		idGeneratorMapper.update(idGen);
		
		Member member = new Member();
		member.setId(seqno);
		member.setEmail("xxx@gen.com" + (int)(Math.random()*1000));
		member.setPassword("1234");
		member.setName("김길동");
		member.setRegdate(new Date());
		
		memberMapper.insertGenerator(member);
		
		log.info("seqno = " + seqno);
		
		session.commit();
		session.close();
		
		
	}
	
	static void jdbcTemplate() throws SQLException, InterruptedException {
		
		JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
		
		// 채번
		String sql = "select name, nextval, incval " +
				 	 " from id_generator " +
				 	 "where name = 'memberId'" +
				 	 " for update "; // rock을 가져감
		
		// <spring에서의 트랜잭션 처리>
//		DataSourceTransactionManager tm = new DataSourceTransactionManager();
//		tm.setDataSource(ctx.getBean(DataSource.class));
		PlatformTransactionManager tm = ctx.getBean(PlatformTransactionManager.class);
		
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		// 트랜잭션의 특성 설정
		definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED); // 독립성 레벨 설정
		definition.setReadOnly(false); // true일 경우 select만 가능, default는 false
		//definition.setTimeout(10); // 트랜잭션에 머물수 있는 시간 설정 
		
		
		// 트랜잭션 시작
		TransactionStatus status = tm.getTransaction(definition);
		
		// 트랜잭션이 없으면 자체적으로 구문 발행, 있으면 트랜잭션을 사용해서 구문 발행
		Map<String, Object> gen = template.queryForMap(sql); 
		
		Thread.sleep((int)(Math.random()*3000));
		
		String name = (String) gen.get("name");
		
		// Map으로 가져올때는 자료형이 BigDecimal(큰 수) 이다.
		int nextval = ((BigDecimal) gen.get("nextval")).intValue();
		int incval = ((BigDecimal) gen.get("incval")).intValue();
		
		String update = "update id_generator " +
				"	set nextval = ? " +
				" where name = 'memberId'";
		
		template.update(update, nextval + incval);
		
		// 트랜잭션 종료
		tm.commit(status);
		
		log.info("name = " + name + ", nextval = " + (nextval + incval) + ", incval = " + incval);
		
		String insert = "insert into member2 " +
						"(id, email, password, name, regdate) " +
						"values " +
						"(?, ?, 'yyy', 'xxx', '2015/08/11') ";
		
		template.update(insert, nextval + incval, "xxx@" + nextval);
		
		
	}
		
	static void jdbc() throws SQLException {
		
		try {
			Thread.sleep((int) (Math.random()*3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		DataSource ds = ctx.getBean(BasicDataSource.class);
		log.info(((BasicDataSource)ds).getUrl());
		
		Connection con = ds.getConnection();
		con.setAutoCommit(false); // exception 발생 시 rollback
		
		// 1. select member_id_seq.nextval from dual;
		Statement stmt = con.createStatement();
		
		String sql = "select name, nextval, incval " +
					 " from id_generator " +
					 "where name = 'memberId'" +
					 " for update "; // rock을 가져감
		
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		
		String name = rs.getString("name");
		int nextval = rs.getInt("nextval");
		int incval = rs.getInt("incval");
		int seq = nextval + incval;
		log.info("seq = " + seq);
		
		
		String insert = "insert into member2 " +
						"(id, email, password, name, regdate) " +
						"values " +
						"(?, ?, 'yyy', 'xxx', '2015/08/11') ";
		
		PreparedStatement pstmt = con.prepareStatement(insert);
		pstmt.setInt(1, seq);
		pstmt.setString(2, "xxx@xxx.com" + seq);
		pstmt.executeUpdate();
		
		String update = "update id_generator " +
						"	set nextval = ? " +
						" where name = 'memberId'";
		
		pstmt = con.prepareStatement(update);
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();
		
		con.commit(); //여기까지 문제없으면 DB에 반영한다.
		con.close();
		
		
		
		// 2. inser into member
		// (id, email, password, name, regdate)
		// values
		// (1004, 'xxx11@yyy.com', '1234', '홍길동', 2015/08/13) 
	}

}
