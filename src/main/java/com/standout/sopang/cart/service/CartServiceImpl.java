package com.standout.sopang.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.standout.sopang.cart.config.CartConvert;
import com.standout.sopang.cart.dto.CartDTO;
import com.standout.sopang.config.ConvertList;
import com.standout.sopang.goods.dto.GoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.standout.sopang.cart.dao.CartDAO;
import com.standout.sopang.cart.vo.CartVO;
import com.standout.sopang.goods.vo.GoodsVO;

@Service("cartService")
@Transactional(propagation = Propagation.REQUIRED)
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	CartConvert carConvertDTO;
	@Autowired
	ConvertList convertList;


	public Map<String, List> myCartList(CartDTO cartDTO) throws Exception {
		Map<String, List> cartMap = new HashMap<String, List>();




		List<CartVO> myCartList = cartDAO.selectCartList(cartDTO);
		List<CartDTO>	myCartDTOList	 = carConvertDTO.convertDTO(myCartList);


		if (myCartList.size() == 0) {return null;}

		List<GoodsVO> myGoodsList = cartDAO.selectGoodsList(myCartDTOList);
		List<GoodsDTO> myGoodsDTOList=convertList.goodsConvertDTO(myGoodsList);
		try {
			cartMap.put("myCartList", myCartDTOList);
			cartMap.put("myGoodsList", myGoodsDTOList);
		} catch (Exception e) {e.printStackTrace();}

		return cartMap;
	}


	public boolean findCartGoods(CartDTO cartDTO) throws Exception {
		return cartDAO.selectCountInCart(cartDTO);
	}
	public void addGoodsInCart(CartDTO cartDTO) throws Exception {
		cartDAO.insertGoodsInCart(cartDTO);
	}


	public void removeCartGoods(int cart_id) throws Exception {
		cartDAO.deleteCartGoods(cart_id);
	}


	public boolean modifyCartQty(CartDTO cartDTO) throws Exception {
		boolean result = true;
		cartDAO.updateCartGoodsQty(cartDTO);
		return result;
	}

}
