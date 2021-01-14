window.addEventListener("load", (e) => {
	const pager = document.querySelector(".pager");
	
	pager.addEventListener("click", (e) => {
		e.preventDefault();
		
		console.log(e.target.innerText);
		
		// 기존방법 : callback 활용
		/*let request = new XMLHttpRequest();
		
		request.addEventListener("load", (e) => {
		
			console.log(e.target.responseText);

		});
		
		request.open("GET", "/api/notice/list");
		request.send();*/
		
		// promise로 변경
		fetch("/api/notice/list")
		.then(response => response.);
		
	});
	
});