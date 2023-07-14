// 모달클래스
class Modal {
	constructor() {
		this.modal = null;
	}

	init(selector) {
		this.modal = new bootstrap.Modal(document.querySelector(selector));
	}

	open() {
		if (this.modal !== null) {
			this.modal.show();
		}
	}

	close() {
		if (this.modal !== null) {
			this.modal.hide();
		}
	}
}

const menuViewModal = new Modal();
const postViewModal = new Modal();

// 상단 메뉴버튼 눌럿을 때
function pageMove(url) {
	$.ajax({
		url,
		dataType: "text"
	}).done((text) => {
		$("div#content").html(text);
	})
};

// store페이지
// 가맹점 삭제버튼
function storeDelBtnHandler(num) {
	// 가맹점삭제하는 요청보내야함.
	// ajax url: /super/storedelete.do
	// data {no : num}
	const tr = document.querySelector(`tr[store-num="${num}"]`);
	const name = tr.firstElementChild.textContent;
	$.ajax({
		url: "/super/storedelete.do",
		data: {'no': num},
		dataType: "text"
	}).done((text) => {
		alert(`가맹점 ${name} 삭제`);
		document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
		$("#content").html(text);
	})
}

// 가맹점 추가모달의 등록버튼
function storeAddBtnHandler() {
	// 유효성검사
	const name = document.querySelector("input#storeName").value;
	$.ajax({
		url:"/super/storeadd.do",
		data: {
			name: name,
			id: document.querySelector("input#storeId").value,
			pwd: document.querySelector("input#storePwd").value,
			tel: document.querySelector("input#storeTel").value,
			owner: document.querySelector("input#storeOwner").value,
			address: document.querySelector("input#storeAddress").value
		},
		dataType: "text",
		method: "post"
	}).done((text) => {
		alert(`가맹점 ${name} 추가`)
		document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
		$("#content").html(text);
	})
}

//sale페이지
// 검색버튼
function searchBtnHandler() {
	alert("검색요청");
}

// menu페이지
// 이미지 미리보기 위한 핸들러
function fileChangeHandler() {
	const input = document.querySelector("input#file-form");
	console.log(input.files);
	if (input.files && input.files[0]) {
		const reader = new FileReader();
		reader.onload = (e) => {
			document.querySelector("img#preview").src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	}
}

// 상세보기 누르면 요청을 보내고 모달을 응답받아 메뉴상세보기 모달을 채워줘야한다.
function menuViewBtnHandler(event) {
	// ajax를 통해 모달의 내용을 받아와 #modal-content에 채워준다
	menuViewModal.open();
}

// 삭제버튼을 누르면 삭제요청 보내야함
function menuDelBtnHandler(event) {
	// ajax를 통해 삭제요청을 보내고 #content를 받아와 화면 변경
	alert("삭제요청");
}

// stock페이지
function stockConfirmBtnHandler(num) {
	$.ajax({
		url: "/super/stockupdate.do",
		data: {no: num},
		dataType: "text"
	}).done((text) => {
		alert(`${num}번 주문이 발주되었습니다.`);
		$("#content").html(text);
	});
}

// post페이지

// 문의글 상세보기
function openViewModal() {
	// ajax를 통해 모달의 내용을 응답받고 #modal-content에 내용을 변경한다.
	postViewModal.open();
}

// 탭버튼 눌렀을 때
function tabBtnClick(num) {
	// num -> post테이블에서 조회해야할 status
	// ajax를 통해 알맞은 post들만 조회하여 #content 화면 변경
	alert("탭 변경");

}

