// 장바구니 내역 저장위한 배열
const cart = [];
// 장바구니가 렌더링되는 div
const tabelContainer = document.querySelector("div.side-content");
const priceSumContainer = document.querySelector("div.price-sum");

// 상품정보를 저장하는 인스턴스 클래스
class Menu {
	constructor(menuNum, menuName, price, cnt) {
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.price = price;
		this.cnt = cnt;
	}
}

// 장바구니를 렌더링하는 함수
function renderCart(sum) {
	if (cart.length === 0) {
		tabelContainer.innerHTML = "<h2 class='empty'>담긴 상품이 없습니다.</h2>";
		priceSumContainer.textContent = "합: " + sum.toLocaleString() + '₩';
		return;
	}
	tabelContainer.innerHTML = "<table class='table cart'>" + cart.map(menu => menuTr(menu)).join('') + "</table>";
	const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
	const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
	priceSumContainer.textContent = "합: " + sum.toLocaleString() + '₩';
}

// cart업데이트 하는 합수
function updateCart(menuNum, menuName, price, cnt) {
	let index = -1;
	for (let i = 0; i < cart.length; i++) {
		const menu = cart[i];
		if (menu.menuNum === menuNum) {
			index = i;
		}
	}

	if (index === -1) {
		cart.push(new Menu(menuNum, menuName, price, cnt));
	}
	else {
		cart[index].cnt += cnt;
		if (cart[index].cnt <= 0) {
			cart.splice(index, 1);
		}
	}
	renderCart(cart.reduce((acc, curr) => acc + curr.price * curr.cnt, 0));
}

// 장바구니 수량 줄이는 버튼 눌렸을때
function cartMinusBtnHandler(event) {
	const td = event.target.parentElement;
	const menuNum = td.parentElement.getAttribute("menu-num");
	updateCart(menuNum, null, null, -1);
}

// 장바구니 수량 늘리는 버튼 눌렀을때
function cartPlusBtnHandler(event) {
	const td = event.target.parentElement;
	const menuNum = td.parentElement.getAttribute("menu-num");
	updateCart(menuNum, null, null, 1);
}

// 장바구니 삭제버튼 눌렀을 때
function stockDelBtnHandler(event) {
	const menuNum = event.target.parentElement.parentElement.getAttribute("menu-num");
	updateCart(menuNum, null, null, Number.MIN_SAFE_INTEGER);
}

// 장바구니 담기 버튼 눌렀을 때
function addBtnHandler(event) {
	const card = event.target.parentElement.parentElement;
	const menuNum = card.getAttribute("menu-num");
	const menuName = card.querySelector(".card-title").textContent.trim();
	const price = Number(card.querySelector(".card-text").textContent.replace(/[\,|₩]/g, ''));
	updateCart(menuNum, menuName, price, 1);
}

function menuTr(menu) {
	const menuNum = menu.menuNum;
	const menuName = menu.menuName;
	const price = menu.cnt * menu.price;
	const cnt = menu.cnt;
	return `
			<tr menu-num=${menuNum}>
				<td data-bs-toggle="tooltip" data-bs-placement="right" data-bs-title="${menuName}">
					${menuName}
				</td>
				<td class="price">${price.toLocaleString()}</td>
				<td>
					<button class="btn btn-dark minusBtn" onclick="cartMinusBtnHandler(event)"></button>
					<input type="text" value="${cnt}" class="form-control quantity" readonly/>
					<button class="btn btn-dark plusBtn" onclick="cartPlusBtnHandler(event)"></button>
				</td>
				<td>
					<button
						class="btn btn-sm btn-danger stockDelBtn"
						onclick="stockDelBtnHandler(event)"
					>
						X
					</button>
				</td>
			</tr>
	`
}

// 요청보내는 핸들러
function orderRequest() {
	if (cart.length === 0) {
		alert("장바구니에 담긴 상품이 없습니다.");
		return;
	}
	const placeRadio = document.querySelector("input[type='radio']:checked");
	if (placeRadio === null) {
		alert("매장 식사여부를 선택해주세요.");
		return;
	}
	$.ajax({
		url: "/admin/kioskorder.do",
		data: orderQueryString(),
		dataType: "text",
		contentType: "application/x-www-form-urlencoded",
		method: "post"
	}).done(() => {
		alert("주문이 완료되었습니다.");
		location.replace("/page/enterkiosk");
	});
}

function orderQueryString() {
	const arr = [];
	arr.push(`place=${document.querySelector("input[type='radio']:checked").value}`);
	arr.push(`price=${cart.reduce((acc, curr) => acc + curr.price * curr.cnt, 0)}`);
	for (const menu of cart) {
		arr.push(`menuNum=${menu.menuNum}`);
		arr.push(`cnt=${menu.cnt}`);
	}
	return arr.join("&");
}


// 반드시 툴팁활성화해야한다.
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
renderCart(0);