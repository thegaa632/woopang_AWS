package com.standout.sopang.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.standout.sopang.goods.dto.GoodsDTO;
import com.standout.sopang.member.dto.MemberDTO;
import com.standout.sopang.order.dto.OrderDTO;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.standout.sopang.common.base.BaseController;
import com.standout.sopang.order.service.ApiService01;
import com.standout.sopang.order.service.OrderService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller("orderController")
@RequestMapping(value = "/order")
public class OrderControllerImpl extends BaseController implements OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDTO orderDTO;


	// 개별주문
	@RequestMapping(value = "/orderEachGoods", method = RequestMethod.POST)
	public String orderEachGoods(@ModelAttribute("orderDTO") OrderDTO _orderDTO, HttpServletRequest request,
								 HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		session = request.getSession();
		// 로그인 여부 체크
		Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
		String action = (String) session.getAttribute("action");
		log.info("Goods_id : " + _orderDTO.getGoods_id());

		// 이전에 로그인 상태인 경우는 주문과정 진행
		if (isLogOn == null || isLogOn == false) {
			session.setAttribute("orderInfo", _orderDTO);
			session.setAttribute("action", "/order/orderEachGoods");
			return "redirect:/member/login";
		}
		// 로그아웃 상태인 경우 로그인 화면으로 이동
		else {
			if (action != null && action.equals("/order/orderEachGoods")) {
				orderDTO = (OrderDTO) session.getAttribute("orderInfo");
				session.removeAttribute("action");
			} else {
				orderDTO = _orderDTO;
			}
		}

		// myOrderList에 선택한 상품정보 orderDTO를 리다이렉트.
		List myOrderList = new ArrayList<OrderDTO>();
		myOrderList.add(orderDTO);
		session.setAttribute("myOrderList", myOrderList);
		log.info("폼태그로 전송하는 정보"+myOrderList.toString());

		// + 회원정보와 함께 리다이렉트.
		MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
		session.setAttribute("orderer", memberInfo);
		log.info("폼태그로 전송하는 정보"+memberInfo.toString());

		return "/order/orderEachGoods";
	}
	// 다중주문
	@RequestMapping(value = "/orderAllCartGoods", method = {RequestMethod.POST})
	public String orderAllCartGoods(@RequestParam("cart_goods_qty") String[] cart_goods_qty,
									HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		List myOrderList = new ArrayList<OrderDTO>();

		//장바구니 리스트를 받아 저장
		Map cartMap = (Map) session.getAttribute("cartMap");
		List<GoodsDTO> myGoodsList = (List<GoodsDTO>) cartMap.get("myGoodsList");

		//회원정보를 받아 저장
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberInfo");

		//상품:수량으로 input에 저장해 넘겻던 정보를 이용할 것임.
		//cart_goods_qty, 받은 input의 수만큼 for문.
		for (int i = 0; i < cart_goods_qty.length; i++) {
			//상품:수량을 나눠 확인.
			String[] cart_goods = cart_goods_qty[i].split(":");
			for (int j = 0; j < myGoodsList.size(); j++) {
				//상품 객체 get
				GoodsDTO goodsDTO = myGoodsList.get(j);
				//상품 id get
				int goods_id = goodsDTO.getGoods_id();
				//goodsid와 전달받은 상품 id값이 같을때
				if (goods_id == Integer.parseInt(cart_goods[0])) {
					//주문객체를 생성한다.
					OrderDTO _orderDTO = new OrderDTO();
					//해당상품 title저장
					String goods_title = goodsDTO.getGoods_title();
					//해당상품 수량 저장
					int goods_sales_price = goodsDTO.getGoods_sales_price();
					//해당상품 fileName도 저장해
					String goods_fileName = goodsDTO.getGoods_fileName();
					//주문객체에 set
					_orderDTO.setGoods_id(goods_id);
					_orderDTO.setGoods_title(goods_title);
					_orderDTO.setGoods_sales_price(goods_sales_price);
					_orderDTO.setGoods_fileName(goods_fileName);
					_orderDTO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
					//완성된 주문객체는 myOrderList에 쌓아간다.
					myOrderList.add(_orderDTO);
					break;
				}
			}
		}
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberDTO);
		return "/order/orderAllCartGoods";
	}
	@Override
	@RequestMapping(value = "/payToOrderGoods", method = {RequestMethod.POST})
	public String payToOrderGoods(Map<String, String> receiverMap, HttpServletRequest request,
								  Model model, RedirectAttributes redirectAttributes, HttpServletResponse response)
			throws Exception {

		//주문자 정보를 가져온다.
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("orderer");
		MemberDTO memberDTO_at = (MemberDTO) session.getAttribute("order_info");
		String member_id = memberDTO.getMember_id();
		String orderer_name = memberDTO.getMember_name();
		String orderer_hp = memberDTO.getHp1();

		//주문정보를 가져온다.
		List<OrderDTO> myOrderList = (List<OrderDTO>) session.getAttribute("myOrderList");

		//결제성공여부
		String responseCode = "";
		String responseMsg = "";
		String itemName = "";
		String orderNumber = "";
		int amount = 0;
//		주문정보를 for로 돌리며 myOrderList에 수령자정보를 담는다.
		for (int i = 0; i < myOrderList.size(); i++) {
			OrderDTO orderDTO = (OrderDTO) myOrderList.get(i);
			orderDTO.setMember_id(member_id);
			orderDTO.setReceiver_name(receiverMap.get("receiver_name"));
			orderDTO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
			orderDTO.setDelivery_address(receiverMap.get("delivery_address"));
			orderDTO.setDelivery_state(receiverMap.get("delivery_state"));

			//추후 결제시 필요할 수 있으니 주석으로 남겨둔다.
			orderDTO.setPay_method(receiverMap.get("pay_method"));
			orderDTO.setCard_com_name(receiverMap.get("card_com_name"));
			orderDTO.setCard_pay_month(receiverMap.get("card_pay_month"));
			orderDTO.setPay_orderer_hp_num(receiverMap.get("pay_orderer_hp_num"));
			orderDTO.setOrderer_hp(orderer_hp);


			String merchantId = "himedia";
			String expireMonth = receiverMap.get("expireMonth");
			String expireYear = receiverMap.get("expireYear");
			String birthday = receiverMap.get("birthday");
			String cardPw = receiverMap.get("cardPw");
			String userName = memberDTO.getMember_name();

			String cardNo = receiverMap.get("cardNo");
			String quota = receiverMap.get("card_pay_month");
			String apiCertKey = "ac805b30517f4fd08e3e80490e559f8e";
			String timestamp = "2023020400000000";



			String url = "https://api.testpayup.co.kr/v2/api/payment/" + merchantId + "/keyin2";
			Map<String, String> map = new HashMap<String, String>();
			Map<String, Object> returnMap = new HashMap<String, Object>();
			map.put("merchantId", merchantId);
			map.put("orderNumber", orderNumber);
			map.put("expireMonth", expireMonth);
			map.put("expireYear", expireYear);
			map.put("birthday", birthday);
			map.put("cardPw", cardPw);
			map.put("amount", Integer.toString(amount));
			map.put("itemName", itemName);
			map.put("userName", userName);
			map.put("cardNo", cardNo);
			map.put("quota", quota);

			map.put("timestamp", timestamp);




			return "redirect:/mypage/listMyOrderHistory";
		}


		return member_id;
	}
	@RequestMapping(value = "/orderResult", method = {RequestMethod.POST} )
	public String sopangPay(@RequestParam Map<String, String> map, HttpServletRequest request,
							HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws Exception {
		response.setContentType("text/html;charset=UTF-8");

		log.info("post form 진입");
		log.info("sopangPay");
		//주문정보를 가져온다.
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("orderer");
		String member_id = memberDTO.getMember_id();
		String orderer_name = memberDTO.getMember_name();
		String orderer_hp = memberDTO.getHp1();
		int goods_id =orderDTO.getGoods_id();
		List<OrderDTO> myOrderList = (List<OrderDTO>) session.getAttribute("myOrderList");

		//주문정보를 for로 돌리며 myOrderList에 수령자정보를 담는다.
		for (int i = 0; i < myOrderList.size(); i++) {
			OrderDTO orderDTO = (OrderDTO) myOrderList.get(i);

			orderDTO.setMember_id(member_id);
			orderDTO.setGoods_id(goods_id);
			orderDTO.setReceiver_name(map.get("receiver_name"));
			orderDTO.setReceiver_hp1(map.get("receiver_hp1"));
			orderDTO.setDelivery_address(map.get("delivery_address"));
			//추후 결제시 필요할 수 있으니 주석으로 남겨둔다.
			orderDTO.setPay_method(map.get("pay_method"));
			orderDTO.setCard_com_name(map.get("card_com_name"));
			orderDTO.setCard_pay_month(map.get("card_pay_month"));
			orderDTO.setPay_orderer_hp_num(map.get("pay_orderer_hp_num"));
			orderDTO.setOrderer_hp(orderer_hp);

			myOrderList.set(i, orderDTO);

		}
		log.info("receiver_hp1"+map.get("receiver_hp1"));
		if (map.get("card_pay_month").equals("0")){
			map.replace("card_pay_month","일시불");
		}
		model.addAttribute("myOrderInfo",map);
		model.addAttribute("myOrderList",myOrderList);
		log.info("모델 : " + myOrderList.toString());
		log.info("receiver_name" +orderDTO.getReceiver_name());

		orderService.addNewOrder(myOrderList);

		return "/order/orderResult";
	}




	@Override
	public String payFail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
}