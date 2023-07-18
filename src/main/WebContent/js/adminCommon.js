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

const empUpdateModal = new Modal();
const postViewModal = new Modal();

// 상단 메뉴버튼 눌럿을 때
function pageMove(url) {
	$.ajax({
		url,
		dataType: "text"
	}).done((text) => {
		$("div#content").html(text);
	})
}

function saleSearchBtnHandler() {
	const start = document.querySelector("#saleStartInput").value;
	const end = document.querySelector("#saleEndInput").value;
	const menuName = document.querySelector("#saleMenuNameInput").value;


	$.ajax({
		url: "/admin/saleContent.do",
		method: "post",
		data: { start, end, menuName },
		dataType: "text"
	}).done((text) => {
		$("#content").html(text);
	})
}
// 좌측 출퇴근 관리 버튼눌렀을 때
function empInoutBtnHandler(num) {
	// ajax요청보내기
	// url: /admin/empinout.do
	// data: {no: num}
	$.ajax({
		url: "/admin/empinout.do",
		data: { no: num },
		dataType: "text"
	}).done((text) => {
		$("#sideBar").html(text);
		//   <table className="table" page="emp">
		if (document.querySelector("table[page='emp']") !== null) {
			pageMove("/admin/empContent.do");
		}
	})
}

// order페이지 렌더링, sse
// 서버로부터 실시간으로 주문 알림을 받아 localstorage에 저장한다.(다른 페이지에 있더라도 주문이 들어오면 저장하기 위해)
// 메뉴탭의 주문관리버튼이 눌리면 ajax가 아닌 js로 화면을 렌더링한다.
function renderOrderContent() {
	// #content.innerHTML 다 삭제
	// localstorage에서 주문 정보를 얻어서 컴포넌트로 렌더링한다.
}

// order페이지 확인버튼 핸들러
function orderDelBtnHandler(event) {
	// 확인이 눌리면 DOM요소를 삭제하고 localstorage에서도 삭제한다.
	event.target.parentElement.parentElement.parentElement.remove();
}

// emp페이지 직원정보 수정모달
function empUpdateModalOpener(num) {
	$.ajax({
		url: "/admin/empupdatemodal.do",
		data: { no: num },
		dataType: "text"
	}).done((text) => {
		$("#update-modal-content").html(text);
		empUpdateModal.open();
	})
}

function empUpdateBtnHandler(num) {
	// ajax
	// url: /admin/empupdate.do
	// data: {no, tel, pay}

	const name = document.querySelector("#empUpdateNameInput").value.trim();
	const tel = document.querySelector("#empUpdateTelInput").value.trim();
	const pay = document.querySelector("#empUpdatePayInput").value.trim();

	$.ajax({
		url: "/admin/empupdate.do",
		method: "post",
		data: { no: num, tel, pay },
		dataType: "text"
	}).done((text) => {
		alert(`${name}직원이 수정되었습니다.`);
		document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
		$("#content").html(text);
	})
}

function empAddBtnHandler() {
	const name = document.querySelector("#empAddNameInput").value.trim();
	const tel = document.querySelector("#empAddTelInput").value.trim();
	const pay = document.querySelector("#empAddPayInput").value.trim();

	$.ajax({
		url: "/admin/empadd.do",
		data: { name, tel, pay },
		dataType: "text",
		method: "post"
	}).done((text) => {
		alert(`${name}직원이 추가되었습니다.`);
		document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
		$("#content").html(text);
		$.ajax({
			url: "/admin/sidebar.do",
			dataType: "text"
		}).done((text) => {
			$("#sideBar").html(text);
		})
	})
}

function empDeleteBtnHandler(num) {
	if (window.confirm("직원 삭제를 진행하시겠습니까?")) {
		$.ajax({
			url: "/admin/empdelete.do",
			data: { no: num },
			dataType: "text",
			method: "post"
		}).done((text) => {
			alert('삭제가 완료되었습니다.');
			$("#content").html(text);
			$.ajax({
				url: "/admin/sidebar.do",
				dataType: "text"
			}).done((text) => {
				$("#sideBar").html(text);
			})
		})
	}
	else { }
}

// stock 페이지
// 수정버튼 눌렀을때
function stockUpdateBtnHandler(event) {
	const tr = event.target.parentElement.parentElement;
	const quantityTd = tr.querySelector("td:nth-child(2)");
	const buttonTd = tr.querySelector("td:nth-child(3)");
	const isUpdating = tr.classList.contains("updating");

	// 이미 수정중일때
	if (isUpdating) {
		const cnt = quantityTd.querySelector("input").value
		const foodNum = tr.getAttribute("food-num");
		$.ajax({
			url: "/admin/stockupdate.do",
			data: {
				foodno: foodNum,
				quantity: cnt,
			},
			dataType: "text"
		}).done((text) => {
			$("#content").html(text);
		})

	}
	else {
		const cnt = quantityTd.textContent;
		tr.setAttribute("prev", cnt);
		quantityTd.innerHTML = `
			<button class="btn btn-dark minusBtn" onclick="stockMinusBtnHandler(event)">
			</button><input type="text" value="${cnt}" class="form-control quantity">
			<button class="btn btn-dark plusBtn" onclick="stockPlusBtnHandler(event)"></button>
		`
		buttonTd.innerHTML += `<button class="btn btn-danger cancelBtn" onclick="stockUpdateCancelBtnHandler(event)">취소</button>`
	}
	tr.classList.toggle("updating");
}

// 수정중에 취소버튼 눌렀을 때
function stockUpdateCancelBtnHandler(event) {
	const cancelBtn = event.target;
	const tr = cancelBtn.parentElement.parentElement;
	const quantityTd = tr.querySelector("td:nth-child(2)");
	const buttonTd = tr.querySelector("td:nth-child(3)");

	quantityTd.innerHTML = tr.getAttribute("prev");
	tr.removeAttribute("prev");
	buttonTd.lastChild.remove();
	tr.classList.toggle("updating")
}

// 수량변경 버튼 눌럿을 때
function stockMinusBtnHandler(event) {
	const inputEl = event.target.parentElement.querySelector("input");
	const val = Number(inputEl.value) - 1
	inputEl.value = Math.max(val, 0);
}

function stockPlusBtnHandler(event) {
	const inputEl = event.target.parentElement.querySelector("input");
	inputEl.value = Number(inputEl.value) + 1;
}

// 주문하기 버튼 눌렀을 때
function stockOrderBtnHandler() {
	// ajax요청보내기

	const stockorderdata = getStockOrderData();

	if (stockorderdata === null) {
		alert("주문을 다시 확인해 주세요.");
		return;
	}

	$.ajax({
		url: "/admin/stockorder.do",
		data: stockorderdata,
		dataType: "text",
		contentType: "application/x-www-form-urlencoded",
		method: "post"
	}).done((text) => {
		alert("발주주문 완료");
		document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
		$("#content").html(text);
	})
}

function getStockOrderData() {
	let words = [];
	const trs = document.querySelectorAll("tr.stockOrder");

	for (const tr of trs) {
		const foodNo = tr.querySelector("select.form-select").value;
		const quantity = tr.querySelector("input.stock-cnt").value;
		if (foodNo === '0' || quantity === "") {
			return null;
		}
		words.push(`foodno=${foodNo}`);
		words.push(`quantity=${quantity}`);
	}
	return words.join("&");
}

// 발주주문 항목 삭제버튼 눌럿을 때
function delStockOrderBtnHandler(event) {
	event.target.parentElement.parentElement.remove();
}

// 발주주문 항목추가 눌럿을 때
function addStockOrderBtnHandler() {
	document.querySelector("table[page='stock-modal'] > tbody").appendChild(stockOrderElement());
}

function stockOrderElement() {
	const tr = document.createElement("tr");
	tr.classList.add("stockOrder");
	tr.innerHTML = `
	<td>
		<select class="form-select" aria-label="재료선택">
			<option value = "0" selected>재료선택</option>
			<option value="1">빵</option>
			<option value="2">소고기패티</option>
			<option value="3">닭고기패티</option>
			<option value="4">새우패티</option>
			<option value="5">닭고기</option>
			<option value="6">소스</option>
			<option value="7">치즈</option>
			<option value="8">양상추</option>
			<option value="9">양파</option>
			<option value="10">해쉬브라운</option>
			<option value="11">감자튀김</option>
			<option value="12">초코시럽</option>
			<option value="13">딸기시럽</option>
			<option value="14">바닐라시럽</option>
			<option value="15">오레오</option>
			<option value="16">아이스크림</option>
			<option value="17">원두</option>
			<option value="18">라떼시럽</option>
			<option value="19">코카콜라</option>
			<option value="20">코카콜라 제로</option>
			<option value="21">스프라이트</option>
			<option value="22">환타</option>
			<option value="23">쉐이크</option>
			<option value="24">오렌지 주스</option>
			<option value="25">생수</option>
			<option value="26">코울슬로</option>
			<option value="27">과자콘</option>
		</select>
	</td>
	<td>
		<input class="form-control stock-cnt" type="number" placeholder="수량입력"/>
		<button class="btn btn-sm btn-danger stockDelBtn" onClick="delStockOrderBtnHandler(event)">X</button>
	</td>
`
	return tr;
}

function openViewModal(num) {
	$.ajax({
		url: "/admin/postmodal.do",
		data: { no: num },
		dataType: "text"
	}).done((text) => {
		$("div#post-view-modal-content").html(text);
		postViewModal.open();
	})
}

// 문의글 작성 모달에서 작성버튼 눌렀을 때
function writePostBtnHandler() {
	// 작성 내용긁어서 등록 요청보내기
	// ajax
	// url: /admin/postadd.do
	// data: {title, content}
	const title = document.querySelector("input#post-add-title").value;
	const content = document.querySelector("textarea#post-add-content").value;

	if (title.trim().length == 0 || content.trim().length == 0) {
		alert("작성된 문의글이 없습니다!")
	} else {
		$.ajax({
			url: "/admin/postadd.do",
			data: { title, content },
			dataType: "text",
			method: "post"
		}).done((text) => {
			document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
			alert("문의등록완료!");
			$("#content").html(text);
		});
	}

}

function stockOrderListBtnHandler() {
	$.ajax({
		url: "/admin/stockorderlist.do",
		dataType: "text",
		method: "post"
	}).done((text) => {
		$("#content").html(text);
	})
}

function stockOrderListDeleteBtnHandler(num) {
	if (window.confirm("정말 재고발주를 취소하시겠습니까?")) {
		$.ajax({
			url: "/admin/stockorderdelete.do",
			data: { num },
			method: "post",
			dataType: "text"
		}).done((text) => {
			$("#content").html(text);
		})
	} else { }
}
// sse
const sseSource = new EventSource("/sse");
let orderNum = 1;
let isOrderPage = true;
sseSource.onmessage = function (e) {
	console.log(e);
	console.log(e.data);
}

// 주문관리
function renderOrderList() {
	contentDiv.innerHTML = '';
	const elements = [];
	const keys = [];
	for (let i = 0; i < localStorage.length; i++) {
		keys.push(localStorage.key(i));
	}
	keys.sort((a, b) => Number(a) - Number(b));
	for (const key of keys) {
		const msg = localStorage.getItem(key);
		elements.push(`<div id=${key}>
      <span>${msg}</span>
      <button>확인</button>
    </div>`)
	}

	if (elements.length === 0) {
		contentDiv.innerHTML = "<h1>대기중인 주문이 없습니다.</h1>"
	}
	else {
		contentDiv.innerHTML = elements.join("<br>");
		const btns = contentDiv.querySelectorAll("button");
		for (const btn of btns) {
			btn.addEventListener("click", () => {
				const parent = btn.parentElement;
				const key = parent.id;
				localStorage.removeItem(key);
				renderOrderList();
			})
		}
	}
}

