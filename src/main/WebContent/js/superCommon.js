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
			address1: document.querySelector("input#sample4_postcode").value,
			address2: document.querySelector("input#sample4_roadAddress").value,
			address3: document.querySelector("input#sample4_detailAddress").value,
			address4: document.querySelector("input#sample4_extraAddress").value
		},
		dataType: "text",
		method: "post"
	}).done((text) => {
		alert(`가맹점 ${name} 추가`)
		document.querySelectorAll(".modal-backdrop").forEach(el => el.remove());
		$("#content").html(text);
	})
}
/*
<input type="text" class="form-control" id="sample4_postcode" placeholder="우편번호">
<input type="button" class="form-control" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" class="form-control" id="sample4_roadAddress" placeholder="도로명주소">
<input type="text" class="form-control" id="sample4_jibunAddress" placeholder="지번주소">
<span id="guide" class="form-control" style="color:#999;display:none"></span>
<input type="text" class="form-control" id="sample4_detailAddress" placeholder="상세주소">
<input type="text" class="form-control" id="sample4_extraAddress" placeholder="참고항목">
*/
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
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
function menuViewBtnHandler(num) {
	// ajax를 통해 모달의 내용을 받아와 #modal-content에 채워준다
	$.ajax({
		url: "/super/menudetail.do",
		data: {no: num},
		dataType: "text",
		method: "post"
	}).done((text) => {
		$("div#menu-detail-modal-content").html(text);
		menuViewModal.open();
	})
}

// 삭제버튼을 누르면 삭제요청 보내야함
function menuDelBtnHandler(num) {
	// ajax를 통해 삭제요청을 보내고 #content를 받아와 화면 변경
	$.ajax({
		url: "/super/menudelete.do",
		data: {no: num},
		dataType: "text"
	}).done((text) => {
		alert(`${ num }번 메뉴가 삭제되었습니다.`);
		$("#content").html(text);
	});
}

// 메뉴 추가모달의 등록버튼
function menuAddBtnHandler() {
	// 유효성검사
	const name = document.querySelector("input#menuname").value;
	$.ajax({
		url: "/super/menuadd.do",
		data: {
			name: name,
			category: document.querySelector("input#category").value,
			price: document.querySelector("input#price").value
		},
		dataType: "text",
	}).done((text) => {
		alert(`${ name } 메뉴가 추가되었습니다.`);
		$("#content").html(text);
	});
}

/*
function getRecipeData() {
	let words = [];
	const 
	
}
*/


// stock페이지
function stockConfirmBtnHandler(num, snum) {
	$.ajax({
		url: "/super/stockupdate.do",
		data: {no: num,
		storeno : snum
		},
		dataType: "text"
	}).done((text) => {
		alert(`${num}번 주문이 발주되었습니다.`);
		$("#content").html(text);
	});
}
/*
function getStockOrderlistDate() {
	let words = [];
	const trs = document.querySelectorAll("tr.order-info");
	for ( const tr of trs ){
		const foodNo = tr.querySelector("td#foodno");
		const quantity = tr.querySelector("td.quantity").value;
		words.push(`foodno=${foodNo}`);
		words.push(`quantity=${quantity}`);
	}
}
*/

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

function saleSearchBtnHandler() {
	const start = document.querySelector("#saleStartInput").value;
	const end = document.querySelector("#saleEndInput").value;
	const menuName = document.querySelector("#saleMenuNameInput").value;
	const storeName = document.querySelector("#saleStoreNameInput").value;

	$.ajax({
		url: "/super/saleContent.do",
		method: "post",
		data: {start, end, menuName, storeName},
		dataType: "text"
	}).done((text) => {
		$("#content").html(text);
	})
}
