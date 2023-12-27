package com.standout.sopang.order.dao;

import java.util.ArrayList;
import java.util.List;

import com.standout.sopang.order.dto.OrderDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.standout.sopang.order.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//주문하기
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException{
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			sqlSession.insert("mapper.order.insertNewOrder",orderVO);
		}
	}
	
	public int selectOrderID() throws DataAccessException{
		//주문번호 시퀀스를 생성하여 결과값을 반환한다.
		int result = sqlSession.selectOne("mapper.order.selectOrderID");
		return result;
	}

	//주문완료시 장바구니에서 상품 제거
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException{
		for(int i=0; i<myOrderList.size();i++){
			//주문상품리스트의 정보를 가지고 delete문을 myOrderList만큼, for문을 돌려 실행한다.
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			sqlSession.delete("mapper.order.deleteGoodsFromCart",orderVO);
		}
	}	
}

