// 수정버튼 눌렀을때
function stockUpdateBtnHandler(event) {
	const tr = event.target.parentElement.parentElement;
	const quantityTd = tr.querySelector("td:nth-child(2)");
	const buttonTd = tr.querySelector("td:nth-child(3)");
	const isUpdating = tr.classList.contains("updating");

	// 이미 수정중일때
	if (isUpdating) {
		const cnt = quantityTd.querySelector("input").value;
		// ajax로 요청보내기
		// 성공시 갱신
		tr.removeAttribute("prev");
		quantityTd.innerHTML = cnt;
		buttonTd.lastChild.remove();
		// 실패시 취소버튼 누른거
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

// 발주주문 주문하기 버튼 눌렀을 때
function stockOrderBtnHandler() {
	// ajax요청보내기
	alert("발주주문 완료");
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
