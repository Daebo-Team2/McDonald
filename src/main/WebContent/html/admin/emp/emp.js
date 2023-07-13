function empModalControl() {
	const modal = new bootstrap.Modal(document.getElementById('emp-update-modal'));
	const modalContent = document.querySelector("div#update-modal-content");
	return {
		empModalOpen: (content) => {
			modalContent.innerHTML = content;
			modal.show();
		}, empModalClose: () => {
			modal.hide();
		}
	}
}

let { empModalOpen, empModalClose } = empModalControl();


function updateBtnHandler(event) {
	const updateModalHTML = `
					<div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">직원수정</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" id="modal-body">
						<label class="form-label">이름</label>
    				<input type="text" class="form-control" value="직원2">
						<label class="form-label">시급</label>
    				<input type="text" class="form-control" value="9400">
						<label class="form-label">전화번호</label>
    				<input type="text" class="form-control" value="010-2222-2222">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary">수정하기</button>
            <button type="button" class="btn btn-danger" onclick="empModalClose()">취소</button>
          </div>
	`;
	empModalOpen(updateModalHTML);
}