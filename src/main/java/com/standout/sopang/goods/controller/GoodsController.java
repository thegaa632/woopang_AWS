package com.standout.sopang.goods.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public interface GoodsController {

	public String menuGoods(@RequestParam("menuGoods") String menuGoods, Model model
							, HttpServletRequest request, HttpServletResponse response) throws Exception;
	

	public @ResponseBody String keywordSearch(@RequestParam("keyword") String keyword,Model model,HttpServletRequest request, HttpServletResponse response) throws Exception;
	

	public String searchGoods(Map<String, String> map, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;


	public String goodsDetail(@RequestParam("goods_id") String goods_id,Model model,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
