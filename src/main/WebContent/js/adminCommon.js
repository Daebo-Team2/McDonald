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
const checkPwdModal = new Modal();
const empAddModal = new Modal();
const updatePwdModal = new Modal();
renderOrderList();

// 상단 메뉴버튼 눌럿을 때
function pageMove(url) {
	isOrderPage = false;
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
	if (start !== '' && end !== '' && start > end) {
		alert("시작일자가 종료일자 이전이야합니다.");
		return;
	}

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

// emp페이지 직원정보 수정모달
function empUpdateModalOpener(num) {
	document.querySelector("input#subpwdInput").value = "";
	checkPwdModal.open();
	document.querySelector("button#pwdSubmitBtn").onclick = () => {
		if (document.querySelector("input#subpwdInput").value === "") {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		$.ajax({
			url: "/admin/checksubpwd.do",
			data: {subpwd: document.querySelector("input#subpwdInput").value},
			method: "post",
			statusCode: {
				200: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					$.ajax({
						url: "/admin/empupdatemodal.do",
						data: {no: num},
						dataType: "text"
					}).done((text) => {
						$("#update-modal-content").html(text);
						empUpdateModal.open();
					})
				},
				403: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					alert("2차 비밀번호가 올바르지 않습니다.");
				}
			}
		})
	}
}

function empUpdateBtnHandler(num) {
	// ajax
	// url: /admin/empupdate.do
	// data: {no, tel, pay}

	const name = document.querySelector("#empUpdateNameInput").value.trim();
	const tel = document.querySelector("#empUpdateTelInput").value.trim();
	const pay = document.querySelector("#empUpdatePayInput").value.trim();
	
	if ( tel.length===0 || pay.length===0 ){
		alert("정보를 모두 적어 주세요.")
		return
	}
	
		const regexTel = /\d{2,3}-\d{3,4}-\d{4}$/g;
	if ( !document.querySelector("#empUpdateTelInput").value.match(regexTel) ){
		alert('연락처 형식을 다시 확인해 주세요.');
		document.querySelector("#empUpdateTelInput").value = '';
		return
	}

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

function empAddModalHandler() {
	document.querySelector("input#subpwdInput").value = "";
	checkPwdModal.open();
	document.querySelector("button#pwdSubmitBtn").onclick = () => {
		if (document.querySelector("input#subpwdInput").value === "") {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		$.ajax({
			url: "/admin/checksubpwd.do",
			data: {subpwd: document.querySelector("input#subpwdInput").value},
			method: "post",
			statusCode: {
				200: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					empAddModal.open();
				},
				403: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					alert("2차 비밀번호가 올바르지 않습니다.");
				}
			}
		})
	}
}

function empAddBtnHandler() {
	const name = document.querySelector("#empAddNameInput").value.trim();
	const tel = document.querySelector("#empAddTelInput").value.trim();
	const pay = document.querySelector("#empAddPayInput").value.trim();
	
	if (name.length===0 || tel.length===0 || pay.length===0) {
		alert("정보를 모두 입력해 주세요.")
		return
	}
	
	const regexTel = /\d{2,3}-\d{3,4}-\d{4}$/g;
	if ( !document.querySelector("#empAddTelInput").value.match(regexTel) ){
		alert('연락처 형식을 다시 확인해 주세요.');
		document.querySelector("#empAddTelInput").value = '';
		document.querySelector("#empAddTelInput").focus();
		return
	}


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

function pwdModalHandler() {
	document.querySelector("input#subpwdInput").value = "";
	checkPwdModal.open();
	document.querySelector("button#pwdSubmitBtn").onclick = () => {
		if (document.querySelector("input#subpwdInput").value === "") {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		$.ajax({
			url: "/admin/checksubpwd.do",
			data: {subpwd: document.querySelector("input#subpwdInput").value},
			method: "post",
			statusCode: {
				200: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					updatePwdModal.open();
				},
				403: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					alert("2차 비밀번호가 올바르지 않습니다.");
				}
			}
		})
	}
}

function pwdUpdateHandler() {
	$.ajax({
		url: "/admin/updatesubpwd.do",
		data: {
			newPwd: document.querySelector("input#newpwdInput").value
		},
		method: "post",
		statusCode: {
			200: () => {
				updatePwdModal.close();
				document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
				alert("2차 비밀번호가 변경되었습니다.");
			},
			500: () => {
				updatePwdModal.close();
				document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
				alert("2차 비밀번호 변경에 실패했습니다.\n잠시후 다시 시도해주세요.");
			}

		}
	})
}

function checkNum(event) { //시급입력시 숫자만!
	const input = event.target;
	input.value = input.value.replace(/[^0-9]/g,'');
}


function empDeleteBtnHandler(num) {
	document.querySelector("input#subpwdInput").value = "";
	checkPwdModal.open();
	document.querySelector("button#pwdSubmitBtn").onclick = () => {
		if (document.querySelector("input#subpwdInput").value === "") {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		$.ajax({
			url: "/admin/checksubpwd.do",
			data: {subpwd: document.querySelector("input#subpwdInput").value},
			method: "post",
			statusCode: {
				200: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
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
				},
				403: () => {
					checkPwdModal.close();
					document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
					alert("2차 비밀번호가 올바르지 않습니다.");
				}
			}
		})
	}
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
	const order = JSON.parse(e.data);
	localStorage.setItem(order.no, e.data);
	if (isOrderPage) {
		renderOrderList();
	}
}

// 주문관리
function renderOrderList() {
	const contentDiv = document.querySelector("div#content");
	contentDiv.innerHTML = '';
	const orders = [];
	const keys = [];
	for (let i = 0; i < localStorage.length; i++) {
		keys.push(localStorage.key(i));
	}
	keys.sort((a, b) => Number(a) - Number(b));
	for (const key of keys) {
		orders.push(JSON.parse(localStorage.getItem(key)));
	}
	contentDiv.innerHTML = orderContent(orders);
}

function orderContent(orders) {
	return `
	<div class="title">
		<h1>주문관리</h1>
	</div>
	
	<div class="wrapper">
	${orders.length === 0 ? "<h2 id=\"no-oreder\">등록된 주문이 없습니다.</h2>" : orders.map(order => orderAccordion(order)).join("")}
	</div>
	`
}

function orderAccordion(order) {
	return `
	<div class="accordion-item" order-no="${order.no}">
		<h2 class="accordion-header">
			<div class="accordion-button collapsed" type="button" page="order">
				<span>${order.no}</span>
				<span>${order.time}</span>
				<span>${order.price.toLocaleString()}</span>
				<span>${order.place === "store" ? "매장" : "포장"}</span>
				<button type="button" class="btn btn-primary delBtn red-btn" onclick="orderDelBtnHandler(event)">확인</button>
			</div>
		</h2>
			<div class="accordion-collapse collapse show">
				<div class="accordion-body" page="order">
					<table class="table" page="order">
						<thead>
							<tr>
								<th>제품명</th>
								<th>수량</th>
							</tr>
						</thead>
						<tbody>
							${order.list.map((menu) => {
								return `
									<tr>
										<td>${menu.name}</td>
										<td>${menu.quantity}</td>
									</tr>
								`
							}).join("")}
						</tbody>
					</table>
				</div>
			</div>
		</div>
	`
}

// order페이지 확인버튼 핸들러
function orderDelBtnHandler(event) {
	// 확인이 눌리면 DOM요소를 삭제하고 localstorage에서도 삭제한다.
	const key = event.target.parentElement.parentElement.parentElement.getAttribute("order-no");
	localStorage.removeItem(key);
	renderOrderList();
}

