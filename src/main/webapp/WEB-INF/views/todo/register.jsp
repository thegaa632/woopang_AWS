<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 기존의 <h1>Header</h1> -->
<%--        <div class="row">--%>
<%--            <div class="col">--%>
<%--                <nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--                    <div class="container-fluid">--%>
<%--                        <a class="navbar-brand" href="#">Navbar</a>--%>
<%--                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                            <span class="navbar-toggler-icon"></span>--%>
<%--                        </button>--%>
<%--                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">--%>
<%--                            <div class="navbar-nav">--%>
<%--                                <a class="nav-link active" aria-current="page" href="#">Home</a>--%>
<%--                                <a class="nav-link" id="register">연</a>--%>
<%--                                <a class="nav-link" href="#">습</a>--%>
<%--                                <a class="nav-link disabled">용</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </nav>--%>
<%--            </div>--%>
<%--        </div>--%>
        <!-- header end -->
        <!-- 기존의 <h1>Header</h1>끝 -->

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        등록하기
                    </div>
                    <div class="card-body">
                        <form id="registForm">
                            <div class="input-group mb-3">
                                <span class="input-group-text">글 제목</span>
                                <input type="text" name="title" class="form-control" placeholder="글 제목을 입력하세요">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text">글 내용</span>
                                <input type="text" name="content" class="form-control" placeholder="글 내용을 입력하세요">
                            </div>


<%--                            <div class="input-group mb-3">--%>
<%--                                <span class="input-group-text">글쓴이</span>--%>
<%--                                <input type="text" name="writer" class="form-control" value="${}" readonly>--%>
<%--                            </div>--%>

                            <div class="my-4">
                                <div class="float-end">
                                    <button type="submit" class="btn btn-primary" id="regist">등록하기</button>
                                    <button type="button" class="btn btn-secondary" onclick="boardNumData()">목록으로</button>
                                </div>
                            </div>
                            <%--외래키로 연결된 세션 정보를 form으로 전송--%>
                            <input type="hidden" name="goods_id_t_shopping_goods" value="${goods_id}">
                            <input type="hidden" name="writer" value="${memberInfo.member_id}">
                        </form>

                        <script>
                            window.onload = function() {
                                console.log("레지스터 실행됨");
                            }

                            const serverValidResult = {}

                            <c:forEach items="${errors}" var="error">

                            serverValidResult['${error.getField()}'] = '${error.defaultMessage}'

                            </c:forEach>

                            console.log(serverValidResult)

                        </script>

                    </div>

                </div>
            </div>
        </div>

    </div>
    <div class="row content">
        <h1>Content</h1>
    </div>
    <div class="row footer">
        <!--<h1>Footer</h1>-->

        <div class="row   fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1 ">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>

    </div>
</div>


</body>
</html>
<script>

    // document.getElementById("add").addEventListener("click", function(event) {
    // event.preventDefault();
    // console.log("서브밋 진입");
    // let add = document.getElementById("add");
    // Promise.all([boardNumData(), add.submit()])
    //     .then(results => {
    //     console.log("모든 작업이 완료되었습니다.");
    // })
    //     .catch(error => {
    //         console.error("에러 발생: ", error);
    //     });
// })

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
