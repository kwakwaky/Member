package com.webapp.service;

import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.MemberDao;
import com.webapp.exception.MemberUnRegisterEmptyException;

public class MemberUnRegisterService {

	MemberDao memberDao;
	
	public void setMemberDao(MemberDao dao) {
		this.memberDao = dao;
	}
	
	@Transactional
	public void unRegister(int id) {
		try {
			memberDao.deleteById(id);
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException e) {
			throw new MemberUnRegisterEmptyException(e);
		}
	}
	
	@Transactional
	public void unRegister(String email) {
		try {
			memberDao.deleteByEmail(email);
		} catch (JdbcUpdateAffectedIncorrectNumberOfRowsException e) {
			throw new MemberUnRegisterEmptyException(e);
		}
	}
	
}
