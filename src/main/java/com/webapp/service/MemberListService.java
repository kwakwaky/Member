package com.webapp.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.MemberDao;
import com.webapp.model.Member;
import com.webapp.model.MemberList;

public class MemberListService {
	
	MemberDao dao;
	
	public void setMemberDao(MemberDao dao) {
		this.dao = dao;
	}
	
	@Transactional // default로 동작
	public List<Member> getListAll() {
		return dao.selectAll();
	}
	
	PlatformTransactionManager tm;
	
//	public void setTransactionManager(PlatformTransactionManager tm) {
//		this.tm = tm;
//	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,
				   propagation=Propagation.REQUIRED, // 현재 진행중인 트랜잭션이 있으면 그 트랜잭션에 참여
				   readOnly=true,
				   rollbackFor=Exception.class) // 모든 익셉션에대해 롤백
	public MemberList getList(int pageNo) {
		MemberList paging = new MemberList();
		
		
		int totalItem = dao.selectTotalCount(); // 모든 아이템 수 계산
		paging.setTotalItem(totalItem);
		paging.setPageNo(pageNo);
		
		int firstItem = paging.getFirstItem();
		int lastItem = paging.getLastItem();
		
		List<Member> members = dao.select(firstItem, lastItem);
		paging.setMembers(members); // page 세팅
		
		return paging;
	}
	
}