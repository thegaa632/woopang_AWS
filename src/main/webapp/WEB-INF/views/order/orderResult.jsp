<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="myOrderInfo" value="${myOrderInfo}"/>
<div class="container">
  <div class="row ms-5 ps-5">

    <div class="mt-5 p-0 ps-5 align-items-center">
      <p class="fs-5 fw-bold mb-3">주문상품</p>
      <div class="border-top border-main border-2 mt-2">
        <div class="shadow-sm p-4 pt-2 mt-3 rounded border border-light">

          <!-- myOrderList를 돌려 각 주문상품 정보를 표시한다. -->
          <c:forEach var="item" items="${myOrderList}">

            <a
                    href="${contextPath}/goods/goodsDetail?goods_id=${item.goods_id }"
                    class="shadow-sm p-0 mt-3 rounded border border-light d-flex justify-content-between text-decoration-none">
              <div class="d-flex">
                <div class="d-flex align-items-center p-4">
                  <div class="d-flex justify-content-between">
                    <div class="d-flex">

                      <!-- 상품이미지 -->
                      <img
                              src="${contextPath}/thumbnails?goods_id=${item.goods_id}&fileName=${item.goods_fileName}"
                              width="64px">
                      <!-- 상품이미지 -->

                      <!-- 상품id와 filename등 사용자에게 보여주지않아도 되는 정보들은 hidden type으로 넘긴다. -->
                      <input type="hidden" id="h_goods_id" name="h_goods_id"
                             value="${item.goods_id}"/> <input type="hidden"
                                                               id="h_goods_fileName"
                                                               name="h_goods_fileName"
                                                               value="${item.goods_fileName}"/>

                      <div class="ms-3">

                        <!-- 상품이름 -->
                        <p class="mb-1 mt-1 small">${item.goods_title }</p>
                        <input type="hidden" id="h_goods_title"
                               name="h_goods_title" value="${item.goods_title }"/>
                        <!-- 상품이름 -->

                        <!-- 상품가격 표시-->
                        <p class="mb-0 text-secondary">
                          <!-- 상품 낱개가격 -->
                          <span>
															<fmt:formatNumber value="${item.goods_sales_price}"
                                                                              pattern="#,###"/>
															</span> 원 <span> ·
															</span> <input type="hidden" id="h_each_goods_price"
                                                                           name="h_each_goods_price"
                                                                           value="${item.goods_sales_price * item.order_goods_qty}"/>
                          <!-- 상품 낱개가격 -->

                          <!-- 상품수량 -->
                          <span>${item.order_goods_qty }</span>개 <input
                                type="hidden" id="h_order_goods_qty"
                                name="h_order_goods_qty"
                                value="${item.order_goods_qty}"/>


                        </p>
                        <!-- 상품가격 표시 -->

                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </a>

            <!-- 상품 가격정보 -->
            <c:set var="final_total_order_price"
                   value="${final_total_order_price+ item.goods_sales_price* item.order_goods_qty}"/>
            <c:set var="total_order_price"
                   value="${total_order_price+ item.goods_sales_price* item.order_goods_qty}"/>
            <c:set var="total_order_goods_qty"
                   value="${total_order_goods_qty+item.order_goods_qty }"/>
            <!-- 상품 가격정보 -->

          </c:forEach>
          <!-- myOrderList를 돌려 각 주문상품 정보를 표시한다. -->


        </div>
      </div>
    </div>
    <div class="mt-5 p-0 ps-5 align-items-center">

      <div class="ps-4">


        <DIV class="clear"></DIV>
        <form name="form_order">
          <br>
          <br>
          <p class="fs-3 fw-bold">배송지 정보</p>
          <DIV class="detail_table">
            <TABLE class="table border-top mb-0 small fw-light">
              <TBODY>
              <TR class="dot_line">
                <TD class="fixed_join">받으실 분</TD>
                <TD>
                  ${myOrderInfo.receiver_name}
                </TD>
              </TR>
              <TR class="dot_line">
                <TD class="fixed_join">연락처</TD>
                <TD>
                  ${myOrderInfo.receiver_hp1}</TD>
              </TR>


              <TR class="dot_line">
                <TD class="fixed_join">받는주소</TD>
                <td>
                  (${myOrderInfo.zipcode}) ${myOrderInfo.delivery_address}

                </td>
              </TR>
              <TR class="dot_line">
                <TD class="fixed_join">배송요청사항</TD>
                <TD>
                  ${myOrderInfo.delivery_content}
                </TD>
              </TR>
              </TBODY>
            </TABLE>

          </DIV>

          <DIV class="clear"></DIV>
          <br>
          <br>
          <br>
          <p class="fs-3 fw-bold">결제 정보</p>
          <DIV class="detail_table">
            <TABLE class="table border-top mb-0 small fw-light">
              <TBODY>
              <TR class="dot_line">
                <TD class="fixed_join">결제방법</TD>
                <TD>
                  ${myOrderInfo.pay_method }
                </TD>
              </TR>
              <TR class="dot_line">
                <TD class="fixed_join">결제카드</TD>
                <TD>
                  ${myOrderInfo.card_com_name}
                </TD>
              </TR>
              <TR class="dot_line">
                <TD class="fixed_join">할부기간</TD>
                <TD>
                  ${myOrderInfo.card_pay_month }
                </TD>
              </TR>
              <TR class="dot_line">
                <TD class="fixed_join">결제금액</TD>
                <TD>
                  ${myOrderInfo.h_final_total_Price}
                </TD>
              </TR>
              </TBODY>
            </table>
          </DIV>
        </form>
        <DIV class="clear"></DIV>
        <br>
        <br>
        <br>
        <a href="${contextPath}/main/main">
          <IMG width="75" alt="" src="${contextPath}/resources/img/house.png">
        </a>
      </div>
    </div>
  </div>
</div>

<br>
<br>
<DIV class="clear"></DIV>




