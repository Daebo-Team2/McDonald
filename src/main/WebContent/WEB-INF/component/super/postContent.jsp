<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/super/post.css" rel="stylesheet" />

<div class="title">
  <h1>문의게시판</h1>
</div>
<div class="wrapper">
  <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
    <li class="nav-item" role="presentation">
      <button
      class="nav-link active"
      type="button"
      data-bs-toggle="pill"
      role="tab"
      aria-selected="true"
      onclick="tabBtnClick(0)"
      >
        문의 대기
      </button>
    </li>
    <li class="nav-item" role="presentation">
      <button
      class="nav-link"
      type="button"
      data-bs-toggle="pill"
      role="tab"
      aria-selected="false"
      onclick="tabBtnClick(1)"
      >
        답변 완료
      </button>
    </li>
  </ul>
  <table class="table" page="post">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>가맹점</th>
        <th>작성시간</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr onclick="openViewModal(1)">
        <td>1</td>
        <td>문의글 제목 2</td>
        <td>OOO점</td>
        <td>2023/07/07 11:44:43</td>
        <td>
          <span class="green">답변완료</span>
        </td>
      </tr>
      <tr onclick="openViewModal(1)">
        <td>1</td>
        <td>
          문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목
          1문의글 제목 1
        </td>
        <td>OOO점</td>
        <td>2023/07/07 11:44:43</td>
        <td>
          <span class="yellow">답변대기</span>
        </td>
      </tr>
    </tbody>
  </table>
</div>
<div class="footer">
  <nav>
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link" href="#">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item active"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item">
        <a class="page-link" href="#">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</div>
<!-- 모달 숨겨놓기-->
<div
class="modal fade"
id="post-modal"
data-bs-backdrop="static"
tabindex="-1"
aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="modal-content">
      <!-- <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">문의 내역 조회</h3>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <h3>문의 내용</h3>
        <h5>제목</h5>
        <p>문의글 제목</p>
        <h5>내용</h5>
        <p class="post-content">
          알림 기능은 공지사항, 이벤트, 업데이트와 같은 제품 및 서비스 소식을 알리는 기능을
          말합니다. 일반적으로 리스트형 게시판을 이용하며 긴급한 내용은 팝업으로 띄우기도
          합니다. 알림 기능은 새로운 소식을 알리는 것 외에 또 하나의 중요한 역할이 있습니다.
          서비스에 문제가 생겼을 때 공개적으로 선제 대응과 후속 조치를 할 수 있다는
          점입니다. 그러면 실제로 문제가 닥쳐도 고객이 느끼는 부정적인 인식이 한결 줄어들며
          신뢰도를 높일 수 있습니다. 영문 모르는 고객들의 문의가 반복적으로 쇄도하는 것도
          막을 수 있고요. 그리고 정기적인 공지나 업데이트는 서비스가 활성화되어 있다는 것을
          알려줄 수도 있습니다. 공지사항을 최상단에 둔 모바일 게임 ‘앨리스클로젯’,
          ‘가디언테일즈’(카카오게임즈) 고객센터 (카카오게임즈도 오큐파이로 고객센터를 만들고
          운영 중입니다)
        </p>
        <hr />
        <h3>답변</h3>
        <h5>제목</h5>
        <p>문의글 제목</p>
        <h5>내용</h5>
        <p class="post-content">
          알림 기능은 공지사항, 이벤트, 업데이트와 같은 제품 및 서비스 소식을 알리는 기능을
          말합니다. 일반적으로 리스트형 게시판을 이용하며 긴급한 내용은 팝업으로 띄우기도
          합니다. 알림 기능은 새로운 소식을 알리는 것 외에 또 하나의 중요한 역할이 있습니다.
          서비스에 문제가 생겼을 때 공개적으로 선제 대응과 후속 조치를 할 수 있다는
          점입니다. 그러면 실제로 문제가 닥쳐도 고객이 느끼는 부정적인 인식이 한결 줄어들며
          신뢰도를 높일 수 있습니다. 영문 모르는 고객들의 문의가 반복적으로 쇄도하는 것도
          막을 수 있고요. 그리고 정기적인 공지나 업데이트는 서비스가 활성화되어 있다는 것을
          알려줄 수도 있습니다. 공지사항을 최상단에 둔 모바일 게임 ‘앨리스클로젯’,
          ‘가디언테일즈’(카카오게임즈) 고객센터 (카카오게임즈도 오큐파이로 고객센터를 만들고
          운영 중입니다)
        </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">작성</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div> -->

      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">답변 작성</h3>
        <button
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <h3>문의 내용</h3>
        <h5>제목</h5>
        <p>문의글 제목</p>
        <h5>내용</h5>
        <p class="post-content">
          알림 기능은 공지사항, 이벤트, 업데이트와 같은 제품 및 서비스 소식을 알리는 기능을
          말합니다. 일반적으로 리스트형 게시판을 이용하며 긴급한 내용은 팝업으로 띄우기도
          합니다. 알림 기능은 새로운 소식을 알리는 것 외에 또 하나의 중요한 역할이 있습니다.
          서비스에 문제가 생겼을 때 공개적으로 선제 대응과 후속 조치를 할 수 있다는
          점입니다. 그러면 실제로 문제가 닥쳐도 고객이 느끼는 부정적인 인식이 한결 줄어들며
          신뢰도를 높일 수 있습니다. 영문 모르는 고객들의 문의가 반복적으로 쇄도하는 것도
          막을 수 있고요. 그리고 정기적인 공지나 업데이트는 서비스가 활성화되어 있다는 것을
          알려줄 수도 있습니다. 공지사항을 최상단에 둔 모바일 게임 ‘앨리스클로젯’,
          ‘가디언테일즈’(카카오게임즈) 고객센터 (카카오게임즈도 오큐파이로 고객센터를 만들고
          운영 중입니다)
        </p>
        <hr />
        <h3>답변</h3>
        <h5>제목</h5>
        <input type="text" class="form-control" />
        <h5>내용</h5>
        <textarea class="form-control"></textarea>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">작성</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script>
  postViewModal.init("div#post-modal");
</script>
