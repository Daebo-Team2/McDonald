<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
    crossorigin="anonymous"
    defer
    ></script>
    <title>${sessionScope.login.name}점</title>
    <style>
      .storeName {
        display: flex;
        flex-direction: row;
        justify-content: center;
        margin-top: 180px;
        margin-bottom: 120px;
      }
      .buttons {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
      }
      .buttons > .btn {
        font-size: 60px;
      }
      #name {
        font-weight: 700;
        font-size: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
    </style>
  </head>
  <body>
    <div>
      <div class="storeName">
        <div>
          <img src="${pageContext.request.contextPath}/image/logo2.png" width="240px" height="180px" />
        </div>
        <div id="name">${sessionScope.login.name}</div>
        <div>
          <img src="${pageContext.request.contextPath}/image/logo2.png" width="240px" height="180px" />
        </div>
      </div>
      <div class="buttons">
        <button type="button" class="btn btn-primary btn-lg" onclick="location.href='${pageContext.request.contextPath}/page/enterkiosk'">키오스크</button>
        <button type="button" class="btn btn-primary btn-lg" onclick="location.href='${pageContext.request.contextPath}/page/admin'">관리자</button>
      </div>
    </div>

  </body>
</html>
