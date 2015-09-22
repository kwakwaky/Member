package com.webapp.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.util.Assert;

import com.webapp.mapper.IdGeneratorMapper;
import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;
import com.webapp.util.Password;

public class MybatisMemberDao implements MemberDao{
	
	static Log log = LogFactory.getLog(MybatisMemberDao.class);
	MemberMapper memberMapper;
	IdGeneratorMapper idGeneratorMapper;
	
	boolean useGeneratorTable;
	
	public void setUseGeneratorTable(boolean use) {
		this.useGeneratorTable = use;
		log.info("useGeneratorTable = " + use);
	}
	
	public void setMemberMapper(MemberMapper mapper) {
		memberMapper = mapper;
	}
	
	public void setIdGeneratorMapper (IdGeneratorMapper mapper) {
		idGeneratorMapper = mapper;
	}
	
	@Override
	public List<Member> selectAll() {
		
		return memberMapper.selectAll();
	}
	
	void error() {
		throw new RuntimeException();
	}
	
	@Override
	public List<Member> select(Map<String, Object> index) {
		List<Member> list = memberMapper.select(index);
		return list;
	}

	@Override
	public List<Member> select(int firstItem, int lastItem) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("firstItem", firstItem);
		index.put("lastItem", lastItem);
		int offset = firstItem - 1;
		int count = lastItem - firstItem + 1;
		index.put("offset", offset);
		index.put("count", count);
		
		
		return this.select(index);
	}
	
	// 이 메서드는 데이터가 없으면 null을 리턴한다. 그래서 해당 문제를 처리해줘야됨
	@Override
	public Member selectById(int id) {
		Member m = memberMapper.selectById(id);
		if (m == null)
			throw new EmptyResultDataAccessException(id + "", 1);
		return m;
	}

	@Override
	public Member selectByEmail(String email) {
		log.info("xxxxxxxxxxxxxxxxxxxxxxxxxx");
		Member m = memberMapper.selectByEmail(email);
		if (m == null) {
			log.info("yyyyyyyyyyyyyyyyyyyyyyyy");
			throw new EmptyResultDataAccessException(email + "", 1);
		}
		return m;
	}
	
	@Override
	public int selectTotalCount() {
		//error(); // end log는 안나옴 -> After Advice 써야됨
		return memberMapper.selectTotalCount();
	}

	@Override
	public int insert(Member member) {
		int rtn = 0;
		member.setPassword(Password.encode(member.getPassword()));
		
		if (useGeneratorTable) {
			// 채번 테이블을 select하는 로직이 필요함
			Map<String, Object> idGen = idGeneratorMapper.selectByName("memberId");
			int nextval = ((BigDecimal)idGen.get("nextval")).intValue();
			int incval = ((BigDecimal)idGen.get("incval")).intValue();
			int seq = nextval + incval;
			
			idGen.put("nextval", seq);
			idGeneratorMapper.update(idGen);
			
			member.setId(seq);
			rtn = memberMapper.insertGenerator(member);
		} else {
			rtn = memberMapper.insert(member);
		}
		
		
		return rtn;
	}

	@Override
	public int insertGenerator(Member member) {
		Assert.isTrue(false, "deprecated...");
//		return memberMapper.insertGenerator(member);
		return 0;
	}

	@Override
	public int update(Member member) {
		int rtn = memberMapper.update(member);
		if (rtn != 1) {
			throw new JdbcUpdateAffectedIncorrectNumberOfRowsException("invalid update", 1, rtn);
		}
		return rtn;
	}

	@Override
	public int deleteById(int id) {
		int rtn = memberMapper.deleteById(id);
		if (rtn != 1) {
			throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(id + "", 1, rtn);
		}
		return rtn;
	}

	@Override
	public int deleteByEmail(String email) {
		int rtn = memberMapper.deleteByEmail(email);
		if (rtn != 1) {
			throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(email + "", 1, rtn);
		}
		return rtn;
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
