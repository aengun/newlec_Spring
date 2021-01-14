/*class UploadBox{
	
	#section;
	#dropZone;
	
	constructor(section, dropZone){
		if (typeof section == "string")
            this.#sectoin = document.querySelector(section);
        else
            this.#section = section;

		if (typeof dropZone == "string")
            this.#dropZone = document.querySelector(dropZone);
        else
            this.#dropZone = dropZone;
	}

	dropZone.addEventListener("drop", (e) => {
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
				dropZone.firstElementChild.innerText = "진척도 : " + Math.round(e.loaded / e.total * 100) + "%";
			else
				dropZone.firstElementChild.innerText = "전송크기를 계산할 수 없습니다.";

		});

		request.addEventListener("load", (e) => {
			console.log(e.target.responseText);
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

		let valid = e.dataTransfer
			&& e.dataTransfer.types
			&& e.dataTransfer.types.indexOf("Files") >= 0;

		let message = valid ? "드롭하세요" : "유효한 파일이 아닙니다.";

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

}

export default UploadBox*/