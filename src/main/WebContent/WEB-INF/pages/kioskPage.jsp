<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>키오스크</title>
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous"
    />
    <link href="${pageContext.request.contextPath}/css/adminCommon.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/kiosk.css" rel="stylesheet" />
    <script
    src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    defer
    ></script>
    <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    defer
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <div class="header">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <img src="${pageContext.request.contextPath}/image/logo2.png" width="80" height="70" />
        </li>
        <li class="nav-item" role="presentation">
          <button
          class="nav-link active"
          id="set-tab"
          data-bs-toggle="tab"
          data-bs-target="#set-tab-pane"
          role="tab"
          aria-controls="set-tab-pane"
          aria-selected="true"
          >
            세트메뉴
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
          class="nav-link"
          id="burger-tab"
          data-bs-toggle="tab"
          data-bs-target="#burger-tab-pane"
          role="tab"
          aria-controls="burger-tab-pane"
          aria-selected="false"
          >
            버거
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
          class="nav-link"
          id="drink-tab"
          data-bs-toggle="tab"
          data-bs-target="#drink-tab-pane"
          role="tab"
          aria-controls="drink-tab-pane"
          aria-selected="false"
          >
            음료/카페
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
          class="nav-link"
          id="side-tab"
          data-bs-toggle="tab"
          data-bs-target="#side-tab-pane"
          role="tab"
          aria-controls="side-tab-pane"
          aria-selected="false"
          >
            사이드
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
          class="nav-link"
          id="dessert-tab"
          data-bs-toggle="tab"
          data-bs-target="#dessert-tab-pane"
          role="tab"
          aria-controls="dessert-tab-pane"
          aria-selected="false"
          >
            디저트
          </button>
        </li>
      </ul>
    </div>
    <div class="section">
      <div class="side">
        <div class="side-header">
          <h1>장바구니</h1>
        </div>
        <div class="side-content">
          <table class="table cart">
          </table>
        </div>
        <div class="side-footer">
          <div class="price-sum">0₩</div>
          <div class="radio-container">
            <div class="form-check form-check-inline">
              <input type="radio" class="form-check-input" name="place" value="store"/><label>매장</label>
            </div>
            <div class="form-check form-check-inline">
              <input type="radio" class="form-check-input" name="place" value="take-out"/><label>포장</label>
            </div>
          </div>
          <button class="btn btn-primary btn-lg">주문하기</button>
        </div>
      </div>

      <div class="content tab-content" id="myTabContent">
        <!-- 세트탭 -->
        <div
        class="tab-pane fade show active"
        id="set-tab-pane"
        role="tabpanel"
        aria-labelledby="set-tab"
        tabindex="0"
        >
          <c:forEach items="${setList}" var="menu">
            <div class="card" menu-num="${menu.no}">
              <img src="${pageContext.request.contextPath}${menu.image}" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title" data-bs-toggle="tooltip" data-bs-placement="bottom" data-bs-title="${menu.name}">${menu.name}</h5>
                <p class="card-text">${menu.price}₩</p>
                <button class="btn btn-primary" onclick="addBtnHandler(event)">장바구니 담기</button>
              </div>
            </div>
          </c:forEach>
        </div>

        <!-- 버거탭 -->
        <div
        class="tab-pane fade"
        id="burger-tab-pane"
        role="tabpanel"
        aria-labelledby="burger-tab"
        tabindex="0"
        >
          <c:forEach items="${burgerList}" var="menu">
            <div class="card" menu-num="${menu.no}">
              <img src="${pageContext.request.contextPath}${menu.image}" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title" data-bs-toggle="tooltip" data-bs-placement="bottom" data-bs-title="${menu.name}">${menu.name}</h5>
                <p class="card-text">${menu.price}₩</p>
                <button class="btn btn-primary" onclick="addBtnHandler(event)">장바구니 담기</button>
              </div>
            </div>
          </c:forEach>
        </div>

        <!-- 음료탭 -->
        <div
        class="tab-pane fade"
        id="drink-tab-pane"
        role="tabpanel"
        aria-labelledby="drink-tab"
        tabindex="0"
        >
          <c:forEach items="${drinkList}" var="menu">
            <div class="card" menu-num="${menu.no}">
              <img src="${pageContext.request.contextPath}${menu.image}" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title" data-bs-toggle="tooltip" data-bs-placement="bottom" data-bs-title="${menu.name}">${menu.name}</h5>
                <p class="card-text">${menu.price}₩</p>
                <button class="btn btn-primary" onclick="addBtnHandler(event)">장바구니 담기</button>
              </div>
            </div>
          </c:forEach>
        </div>

        <!-- 사이드탭 -->
        <div
        class="tab-pane fade"
        id="side-tab-pane"
        role="tabpanel"
        aria-labelledby="side-tab"
        tabindex="0"
        >
          <c:forEach items="${sideList}" var="menu">
            <div class="card" menu-num="${menu.no}">
              <img src="${pageContext.request.contextPath}${menu.image}" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title" data-bs-toggle="tooltip" data-bs-placement="bottom" data-bs-title="${menu.name}">${menu.name}</h5>
                <p class="card-text">${menu.price}₩</p>
                <button class="btn btn-primary" onclick="addBtnHandler(event)">장바구니 담기</button>
              </div>
            </div>
          </c:forEach>
        </div>

        <!-- 디저트탭 -->
        <div
        class="tab-pane fade"
        id="dessert-tab-pane"
        role="tabpanel"
        aria-labelledby="dessert-tab"
        tabindex="0"
        >
          <c:forEach items="${dessertList}" var="menu">
            <div class="card" menu-num="${menu.no}">
              <img src="${pageContext.request.contextPath}${menu.image}" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title" data-bs-toggle="tooltip" data-bs-placement="bottom" data-bs-title="${menu.name}">${menu.name}</h5>
                <p class="card-text">${menu.price}₩</p>
                <button class="btn btn-primary" onclick="addBtnHandler(event)">장바구니 담기</button>
              </div>
            </div>
          </c:forEach>
        </div>

      </div>
      <script defer src="${pageContext.request.contextPath}/js/kiosk.js"></script>
    </div>
  </body>
</html>