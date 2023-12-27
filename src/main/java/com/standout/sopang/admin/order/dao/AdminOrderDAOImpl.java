package com.standout.sopang.admin.order.dao;

import java.util.ArrayList;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.standout.sopang.order.vo.OrderVO;

@Repository("adminOrderDAO")
@Log4j2
public class AdminOrderDAOImpl  implements AdminOrderDAO{
	@Autowired
	private SqlSession sqlSession;
	

	public ArrayList<OrderVO>selectNewOrderList(Map condMap) throws DataAccessException{
		ArrayList<OrderVO> orderList=(ArrayList)sqlSession.selectList("mapper.admin.order.selectNewOrderList",condMap);
		orderList.forEach(order -> log.info("orderList: goods_id : " + order.getOrder_id()));
		return orderList;
	}


	public void  updateDeliveryState(Map deliveryMap) throws DataAccessException{
		sqlSession.update("mapper.admin.order.updateDeliveryState",deliveryMap);
	}
	
}
