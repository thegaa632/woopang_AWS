package com.standout.sopang.admin.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminOrderController {

	//�ֹ����
	public String adminOrderMain(@RequestParam Map<String, String> dateMap, Model model,
								 HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	//�ֹ����� - ��ۼ���
	public ResponseEntity modifyDeliveryState(@RequestParam Map<String, String> deliveryMap, 
            HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
