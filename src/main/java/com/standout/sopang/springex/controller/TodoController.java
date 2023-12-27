package com.standout.sopang.springex.controller;

import com.standout.sopang.member.dto.MemberDTO;
import com.standout.sopang.springex.dto.PageRequestDTO;
import com.standout.sopang.springex.dto.PageResponseDTO;
import com.standout.sopang.springex.dto.TodoDTO;
import com.standout.sopang.springex.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;


@Controller("TodoController")
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private final TodoService todoService;

    @Autowired
    private MemberDTO memberDTO;

    private int goods_id_t_shopping_goods;

    @GetMapping("/register")
    public void register() {
        log.info("GET todo register.......");

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid TodoDTO todoDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request, HttpServletResponse response
    ) {

        log.info("POST todo register......." + todoDTO.toString());
        HttpSession session = request.getSession();
        memberDTO = (MemberDTO) session.getAttribute("memberInfo");
        log.info("memberDTO : " + memberDTO.toString());
        String member_id = memberDTO.getMember_id();
        String contextPath = request.getContextPath();
        if (member_id == null) {
            log.info("로그인 정보 없음");
            return "noId";
        }

        if (bindingResult.hasErrors()) {
            log.info("값을 입력해야함");
            log.info("todoDTO : " + todoDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        //101번 반복 등록
//        if(true) {
//            for(int i = 0; i < 101; i++ ) {
//                log.info("todoDTO : " + todoDTO);
//                todoService.register(todoDTO);
//            }
//        }
        log.info("todoDTO : " + todoDTO);
        todoService.register(todoDTO);

        return "redirect:/goods/goodsDetail?goods_id=" + todoDTO.getGoods_id_t_shopping_goods();
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) {

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info("todoDTO : " + todoDTO);

        model.addAttribute("dto", todoDTO);
    }


    @PostMapping("/remove")
    public String remove(Long tno, TodoDTO todoDTO, HttpServletRequest request) {

        log.info("-------------remove------------------");
        log.info("tno: " + tno);
        todoService.remove(tno);

        return "redirect:/goods/goodsDetail?goods_id=" + todoDTO.getGoods_id_t_shopping_goods();
    }

    @PostMapping("/modify")
    public String modify(
            @Valid TodoDTO todoDTO,
            BindingResult bindingResult,
            PageRequestDTO pageRequestDTO,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        HttpSession session = request.getSession();
        memberDTO = (MemberDTO) session.getAttribute("memberInfo");
        String member_id = memberDTO.getMember_id();

        if (todoDTO.getWriter() == null) {
            todoDTO = todoService.getOne(todoDTO.getTno()); //tno 값을 가지고 새로운 todoDTO 데이터를 받아옴
            log.info("todoDTO : " + todoDTO.getWriter());
            log.info("member_id : " + member_id);
        }
        if (member_id != null && todoDTO.getWriter() != null && !member_id.equals(todoDTO.getWriter())) {
            log.info("작성자가 아님");
            return "noWriter";
        }
        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/goods/goodsDetail?goods_id=" + todoDTO.getGoods_id_t_shopping_goods();
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public void list(@RequestParam Map<String, Object> goods_id, Model model, TodoDTO todoDTO) {
        log.info("list get 호출");
        getGoodsId(goods_id, model, todoDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public PageResponseDTO<TodoDTO> list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                         Model model, HttpServletRequest request
    ) {

        log.info("list post 호출");

//        if (bindingResult.hasErrors()) {
//            log.info("에러가 발생함");
//            pageRequestDTO = PageRequestDTO.builder().build();
//        }
        pageRequestDTO.setGoods_id_t_shopping_goods(goods_id_t_shopping_goods);

        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));

        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);

        HttpSession session = request.getSession();
        session.setAttribute("responseDTO", pageResponseDTO);
        log.info("list가 성공적으로 출력됨");
        return pageResponseDTO;
    }

    public int getGoodsId(@RequestParam Map<String, Object> goods_id, Model model, TodoDTO todoDTO) {
        todoDTO.setGoods_id_t_shopping_goods(Integer.parseInt((String) goods_id.get("goods_id")));
        return goods_id_t_shopping_goods = todoDTO.getGoods_id_t_shopping_goods();
    }

}