import CSS from './modules/CSS.js';

class UploadBox {

	#dropZone;

	constructor(dropZone) {

		if (typeof dropZone == "string")
			this.#dropZone = document.querySelector(dropZone);
		else
			this.#dropZone = dropZone;

		this.#dropZone.addEventListener("drop", this.dropHandler.bind(this));
		this.#dropZone.addEventListener("dragover", this.dragoverHandler.bind(this));
		this.#dropZone.addEventListener("dragenter", this.dragenterHandler.bind(this));
		this.#dropZone.addEventListener("dragleave", this.dragleaveHandler.bind(this));

	}

	#dropHandler(e) {
		e.preventDefault();
		e.stopPropagation();

		let valid = e.dataTransfer
			&& e.dataTransfer.types
			&& e.dataTransfer.types.indexOf("Files") >= 0;

		if (!valid) {
			ModalBox.alert("파일 형식이 아닙니다.")
		}

		console.log(e.dataTransfer.files[0].name);

		let url = "/upload";
		let fd = new FormData();
		fd.append("file", e.dataTransfer.files[0]);
		fd.append("title", "제목1");

		let request = new XMLHttpRequest();

		request.upload.addEventListener("progress", (e) => {
			console.log(`total : ${e.total}, loaded : ${e.loaded}`);

			if (e.lengthComputable)
				this.#dropZone.firstElementChild.innerText = "진척도 : " + Math.round(e.loaded / e.total * 100) + "%";
			else
				this.#dropZone.firstElementChild.innerText = "전송크기를 계산할 수 없습니다.";

		});

		request.addEventListener("load", (e) => {
			console.log(e.target.responseText);
			CSS.set(this.#dropZone, {
				background: "#e9e9e9",
				borderRadius: "20px"
			});
		});

		request.addEventListener("error", (reason) => {
			console.log(reason);
		});

		request.open("POST", url);
		request.send(fd);
	};

	#dragoverHandler(e) {
		e.preventDefault();
		e.stopPropagation();

		let valid = e.dataTransfer
			&& e.dataTransfer.types
			&& e.dataTransfer.types.indexOf("Files") >= 0;

		let message = valid ? "드롭하세요" : "유효한 파일이 아닙니다.";

		this.#dropZone.firstElementChild.innerText = message;

		console.log("dragover : " + e.dataTransfer);
	};

	#dragenterHandler(e) {
		e.preventDefault();
		e.stopPropagation();

		CSS.set(this.#dropZone, {
			background: "pink",
			borderRadius: "0px"
		})

		console.log("enter : " + e.dataTransfer);
	};

	#dragleaveHandler(e) {
		e.preventDefault();
		e.stopPropagation();

		CSS.set(this.#dropZone, {
			background: "#e9e9e9",
			borderRadius: "20px"
		});

		this.#dropZone.firstElementChild.innerText = "업로드할 파일을 드래그 드롭하세요!";


		console.log("exit");
	};

}

export default UploadBox