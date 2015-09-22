package com.webapp.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.MemberDao;
import com.webapp.exception.MemberNotFoundException;
import com.webapp.model.Member;

public class MemberInfoService {
	
	MemberDao memberDao;
	
	public void setMemberDao(MemberDao dao) {
		this.memberDao = dao;
	}
	
	@Transactional(readOnly=true)
	public Member getMember(int id) {
		Member m = null;
		try {
			m = memberDao.selectById(id);
		} catch (EmptyResultDataAccessException e) { // 빈 익셉션을 비즈 익셉션으로 변환처리
			throw new MemberNotFoundException(e); // 비즈적인 의미에 맞는 익셉션 = 비즈익셉션
			// 해당 익셉션을 여기서 잡아줌, 컨트롤러에서 비즈 익셉션을 처리해준다.
		}
		return m;
	}
	
	@Transactional(readOnly=true)
	public Member getMember(String email) {
		Member m = null;
		try {
			m = memberDao.selectByEmail(email);
		} catch (EmptyResultDataAccessException e) { 
			throw new MemberNotFoundException(e); 
		}
		return m;
	}
	
}
