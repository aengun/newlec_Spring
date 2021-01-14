// import DragBox from "./DragBox.js";
// import ModalBox from "./ModalBox.js";

// // s2 ===========================================================

// window.addEventListener("load", (e) => {

//     let section = document.querySelector("#s2");
//     let alertButton = section.querySelector(".alert-button");

//     alertButton.onclick = (e) => {
//         let modal = new ModalBox();
//         let time = 0;
//         setTimeout(() => {
//             modal.alert("hello"); // ModalBox.alert("hello");

//         }, 500);

//     }
// });

// // s1 ===========================================================

// window.addEventListener("load", (e) => {

//     // 1. 객체를 넘겨주거나
//     // let container = document.querySelector("#s1 .container");
//     // let dragbox = new DragBox(container);
//     // 2. 객체를 선택하라고 하거나
//     let dragBox = new DragBox("#s1 .container");

// });

// ====================================================
// 성현이코드

import DragBox from "./modules/DragBox.js";
import ModalBox from "./modules/ModalBox.js";
window.addEventListener("load", (e) => {

	// 1. 객체를 넘겨주거나
	// let container = document.querySelector("#s1 .container");
	//  let dragBox = new DragBox(container);

	// 2. 객체를 선택하라고 하거나
	let dragBox = new DragBox("#s1 .container");

});

window.addEventListener("load", (e) => {

	let section = document.querySelector("#s2");
	let alertButton = section.querySelector(".alert-button");

	//2-1
	// let modalBox = new ModalBox();

	alertButton.onclick = (e) => {

		//  let modalBox = new ModalBox();
		//  let time = 0;
		//  setTimeout(() => {
		//      modalBox.alert("he");
		//      console.log(time);
		//  }, 100);
		// modalBox.alert("he");
		//    2
		//    let modalBox = new ModalBox();
		//    alert("hello");
		//    modalBox.alert("he");

		// =========== 3 ===========

		// ModalBox.alert("hello");

		// let p = ModalBox.alert("hello");
		// p
		//     .then((result) => {
		//         console.log(result + "가(이) 눌렸구나!");
		//     });

		//같은 방법
		(async () => {
			let result = await ModalBox.alert("hello");
			console.log(result + "가(이) 눌렸구나!!");
		})();

	}

});


// ==================================================

import CSS from './modules/CSS.js';

window.addEventListener("load", (e) => {
	let section = document.querySelector("#s3");
	let dropZone = section.querySelector(".drop-zone");

	dropZone.addEventListener("drop", (e) => {
		e.preventDefault();
		e.stopPropagation();

		let valid = e.dataTransfer
			&& e.dataTransfer.types
			&& e.dataTransfer.types.indexOf("Files") >= 0;

		if (!valid) {
			ModalBox.alert("파일 형식이 아닙니다.")
		}

		// console.log("drop : " + e.dataTransfer);
		console.log(e.dataTransfer.files[0].name);

		let url = "/upload";
		let fd = new FormData();
		fd.append("file", e.dataTransfer.files[0]);
		fd.append("title", "제목1");

		let request = new XMLHttpRequest();

		// 파일이 로드되는 동안의 상태(upload)를 알기 위한 progress 이벤트 사용
		// Binary Data 담을 때  이벤트 발생 = > 버퍼가 비워질 때마다 발생.
		request.upload.addEventListener("progress", (e) => { //진척 정도
			console.log(`total : ${e.total}, loaded : ${e.loaded}`);

			if (e.lengthComputable) // 계산할 수 있을 때
				dropZone.firstElementChild.innerText = "진척도 : " + Math.round(e.loaded / e.total * 100) + "%";
			else
				dropZone.firstElementChild.innerText = "전송크기를 계산할 수 없습니다.";

		});

		request.addEventListener("load", (e) => {
			// console.log(e);
			console.log(e.target.responseText);
			// 업로드 완료되면 스타일을 원래로 되돌리기
			CSS.set(dropZone, {
				background: "#e9e9e9",
				borderRadius: "20px"
			});
		});

		request.addEventListener("error", (reason) => {
			console.log(reason);
		});

		request.open("POST", url);
		request.send(fd);
	});

	dropZone.addEventListener("dragover", (e) => {
		e.preventDefault();
		e.stopPropagation();

		// for(let att in e.dataTransfer.types)
		//     console.log(att);
		// console.log(e.dataTransfer.types)
		let valid = e.dataTransfer
			&& e.dataTransfer.types
			&& e.dataTransfer.types.indexOf("Files") >= 0;

		let message = valid ? "드롭하세요" : "유효한 파일이 아닙니다."; // valid ? true : false;

		// if(valid)
		//     CSS
		dropZone.firstElementChild.innerText = message;

		console.log("dragover : " + e.dataTransfer);
	});

	dropZone.addEventListener("dragenter", (e) => {
		e.preventDefault();
		e.stopPropagation();

		CSS.set(dropZone, {
			background: "pink",
			borderRadius: "0px"
		})

		console.log("enter : " + e.dataTransfer);
	});

	dropZone.addEventListener("dragleave", (e) => {
		e.preventDefault();
		e.stopPropagation();

		CSS.set(dropZone, {
			background: "#e9e9e9",
			borderRadius: "20px"
		});

		dropZone.firstElementChild.innerText = "업로드할 파일을 드래그 드롭하세요!";


		console.log("exit");
	});
});