/* login.js */

const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-submit");

loginButton.addEventListener("click", (event) => {
	
	event.preventDefault();
	/* a 태그나 submit 태그는 누르게 되면 href 를 통해 이동하거나, 창이 새로고침하여 실행됩니다.
		preventDefault 를 통해 이러한 동작을 막아줄 수 있습니다. */
	
	const id = loginForm.id.value.trim();
	const pwd = loginForm.password.value.trim();

	if ( !id || id.trim().length == 0 ){
		alert("아이디가 입력되지 않았습니다.");
		document.getElementById("floatingInput").focus();
	} else 	if ( !pwd || pwd.trim().length ==0 ) {
		alert("비밀번호를 입력해주세요.");
		document.getElementById("floatingPassword").focus();
	} else {
		loginForm.submit();
	}
})

