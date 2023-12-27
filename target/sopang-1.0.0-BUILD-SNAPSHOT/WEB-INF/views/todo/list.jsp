<%@ page import="com.standout.sopang.springex.dto.PageResponseDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 기존의 <h1>Header</h1> -->
        <div class="row">
            <div class="col">
<%--                <nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--                    <div class="container-fluid">--%>
<%--                        <a class="navbar-brand" href="#">Navbar</a>--%>
<%--                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"--%>
<%--                                data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"--%>
<%--                                aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                            <span class="navbar-toggler-icon"></span>--%>
<%--                        </button>--%>
<%--                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">--%>
<%--                            <div class="navbar-nav">--%>
<%--                                <a class="nav-link active" aria-current="page" href="#">Home</a>--%>
<%--                                <a class="nav-link" href="#">Features</a>--%>
<%--                                <a class="nav-link" href="#">Pricing</a>--%>
<%--                                <a class="nav-link disabled">Disabled</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </nav>--%>
            </div>
        </div>
        <!-- header end -->
        <!-- 기존의 <h1>Header</h1>끝 -->
        <div class="row content">
            <div class="col">
                <div class="card">
<%--                    <div class="card-body">--%>
<%--                        <h5 class="card-title">Search </h5>--%>
<%--                        <form action="/todo/list" method="post">--%>
<%--                            <input type="hidden" name="size" value="${pageRequestDTO.size}">--%>
<%--&lt;%&ndash;                            <div class="mb-3">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <input type="checkbox" name="finished" ${pageRequestDTO.finished?"checked":""} >완료여부&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--                            <div class="mb-3">--%>
<%--                                <input type="checkbox" name="types"--%>
<%--                                       value="searchTitle" ${pageRequestDTO.checkType("searchTitle")?"checked":""}>제목--%>
<%--                                <input type="checkbox" name="types"--%>
<%--                                       value="searchWriter"  ${pageRequestDTO.checkType("searchWriter")?"checked":""}>작성자--%>
<%--                                <input type="text" name="keyword" class="form-control"--%>
<%--                                       value='<c:out value="${pageRequestDTO.keyword}"/>'>--%>
<%--                            </div>--%>
<%--                            <div class="input-group mb-3 dueDateDiv">--%>
<%--                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">--%>
<%--                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">--%>
<%--                            </div>--%>
<%--                            <div class="input-group mb-3">--%>
<%--                                <div class="float-end">--%>
<%--                                    <button class="btn btn-primary" id="search">Search</button>--%>
<%--                                    <button class="btn btn-info clearBtn" type="reset">Clear</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </div>--%>
                </div>

            </div>
        </div>

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        한줄평가
                    </div>
                    <div class="card-body">
<%--                        <h5 class="card-title">한줄평가</h5>--%>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">글 내용</th>
                                <th scope="col">작성자</th>
                                <th scope="col">등록일자</th>
<%--                                <th scope="col">답변여부</th>--%>
                            </tr>
                            </thead>
                            <tbody id="boardData">
                            <c:forEach items="${responseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.tnoNumber}"/></th>
                                    <td>
                                        <a href="/todo/read?tno=${dto.tnoNumber}&${pageRequestDTO.link}"
                                           class="text-decoration-none" data-tno="${dto.tnoNumber}">
                                            <c:out value="${dto.title}"/>
                                        </a>
                                    </td>
                                    <td><c:out value="${dto.content}"/></td>
                                    <td><c:out value="${dto.writer}"/></td>
                                    <td><c:out value="${dto.dueDate}"/></td>
<%--                                    <td><c:out value="${dto.finished}"/></td>--%>
                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>

                        </table>
                        <button id="register" class="btn btn-primary" name="register">등록하기</button>


                        <div class="float-end">
                            <ul class="pagination flex-wrap" id="boardDataNum">
<%--                                <c:if test="${pageResponseDTO.prev}">--%>
<%--                                    <li class="page-item">--%>
<%--                                        <a class="page-link" data-num="${pageResponseDTO.start -1}">Previous</a>--%>
<%--                                    </li>--%>
<%--                                </c:if>--%>

<%--                                <c:forEach begin="${pageResponseDTO.start}" end="${pageResponseDTO.end}" var="num">--%>
<%--                                    <li class="page-item ${pageResponseDTO.page == num? "active":""} ">--%>
<%--                                        <a class="page-link" data-num="${num}">${num}</a></li>--%>
<%--                                </c:forEach>--%>

<%--                                <c:if test="${pageResponseDTO.next}">--%>
<%--                                    <li class="page-item">--%>
<%--                                        <a class="page-link" data-num="${pageResponseDTO.end + 1}">Next</a>--%>
<%--                                    </li>--%>
<%--                                </c:if>--%>
                            </ul>
                        </div>
<%--                    <script>--%>
<%--                            document.querySelector(".pagination").addEventListener("click", function (e) {--%>
<%--                                e.preventDefault()--%>
<%--                                e.stopPropagation()--%>

<%--                                const target = e.target--%>

<%--                                if (target.tagName !== 'A') {--%>
<%--                                    return--%>
<%--                                }--%>
<%--                                const num = target.getAttribute("data-num")--%>

<%--                                const formObj = document.querySelector("form")--%>

<%--                                formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`--%>

<%--                                formObj.submit();--%>

<%--                            }, false)--%>
<%--                            document.querySelector(".clearBtn").addEventListener("click", function (e) {--%>
<%--                                e.preventDefault()--%>
<%--                                e.stopPropagation()--%>

<%--                                self.location = '/todo/list'--%>

<%--                            }, false)--%>
<%--                        </script>--%>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="row content">
    </div>
    <div class="row footer">
        <!--<h1>Footer</h1>-->

        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1 ">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>

    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>