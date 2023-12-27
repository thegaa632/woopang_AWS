package com.standout.sopang.member.dao;

import java.util.HashMap;
import java.util.Map;

import com.standout.sopang.member.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.standout.sopang.member.vo.MemberVO;

@Repository("memberDAO")
@Log4j2
public class MemberDAOImpl  implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	MemberDTO memberDTO;
	//로그인
	@Override
	public MemberVO login(Map<String,String> loginMap) throws DataAccessException{
		MemberVO member = sqlSession.selectOne("mapper.member.login",loginMap);
		log.info(member);
	   return member;
	}
	
	//회원가입
	@Override
	public void insertNewMember(MemberDTO memberDTO) throws DataAccessException{
		sqlSession.insert("mapper.member.insertNewMember",memberDTO);
	}

	//아이디 중복확인
	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",id);
		return result;
	}
	
	
}
