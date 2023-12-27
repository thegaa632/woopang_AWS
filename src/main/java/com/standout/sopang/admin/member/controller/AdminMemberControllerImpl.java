package com.standout.sopang.admin.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.standout.sopang.member.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.standout.sopang.admin.member.service.AdminMemberService;
import com.standout.sopang.common.base.BaseController;
import com.standout.sopang.member.vo.MemberVO;
@Log4j2
@Controller("adminMemberController")
@RequestMapping(value="/admin/member")
public class AdminMemberControllerImpl extends BaseController  implements AdminMemberController{

	@Autowired
	private AdminMemberService adminMemberService;



	@RequestMapping(value="/adminMemberMain" ,method={RequestMethod.POST,RequestMethod.GET})
	@Override
	public String adminGoodsMain(Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {


		log.info("adminController : ");
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");

		String beginDate=null,endDate=null;


		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);

		HashMap<String,Object> condMap=new HashMap<String,Object>();
		condMap.put("beginDate",beginDate);
		condMap.put("endDate", endDate);
		ArrayList<MemberDTO> member_list=adminMemberService.listMember(condMap);


		model.addAttribute("member_list", member_list);

		String beginDate1[]=beginDate.split("-");
		String endDate2[]=endDate.split("-");

		model.addAttribute("beginYear",beginDate1[0]);
		model.addAttribute("beginMonth",beginDate1[1]);
		model.addAttribute("beginDay",beginDate1[2]);
		model.addAttribute("endYear",endDate2[0]);
		model.addAttribute("endMonth",endDate2[1]);
		model.addAttribute("endDay",endDate2[2]);

		return "/admin/member/adminMemberMain";
	}

}
