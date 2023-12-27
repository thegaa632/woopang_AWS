package com.standout.sopang.cart.dao;

import java.util.List;

import com.standout.sopang.cart.dto.CartDTO;
import org.springframework.dao.DataAccessException;

import com.standout.sopang.cart.vo.CartVO;
import com.standout.sopang.goods.vo.GoodsVO;

public interface CartDAO {

	public List<CartVO> selectCartList(CartDTO cartDTO) throws DataAccessException;
	public List<GoodsVO> selectGoodsList(List<CartDTO> cartList) throws DataAccessException;


	public boolean selectCountInCart(CartDTO cartDTO) throws DataAccessException;
	public void insertGoodsInCart(CartDTO cartDTO) throws DataAccessException;


	public void deleteCartGoods(int cart_id) throws DataAccessException;


	public void updateCartGoodsQty(CartDTO cartDTO) throws DataAccessException;

}