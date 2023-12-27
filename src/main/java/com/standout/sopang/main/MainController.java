package com.standout.sopang.main;

import com.standout.sopang.common.base.BaseController;
import com.standout.sopang.goods.dto.GoodsDTO;
import com.standout.sopang.goods.service.GoodsService;
import com.standout.sopang.goods.vo.GoodsVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Log4j2

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {

	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/main/main", method = {RequestMethod.POST, RequestMethod.GET})
	public String main(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info("메인 컨트롤러 진입");
		HttpSession session;
		session = request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String, List<GoodsDTO>> goodsMap = goodsService.listGoods();
		model.addAttribute("goodsMap", goodsMap);

		return "/main/main";
	}
}