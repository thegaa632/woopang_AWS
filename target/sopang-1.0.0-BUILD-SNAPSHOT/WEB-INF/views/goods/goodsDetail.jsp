<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="goods" value="${goodsMap.goodsDTO}"/>
<c:set var="imageList" value="${goodsMap.imageList }"/>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<div class="container">
    <div class="row">
        <div class="p-0 align-items-center gap-3 mt-5">
        </div>
    </div>

    <div class="row">

        <div class="p-0 d-flex">

            <!-- 상품이미지 -->
            <div class="d-flex">
                <!-- tab caller -->
                <div class="list-group me-3" id="list-tab" role="tablist">
                    <a class="active mb-3 back_eee" id="detailThumb1"
                       data-bs-toggle="list" href="#detailThumb01" role="tab"
                       aria-controls="detailThumb01"> <img
                            src="${contextPath}/thumbnails?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}"
                            style="width: 50px"></a> <a class="mb-3 back_eee"
                                                        id="detailThumb2" data-bs-toggle="list" href="#detailThumb02"
                                                        role="tab" aria-controls="detailThumb02"> <img
                        src="${contextPath}/resources/img/logo_square.png"
                        style="width: 50px"></a>
                </div>
                <!-- tab caller -->

                <!-- tab 본문 -->
                <!-- 배경설정 back_eee -->
                <div class="tab-content back_eee" id="nav-tabContent">
                    <div class="tab-pane show active" id="detailThumb01"
                         role="tabpanel" aria-labelledby="detailThumb1">
                        <img
                                src="${contextPath}/download?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}"
                                style="width: 410px">
                    </div>
                    <div class="tab-pane back_eee" id="detailThumb02" role="tabpanel"
                         aria-labelledby="detailThumb2">
                        <img src="${contextPath}/resources/img/logo_square.png">
                    </div>
                </div>
                <!-- tab 본문 -->

            </div>
            <!-- 상품이미지 -->


            <!-- 상품정보 -->
            <div class="ps-4 w-100">
                <!-- 카테고리 -->
                <p class="fs-6 mb-1">${goods.goods_sort }</p>
                <!-- 상품명 -->
                <p class="fs-3 fw-bold">${goods.goods_title }</p>
                <hr>
                <!-- 가격 및 수량, 수량은 고정 -->
                <p class="fs-6 mb-1">
               <span class="fs-4 text-danger fw-bold">
               <fmt:formatNumber value="${goods.goods_sales_price }" pattern="#,###"/>
               </span>원
                    · 1개
                </p>

                <div class="d-flex gap-2 mt-4">
                    <input type="hidden" id="goods_qty" name="order_goods_qty"
                           value="1">

                    <!-- 장바구니 담기, goods_id값과 함께 add_cart 실행 -->
                    <a href="javascript:add_cart('${goods.goods_id}')"
                       class="btn btn-lg fw-bold border-main rounded-0 d-block flex-fill">장바구니담기</a>
                    <!-- 주문하기, 상품정보와 함께 fn_order_each_goods실행  -->
                    <a
                            href="javascript:fn_order_each_goods('${goods.goods_id }','${goods.goods_title }','${goods.goods_sales_price}','${goods.goods_fileName}');"
                            class="btn btn-lg fw-bold btn-main rounded-0 d-block flex-fill">바로구매</a>
                </div>
            </div>
            <!-- 상품정보 -->


        </div>


        <!-- 하단 상품상세정보 -->
        <div class="mt-5 border-top border-secondary border-3 p-0">
            <!-- tab Caller -->
            <ul class="nav nav-tabs">
                <li class="nav-item"><a
                        class="nav-link rounded-0 text-center py-3 fw-bold active"
                        id="detailInfo1" data-bs-toggle="list" href="#detailInfo01"
                        role="tab" aria-controls="detailInfo01" style="width: 250px">
                    상품상세 </a></li>
                <li class="nav-item"><a
                        class="nav-link rounded-0 py-3 text-center fw-bold"
                        id="detailInfo2" data-bs-toggle="list" href="#detailInfo02"
                        role="tab" aria-controls="detailInfo02" style="width: 250px">
                    배송/교환/반품 안내</a></li>
                <li class="nav-item"><a
                        class="nav-link rounded-0 py-3 text-center fw-bold"
                        id="detailInfo3" data-bs-toggle="list" href="#detailInfo03"
                        role="tab" aria-controls="detailInfo03" style="width: 250px">
                    한줄 평가</a></li>
            </ul>
            <!-- tab Caller -->

            <!-- tab 본문 -->
            <div class="tab-content mt-5" id="nav-tabContent">
                <div class="tab-pane show active" id="detailInfo01" role="tabpanel"
                     aria-labelledby="detailInfo1">
                    <!-- 상세이미지 리스트 foreach로 뿌림 -->
                    <c:forEach var="image" items="${imageList }">
                        <div class="mb-5"
                             style="background:url(${contextPath}/resources/img/back1.jpg);background-size: cover;">
                            <img class=""
                                 src="${contextPath}/download?goods_id=${goods.goods_id}&fileName=${image.fileName}"
                                 style="width: 1200px;">
                        </div>
                    </c:forEach>
                    <!-- 상세이미지 리스트 foreach로 뿌림 -->
                </div>

                <!-- 상품/배송정보등의 외 정보 -->
                <div class="tab-pane" id="detailInfo02" role="tabpanel"
                     aria-labelledby="detailInfo2">
                    <img src="${contextPath}/resources/img/goods/detailInfo.jpg">
                </div>
                <!-- 상품/배송정보등의 외 정보 -->

                <!-- QnA 및 문의사항 정보 -->
                <div class="tab-pane" id="detailInfo03" role="tabpanel"
                     aria-labelledby="detailInfo3">
                    <%--여긴 댓글 등록 부분--%>
                    <%--리스트 부분--%>
                    <!-- QnA 및 문의사항 정보 -->
                </div>
                <!-- tab 본문 -->
            </div>
            <!-- 하단 상품상세정보 -->

        </div>
    </div>

        <%
        String goods_id = request.getParameter("goods_id");
        session.setAttribute("goods_id", goods_id);
    %>

    <script>

        document.getElementById("detailInfo3").addEventListener("click", boardNumData);

        var contextPath = '<%= request.getContextPath() %>'
        console.log("contextPath : " + contextPath);

        function boardNumData() {
            urlData = window.location.href;
            let url = new URL(urlData);
            let goods_id = url.searchParams.get("goods_id");

            console.log("goods_id : " + goods_id);

            // div를 클릭했을 때의 이벤트 핸들러
            console.log('html클릭함');

            // Ajax를 사용하여 서버에 데이터 요청
            $.ajax({
                type: "GET",
                url: contextPath + "/todo/list", //  URL을 지정
                data: {"goods_id": goods_id},
                dataType: "HTML",
                success: function (data) {
                    console.log("data")
                    let htmlLocation = document.getElementById("detailInfo03");
                    htmlLocation.innerHTML = data;
                    boardList();
                    document.getElementById("boardDataNum").addEventListener("click", function (event) {
                        movePageing(event);
                    });
                    document.getElementById("register").addEventListener("click", function (event) {
                        register(event);
                    });
                    // document.getElementById("search").addEventListener("click", function (event) {
                    //     boardList(event);
                    // });
                },

                error: function e(err) {
                    console.error("err : " + err.toString());
                }
            });
        }

        function boardList() {
            // div를 클릭했을 때의 이벤트 핸들러
            console.log('json클릭함');
            // Ajax를 사용하여 서버에 데이터 요청
            $.ajax({
                type: "POST",
                url: contextPath + "/todo/list", //  URL을 지정
                dataType: "JSON",
                success: function (data) {
                    console.log("data : " + data);
                    console.log("boardList 진입");
                    updatePage(data);
                    error: function e(xhr, status, error) {
                        console.error("Error loading data. Status: " + status + ", Error: " + error);
                    }
                }
            });
        }

        function viewread(event) {
            // div를 클릭했을 때의 이벤트 핸들러
            console.log('viewread 클릭함');
            let tno = event.target.getAttribute('name');
            console.log("tno : " + tno);
            // Ajax를 사용하여 서버에 데이터 요청
            $.ajax({
                type: "GET",
                url: contextPath + "/todo/read?tno=" + tno, //  URL을 지정
                dataType: "html",
                success: function (data) {
                    let htmlLocation = document.getElementById("detailInfo03");
                    htmlLocation.innerHTML = data;
                    document.getElementById("modify").addEventListener("click", function (event) {
                        modify(event);
                    });
                    error: function e(xhr, status, error) {
                        console.error("Error loading data. Status: " + status + ", Error: " + error);
                    }
                }
            });
        }

        function movePageing(event) {
            console.log('이벤트 진입 :' + event);
            if (event.target.tagName === "A") {
                const clickedPage = parseInt(event.target.getAttribute("data-num"));
                console.log(clickedPage);
                //form을 이용해 페이지 데이터를 전송함
                const formData = new FormData();
                formData.append('page', clickedPage);
                // fetch는 JavaScript에서 제공하는 네트워크 요청을 간단하게 만들기 위한 API입니다.
                // fetch 함수는 Promise를 반환하며, 네트워크 요청을 만들고 응답을 처리하는 데 사용됩니다.
                fetch(`${contextPath}/todo/list`, {
                    method: 'POST',
                    body: formData
                })
                    .then(response => {
                        //fetch는 404,500등 에러코드를 띄우지 않음으로 response.ok 함수를 이용하여 오류코드를 확인할수 있다.
                        if (!response.ok) {
                            const statusCode = response.status; //코드 오류
                            const statusText = response.statusText; //오류 메시지

                            // 오류 메시지를 생성하고 throw
                            const errorMessage = `네트워크 오류가 발생함. Status: ${statusCode} ${statusText}`;
                            throw new Error(errorMessage);
                        }
                        return response.json()
                    })
                    .then(newData => {
                        // 받아온 데이터를 사용하여 페이지 업데이트
                        updatePage(newData);
                    })
                    .catch(error => {
                        console.error("Error fetching data:", error);
                    });
            }
        }

        function updatePage(data) {
            //<======게시판 목록 부분======>
            console.log('new data 받음 :' + data);
            let numData = data;
            console.log('데이터(data) 전송 성공(success)!');
            // 테이블 데이터 출력
            let boardData = document.getElementById("boardData");
            boardData.innerHTML = ""; // 혹시 모를 기존 데이터 초기화
            let dtoListData = numData.dtoList;
            //받아온 데이터를 출력함 일단 직접 넣는걸로...
            dtoListData.forEach(function (dto) {
                let row = document.createElement("tr");
                //번호
                let tnoCell = document.createElement("td");
                tnoCell.textContent = dto.tnoNumber;
                row.appendChild(tnoCell);
                //제목
                let titleCell = document.createElement("td");
                let titleLink = document.createElement("a");
                titleLink.textContent = dto.title;
                titleLink.setAttribute("onclick", "viewread(event)");
                titleLink.setAttribute("name", dto.tno);
                titleCell.appendChild(titleLink);
                row.appendChild(titleCell);
                //내용
                let contentCell = document.createElement("td");
                contentCell.textContent = dto.content;
                row.appendChild(contentCell);
                //글쓴이
                let writerCell = document.createElement("td");
                writerCell.textContent = dto.writer;
                row.appendChild(writerCell);
                //날짜
                let dueDateCell = document.createElement("td");
                dueDateCell.textContent = dto.dueDate;
                row.appendChild(dueDateCell);
                //확인여부
                // let finishedCell = document.createElement("td");
                // finishedCell.textContent = dto.finished;
                // row.appendChild(finishedCell);

                boardData.appendChild(row);
                //<======게시판 목록 부분======>
            })
            //<======게시판 페이징 부분======>
            let responseDTO = data; //햇갈리니까 responseDTO 그대로 사용함
            // let pageResponseDTO = data
            let boardDataNum = document.getElementById("boardDataNum");
            boardDataNum.innerHTML = ""; // 혹시 모를 기존 데이터 초기화
            // 페이징 처리 로직 추가
            // responseDTO에서 start, end, page 등을 활용하여 페이지 번호를 동적으로 생성
            // 10페이지 초과시 11페이지
            if (responseDTO.prev) {
                let li = document.createElement("li");
                li.className = "page-item"; // 이전 버튼을 감싸는 li의 클래스
                let a = document.createElement("a");
                a.className = "page-link";
                a.textContent = "Previous";
                a.setAttribute("data-num", responseDTO.start - 1); // 이전 페이지 번호를 속성에 추가
                li.appendChild(a);
                boardDataNum.appendChild(li); // boardDataNum에 이전 페이지 링크를 추가
            }
            for (let num = responseDTO.start; num <= responseDTO.end; num++) {
                // var paginationUl = $("#boardDataNum");
                let li = document.createElement("li");
                li.className = "page-item " + (responseDTO.page == num ? "active" : "");
                //셀 생성하고 페이지 번호에 맞는 칸 추가
                let a = document.createElement("a");
                a.className = "page-link";
                a.textContent = num;
                a.setAttribute("data-num", num);
                li.appendChild(a);
                //번호부여
                boardDataNum.appendChild(li);
            }
            //10페이지 초과시 11페이지로 이동
            if (responseDTO.next) {
                let li = document.createElement("li");
                li.className = "page-item"; // 이전 버튼을 감싸는 li의 클래스
                let a = document.createElement("a");
                a.className = "page-link";
                a.textContent = "Next";
                a.setAttribute("data-num", responseDTO.end + 1); // 이전 페이지 번호를 속성에 추가
                li.appendChild(a);
                boardDataNum.appendChild(li); // boardDataNum에 다음 페이지 링크를 추가
            }
        }

        function register(event) {
            console.log("register 진입");
            console.log("contextPath : " + contextPath);

            $.ajax({
                type: "POST",
                url: contextPath + "/todo/register",
                dataType: "html",
                success: function (data) {
                    console.log("register 로딩 성공");
                    detailInfo03.innerHTML = data;
                    console.log("등록 작동")
                    const registForm = document.getElementById("registForm")
                    console.log("registForm :" + registForm);
                    document.getElementById("regist").addEventListener("click", function (e) {
                        console.log("등록 작동")
                        e.preventDefault()
                        e.stopPropagation()
                        registForm.action = contextPath + "/todo/register"
                        registForm.method = "post"

                        registForm.submit()

                    }, false);
                },
                error: function e(data, textStatus) {
                    console.error("오류발생!");
                    alert("로그인 후 등록해주세요!");
                    window.location.assign(contextPath + "/member/login");
                }
            })
        }

        function modify(event) {
            console.log("modify 진입");
            let dto = event.target.getAttribute('value');
            console.log("tno : " + dto);
            $.ajax({
                type: "post",
                url: contextPath + "/todo/modify?tno=" + dto,
                dataType: "html",
                success: function (data) {
                    console.log("성공");
                    detailInfo03.innerHTML = data;
                    const formObj = document.getElementById("modForm")
                    console.log("formObj :" + formObj);
                    document.getElementById("mo1").addEventListener("click", function (e) {
                        console.log("등록 작동")
                        e.preventDefault()
                        e.stopPropagation()
                        formObj.action = contextPath + "/todo/modify"
                        formObj.method = "post"

                        formObj.submit()

                    }, false);
                    document.querySelector(".btn-danger").addEventListener("click", function (e) {

                        e.preventDefault()
                        e.stopPropagation()
                        console.log("contextPath : " + contextPath);
                        formObj.action = contextPath + "/todo/remove?tno=" + dto;
                        formObj.method = "post"

                        formObj.submit()
                    }, false);
                },
                error: function e(data, textStatus) {
                    console.log("작성자가 아님");
                    alert("작성자만 수정할수 있습니다!");
                }
            })
        }

        //장바구니 추가, goods_id정보를 넘겨줌.
        function add_cart(goods_id) {
            $.ajax({
                type: "post",
                async: true,
                url: contextPath + "/cart/addGoodsInCart",
                data: {goods_id: goods_id},
                success: function (data, textStatus) {
                    if (data.trim() == 'add_success') {
                        alert("장바구니에 추가되엇습니다.");
                    } else if (data.trim() == 'already_existed') {
                        alert("이미 카트에 등록된 상품입니다.");
                    }
                },
                error: function (data, textStatus) {
                    alert("로그인 후 추가하실 수 있습니다!");
                },
                complete: function (data, textStatus) {
                }
            });
        }

        //바로 주문하기
        function fn_order_each_goods(goods_id, goods_title, goods_sales_price, fileName) {
            var total_price, final_total_price;
            var order_goods_qty = document.getElementById("order_goods_qty");
            var formObj = document.createElement("form");
            var i_goods_id = document.createElement("input");
            var i_goods_title = document.createElement("input");
            var i_goods_sales_price = document.createElement("input");
            var i_fileName = document.createElement("input");
            var i_order_goods_qty = document.createElement("input");

            let memberName = "${memberInfo.member_name }";

            if (memberName == "") {
                alert("로그인 후 구매하실 수 있습니다!");
            }/* 로그인상태가 아닌경우 안내함. */
            else {
                i_goods_id.name = "goods_id";
                i_goods_title.name = "goods_title";
                i_goods_sales_price.name = "goods_sales_price";
                i_fileName.name = "goods_fileName";
                i_order_goods_qty.name = "order_goods_qty";
                i_goods_id.value = goods_id;
                i_order_goods_qty.value = 1;//i_order_goods_qty 1로 고정
                i_goods_title.value = goods_title;
                i_goods_sales_price.value = goods_sales_price;
                i_fileName.value = fileName;

                //formObj에 해당 상품 정보를 할당해 orderEachGoods로 action
                formObj.appendChild(i_goods_id);
                formObj.appendChild(i_goods_title);
                formObj.appendChild(i_goods_sales_price);
                formObj.appendChild(i_fileName);
                formObj.appendChild(i_order_goods_qty);
                document.body.appendChild(formObj);

                formObj.method = "post";
                formObj.action = contextPath + "/order/orderEachGoods";
                formObj.submit();
            }
        }
    </script>