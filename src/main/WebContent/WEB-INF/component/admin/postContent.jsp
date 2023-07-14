<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/admin/post.css" rel="stylesheet" />
<div class="title">
  <h1>문의게시판</h1>
</div>
<div class="wrapper">
  <table class="table" page="post">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성시간</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr onclick="openViewModal(1)">
        <td>1</td>
        <td>문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목 1문의글 제목 1</td>
        <td>2023/07/07 11:44:43</td>
        <td><span class="yellow">답변대기</span></td>
      </tr>
      <tr onclick="openViewModal(2)">
        <td>2</td>
        <td>문의글 제목 2</td>
        <td>2023/07/06 11:44:43</td>
        <td><span class="green">답변완료</span></td>
      </tr>
      <tr onclick="openViewModal(3)">
        <td>5</td>
        <td>문의글 제목 5</td>
        <td>2023/07/05 11:44:43</td>
        <td><span class="yellow">답변대기</span></td>
      </tr>
    </tbody>
  </table>
</div>
<div class="footer">
  <div class="empty"></div>
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
  <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#post-add-modal">글작성</button>
</div>
<div class="modal fade" id="post-view-modal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="post-view-modal-content">
    </div>
  </div>
</div>
<div class="modal fade" id="post-add-modal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="add-modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="staticBackdropLabel">문의글 작성</h3>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h5>제목</h5>
        <input type="text" class="form-control">
        <h5>내용</h5>
        <textarea class="form-control"></textarea>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="writePostBtnHandler(event)">작성</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script>
  postViewModal.init("div#post-view-modal")
</script>
