package com.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.webapp.model.Member;

public class LogProxyMemberDao implements MemberDao {
	
	static Log log;
	MemberDao real;
	
	public LogProxyMemberDao(MemberDao real) {
		this.real = real;
		log = LogFactory.getLog(real.getClass()); // 현재 클래스의 log를 넘겨주는게 아님
	}
	
	@Override
	public List<Member> selectAll() {
		log.info("######################"); // 횡단적 기능(관심)(관점) Aspect
		log.info("selectAll() start...");	// 횡단적 관심사의 분리 ==> AOP
		log.info("######################"); // id_generator ==> idGenerator 로 자동맵핑 됨
		List<Member> list = real.selectAll();
		log.info("######################");
		log.info("selectAll() end...");
		log.info("######################");
		return list;
	}

	@Override
	public List<Member> select(Map<String, Object> index) {
		log.info("######################"); // 횡단적 기능(관심)(관점) Aspect
		log.info("select(Map<String, Object> index) start...");	// 횡단적 관심사의 분리 ==> AOP
		log.info("######################");	// cross-cutting concern
		List<Member> list = real.select(index);
		log.info("######################");
		log.info("select(Map<String, Object> index) end...");
		log.info("######################");
		return list;
	}

	@Override
	public Member selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectTotalCount() {
		
		int rtn = 0;
		// 아래의 모든것은 Around Advice
		try {
			log.info("######################");
			log.info("selectTotalCount() Before Advice...");
			log.info("######################");
			// Before Advice
			rtn = real.selectTotalCount();		// JoinPoint (조인포인트)
			log.info("######################");
			log.info("selectTotalCount() After Returning...");
			log.info("######################");
			// After ReturnIng
		} catch (Throwable t) {
			log.info("######################");
			log.info("selectTotalCount() After Throwing...");
			log.info("######################");
			// After Throwing
		} finally {
			log.info("######################");
			log.info("selectTotalCount() After Advice...");
			log.info("######################");
			// After Advice
		}
		
		// After Advice
		// After ReturnIng : 성공적으로 수행될 때
		// After Throwing : 에러시 익셉션이 발생했을 때
		
		// Around Advice : Before + After, Tracsaction처리는 AroundAdvice로 한다.
		// Pointcut : Weaving되는 JoinPoint들
		// Aspect Class = Pointcut + Advice
		
		return rtn;
	}

	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertGenerator(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> select(int firstItem, int lastItem) {
		return real.select(firstItem, lastItem);
	}

	@Override
	public Member selectByEmailWithPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByIdWithEmail(int id, String email) {
		// TODO Auto-generated method stub
		
	}

}
