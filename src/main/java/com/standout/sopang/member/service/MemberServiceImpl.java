package com.standout.sopang.member.service;

import java.util.Map;

import com.standout.sopang.member.dto.MemberDTO;
import com.standout.sopang.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.standout.sopang.member.dao.MemberDAO;

@Log4j2
@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private ModelMapper modelMapper;
	
	//로그인
	@Override
	public MemberDTO login(Map<String,String> loginMap) throws Exception{

		MemberVO memberVO = memberDAO.login(loginMap);
		log.info("memberVO : " + memberVO);

		if(memberVO == null){
			return null;
		} else {
			MemberDTO memberDTO = modelMapper.map(memberVO,MemberDTO.class);
			log.info(memberDTO);
			return memberDTO;
		}

	}
	
	//회원가입
	@Override
	public void addMember(MemberDTO memberDTO) throws Exception{
		memberDAO.insertNewMember(memberDTO);
	}
	
	//아이디 중복확인
	@Override
	public String overlapped(String id) throws Exception{
		return memberDAO.selectOverlappedID(id);
	}
}
