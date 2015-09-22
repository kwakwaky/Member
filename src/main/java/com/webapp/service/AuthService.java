package com.webapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import com.webapp.dao.MemberDao;
import com.webapp.exception.IdPasswordNotMatchException;
import com.webapp.model.AuthInfo;
import com.webapp.model.Member;
import com.webapp.util.Password;

public class AuthService {
	
	static Log log = LogFactory.getLog(AuthService.class);
	
	MemberDao dao;
	
	public void setMemberDao (MemberDao dao) {
		this.dao = dao;
	}

	public AuthInfo authenticate(String email, String password) {
		AuthInfo info = new AuthInfo();
//		info.setName("곽대성");
//		info;
		try {
			Member m = dao.selectByEmail(email);	
			if (!m.getPassword().equals(Password.encode(password))) {
				log.info("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
				throw new IdPasswordNotMatchException();
			}
			log.info("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
			info.setEmail(m.getEmail());
			info.setName(m.getName());
			
			
		} catch (EmptyResultDataAccessException e) {
			log.info("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
			throw new IdPasswordNotMatchException(e);
		}
		return info;
	}
	
}
