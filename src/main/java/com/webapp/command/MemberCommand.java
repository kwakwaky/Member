package com.webapp.command;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.webapp.model.Member;

public class MemberCommand {
	
	static Log log = LogFactory.getLog(MemberCommand.class);
	
	private String email;		// size = 64
	private String password;	// size = 64
	private String name;		// size = 12
	private String gender;
	private String[] hobby;
	private String comment;
	private boolean reception;
	
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		//JSON(JavaScript Object Notation)
		builder.append("\n");
		builder.append("email : " + email);						builder.append(" ," + "\n");
		builder.append("password : " + password);				builder.append(" ," + "\n");
		builder.append("name : " + name);						builder.append(" ," + "\n");
		builder.append("gender : " + gender);					builder.append(" ," + "\n");
		builder.append("hobby : " + Arrays.toString(hobby));	builder.append(" ," + "\n");
		builder.append("comment : " + comment);					builder.append(" ," + "\n");
		builder.append("reception : " + reception);
		
		log.info("gender = " + gender);
		log.info("hobby = " + Arrays.toString(hobby));
		log.info("comment = " + comment);
		log.info("reception = " + reception);
		
		
		return builder.toString();
	}
	
	
	public Member getMember() {
		Member m = new Member();
		
		m.setEmail(this.email);
		m.setPassword(this.password);
		m.setName(this.name);
		m.setRegdate(new Date());
		
		return m;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String[] getHobby() {
		return hobby;
	}


	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public boolean isReception() {
		return reception;
	}


	public void setReception(boolean reception) {
		this.reception = reception;
	}

	
	
	
	

}
