window.addEventListener("load", (e) => {
	const pager = document.querySelector(".pager");
	const notice = document.querySelector(".notice");
	const tbody = notice.querySelector("tbody");
	const searchForm = document.querySelector(".search-form");
	const searchButton = searchForm.querySelector(".btn-search");
	const queryInput = searchForm.querySelector("input[name=q]");
	
	searchButton.addEventListener = ("click", (e) => {
		
		let page = e.target.innerText;
		let query = queryInput.value;
		let field = "title";
		
		fetch(`/api/notice/list?p=${page}&f=${field}&q=${query}`)
			.then(response => response.json())
			.then(json => {
				tbody.innerHTML = "";
				for (let n of json) {
					let tr = `
					<tr>
						<td>${n.id}</td>
						<td class="title indent text-align-left"><a href="${n.id}">${n.title}</a>[${n.cmtCount}]</td>
						<td>${n.writerId}</td>
						<td>${n.regdate}</td>
						<td>${n.hit}</td>
					</tr>`;
					tbody.insertAdjacentHTML("beforeend", tr);
				}
			});
			
	});

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


		let page = e.target.innerText;
		let query = queryInput.value;
		let field = "title";
		
		// promise로 변경(JSON을 대신할 수 있다.)
		fetch(`/api/notice/list?p=${page}&f=${field}&q=${query}`)
			.then(response => response.json())
			.then(json => {
				// console.log(json[0].title);
				tbody.innerHTML = "";
				for (let n of json) {
					let tr = `
					<tr>
						<td>${n.id}</td>
						<td class="title indent text-align-left"><a href="${n.id}">${n.title}</a>[${n.cmtCount}]</td>
						<td>${n.writerId}</td>
						<td>${n.regdate}</td>
						<td>${n.hit}</td>
					</tr>`;
					// console.log(tr);
					tbody.insertAdjacentHTML("beforeend", tr);
				}
			});

	});

});