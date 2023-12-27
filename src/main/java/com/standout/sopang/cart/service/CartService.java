package com.standout.sopang.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.standout.sopang.cart.dto.CartDTO;
import com.standout.sopang.cart.vo.CartVO;
import com.standout.sopang.goods.vo.GoodsVO;

public interface CartService {

	public Map<String ,List> myCartList(CartDTO cartDTO) throws Exception;
	

	boolean findCartGoods(CartDTO cartDTO) throws Exception;
	public void addGoodsInCart(CartDTO cartDTO) throws Exception;
	

	public void removeCartGoods(int cart_id) throws Exception;
	

	public boolean modifyCartQty(CartDTO cartDTO) throws Exception;
}
