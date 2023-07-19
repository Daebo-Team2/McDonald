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
	  body {
		background-image: linear-gradient(to bottom, #fffad7, #fff5d7, #fff1d9, #ffeddb, #ffeadd);
	  }

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
        border: 1px solid #DA291C;
  		border-radius: 0.375rem;
  		background-color: #DA291C;
  		transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
      }
	 .btn:hover {
		background-color: #FFCC00;
		transition: 0.7s;
		color: black;
		border: 1px solid #FFCC00;
	
	 }
      #name {
        font-weight: 700;
        font-size: 100px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      
      .btn-primary:active{	
      	background-color: #FFCC00 !important;
      	border-color: #FFCC00 !important;
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
