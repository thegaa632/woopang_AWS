package com.standout.sopang.admin.order.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.standout.sopang.config.ConvertList;
import com.standout.sopang.order.dto.OrderDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.standout.sopang.admin.goods.dao.AdminGoodsDAO;
import com.standout.sopang.admin.order.dao.AdminOrderDAO;
import com.standout.sopang.goods.vo.GoodsVO;
import com.standout.sopang.goods.vo.ImageFileVO;
import com.standout.sopang.member.vo.MemberVO;
import com.standout.sopang.order.vo.OrderVO;


@Service("adminOrderService")
@Log4j2
@Transactional(propagation=Propagation.REQUIRED)
public class AdminOrderServiceImpl implements AdminOrderService {
	@Autowired
	private AdminOrderDAO adminOrderDAO;

	@Autowired
	ConvertList convertList;
	
	

	public List<OrderDTO>listNewOrder(Map condMap) throws Exception{
//		List<OrderDTO> orderConverDTOList	=convertList.orderConverDTO(adminOrderDAO.selectNewOrderList(condMap));

//		return adminOrderDAO.selectNewOrderList(condMap);
		//여기도 하나 빠져서 나옴
		return convertList.orderConverDTO(adminOrderDAO.selectNewOrderList(condMap));
	}
	


	@Override
	public void  modifyDeliveryState(Map deliveryMap) throws Exception{
		adminOrderDAO.updateDeliveryState(deliveryMap);
	}
	

}
