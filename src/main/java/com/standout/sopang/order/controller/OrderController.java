package com.standout.sopang.order.controller;

import com.standout.sopang.order.dto.OrderDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface OrderController {
	public String orderEachGoods(@ModelAttribute("orderDTO") OrderDTO _orderDTO, HttpServletRequest request,
								 HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws Exception;

	public String orderAllCartGoods(@RequestParam String[] cart_goods_qty, HttpServletRequest request,
									HttpServletResponse response) throws Exception;

	public String payToOrderGoods(@RequestParam Map<String, String> orderMap, HttpServletRequest request,Model model, RedirectAttributes redirectAttributes,
								  HttpServletResponse response) throws Exception;

	public String payFail(HttpServletRequest request, HttpServletResponse response) throws Exception;

}