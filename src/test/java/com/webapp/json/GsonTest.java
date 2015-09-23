package com.webapp.json;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.model.Member;

public class GsonTest {

	public static void main(String[] args) {
		
		Gson g = new GsonBuilder().setPrettyPrinting().create(); //출력을 더 가독성 있게 해주는 옵션
		
		// Json으로 변환
		/*
		 * Promitive
		 */
		String json = g.toJson("Hello");
		System.out.println(json);
		
		/*
		 * Array
		 */
		json = g.toJson(new Object[]{"Hello", 1234,"C++"});
		System.out.println(json);
		
		/*
		 * Object
		 */
		Member m = new Member();
		m.setId(100);
		m.setEmail("gson@gmail.com");
		m.setName("쥐선");
		m.setPassword("1234");
		m.setRegdate(new Date());
		
		json = g.toJson(m);
		System.out.println(json);
		
		// serialization 한거임 : 객체를 스트링으로
	}

}
