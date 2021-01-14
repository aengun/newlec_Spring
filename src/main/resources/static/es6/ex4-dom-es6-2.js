

import DragBox from "./modules/DragBox.js";
import ModalBox from "./modules/ModalBox.js";
window.addEventListener("load", (e) => {

	let dragBox = new DragBox("#s1 .container");

});

window.addEventListener("load", (e) => {

	let section = document.querySelector("#s2");
	let alertButton = section.querySelector(".alert-button");

	alertButton.onclick = (e) => {
		(async () => {
			let result = await ModalBox.alert("hello");
			console.log(result + "가(이) 눌렸구나!!");
		})();

	}

});

// ==================================================

import CSS from './modules/CSS.js';

window.addEventListener("load", (e) => {
	
	let uploadBox = new UploadBox("#s3", ".drop-zone");
	
});