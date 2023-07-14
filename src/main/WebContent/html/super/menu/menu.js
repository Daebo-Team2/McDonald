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