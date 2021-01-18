window.addEventListener("load", (e) => {
	const section = document.querySelector("#client");
	const chatList = section.querySelector(".chat-list");
	const chatInput = section.querySelector(".chat-input");
	const conButton = section.querySelector(".connection-button");
	const sendButton = section.querySelector(".send-button");

	let username = "newlec";
	/*let message = {
		username,
		chatData: ""
	};*/

	// Create WebSocket connection.
	let socket;

	conButton.addEventListener("click", (e) => {

		if (socket == undefined)
			socket = new WebSocket('ws://localhost:8080/chat');

		// Connection opened
		socket.addEventListener('open', function(e) {
			console.log('연결되었습니다.');
		});

		// Listen for messages
		socket.addEventListener('message', function(e) {
			let message = JSON.parse(e.data);
			let {username, chatData} = message;
			let chatItemTemplate = `<div>
										<span>profile</span>
										<span>${username} : </span>
										<span>${chatData}</span>
									</div>`;
			chatList.insertAdjacentHTML("beforeend", chatItemTemplate);
		});

	});

	sendButton.addEventListener("click", (e) => {

		let message = {
			username,
			chatData : chatInput.value
		};

		if (socket != undefined)
			socket.send(JSON.stringify(message));
			//socket.send(chatInput.value);
	});

})