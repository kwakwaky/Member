package com.webapp.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContainerTest {
	
	static Log log = LogFactory.getLog(SpringContainerTest.class);
	
	static BeanFactory factory;
	SimpleDateFormat format;
	
//	설정자
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	
	public void print() throws InterruptedException {
		for ( int i=0; i<5; i++){
			Date curr = factory.getBean(Date.class);
			log.info("Current Time = " + format.format(curr));
			Thread.sleep(1000);
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		
//		factory
//		xml설정을 읽어들여서 그 설정대로 모든 Bean들을 이용해 공장을 만듬
		
//		BeanFactory factory = new GenericXmlApplicationContext("file:beans.xml");
		factory = new FileSystemXmlApplicationContext("classpath:beans.xml");
		
//		공장에서 만들어진것을 가져옴
		factory.getBean(SpringContainerTest.class).print();
		
			
//		SimpleDateFormat format = (SimpleDateFormat) factory.getBean("format");
		
//		Spring은 싱글톤으로서 new를 한번만 하기 때문에 아래의 for문 안에서 curr은 처음 new된 시간만이 연속적으로 출력됨
//		beans.xml에서 date객체에 scope로 prototype을 주면 제대로 나옴
		
		
		
		
	}

}
