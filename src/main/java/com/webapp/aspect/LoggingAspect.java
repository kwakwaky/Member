package com.webapp.aspect;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect // 이걸보고 Aspect취급 됨
@Order(0) // 낮은게 먼저 적용됨
public class LoggingAspect {
	
//	위빙되는 대상을 필터링 
//	@Pointcut("execution(public * *..*Member.*(..) )")  public, return타입 상관x, 모든 패키지, 파라미터 상관x
//	메서드 이름 형식을 떠올리면 된다.
	@Pointcut("execution(public * com.webapp.dao.*MemberDao.*(..) ) || " // or는 안먹음
			+ "execution(public * com.webapp.service.*Service.*(..) )") 
	void pointcut() {
	}
	
	@Before("pointcut()") //  <-- pointcut을 설정한 메서드의 이름이 들어감
	void before(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		String msg = "### " + 
					 jp.getSignature().getName() +
					 "(" +
					 Arrays.toString(jp.getArgs()) +
					 ")" +
					 " START";
		log.info(msg);
	}
	
	@After("pointcut()")
	void after(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		String msg = "### " + 
				 jp.getSignature().getName() +
				 "(" +
				 Arrays.toString(jp.getArgs()) +
				 ")" +
				 " END";
		log.info(msg);
	}
	
}
