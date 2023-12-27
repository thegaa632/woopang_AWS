package com.standout.sopang.common.interceptor;

import java.sql.Wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.standout.sopang.member.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.standout.sopang.member.vo.MemberVO;

public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	//count를 간단히 불러오기 위해 intercrptor에 sqlSession추가.
	@Autowired
	private SqlSession sqlSession;

	//일반사용자인지, 관리자인지를 구문하기 위해 memberVO 빈을 사용한다.
	@Autowired
	private MemberDTO memberDTO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		HttpSession session = request.getSession();

		try {
			//사용자확인
			memberDTO=(MemberDTO) session.getAttribute("memberInfo");
			String  member_id=memberDTO.getMember_id();

			//공통, 카트갯수, 주문갯수, 소팡머니 출력
			int cartCount = 0;
			cartCount=sqlSession.selectOne("mapper.sopang.counts.cartLen",member_id);
			session.setAttribute("cartCount", cartCount);

			int deliveringCount = 0;
			deliveringCount=sqlSession.selectOne("mapper.sopang.counts.deliveringLen",member_id);
			session.setAttribute("deliveringCount", deliveringCount);

//			Long sopang_money = 0L;
//			sopang_money=(Long)sqlSession.selectOne("mapper.sopang.counts.sopang_money",member_id);
//			session.setAttribute("sopang_money", sopang_money);

			//관리자일경우, 상품갯수, 주문건수, 총매출 출력.
			//System.out.println(member_id);
			if(member_id.equals("1111") == true) {
				int goodsLen = 0;
				goodsLen=sqlSession.selectOne("mapper.sopang.counts.goodsLen");
				session.setAttribute("goodsLen", goodsLen);

				int ordersLen = 0;
				ordersLen=sqlSession.selectOne("mapper.sopang.counts.ordersLen");
				session.setAttribute("ordersLen", ordersLen);

				Long totalSales = 0L;
				totalSales=(Long)sqlSession.selectOne("mapper.sopang.counts.totalSales");
				session.setAttribute("totalSales", totalSales);
			}

		}catch (Exception e) {
			//System.out.println("로그인하지않았거나 예상하기 어려운 예외가 발생했습니다.");
		}

		return true;
	}

	//요청시 요청 url에서 viewName을 추출할 getViewName 메소드, fileName return
//	private String getViewName(HttpServletRequest request) throws Exception {
//		String contextPath = request.getContextPath();
//		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
//		if (uri == null || uri.trim().equals("")) {
//			uri = request.getRequestURI();
//		}
//
//		int begin = 0;
//		if (!((contextPath == null) || ("".equals(contextPath)))) {
//			begin = contextPath.length();
//		}
//
//		int end;
//
//		//파라미터가 있을경우
//		if (uri.indexOf(";") != -1) {
//			end = uri.indexOf(";");
//		} else if (uri.indexOf("?") != -1) {
//			end = uri.indexOf("?");
//		} else {
//			end = uri.length();
//		}
//
//		//경로안에 파일등이 있을경우
//		String fileName = uri.substring(begin, end);
//		if (fileName.indexOf(".") != -1) {
//			fileName = fileName.substring(0, fileName.lastIndexOf("."));
//		}
//		if (fileName.lastIndexOf("/") != -1) {
//			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
//		}
//
//		return fileName;
//	}

}