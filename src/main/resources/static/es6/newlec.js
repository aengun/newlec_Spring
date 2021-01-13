


// s13 trigger ==================================
window.addEventListener("load", function () {

    var section = document.querySelector("#s13");

    var container = section.querySelector(".container");
    var box = container.querySelector(".box");

    var down = false;
    // var offX = 0;
    // var offY = 0;
    var offset = { x: 0, y: 0 }

    box.onclick = function(e){
        console.log(e.x);
        console.log(e.y);
    }

    container.onmousedown = function () {
        console.log("down");
        down = true;
    };

    container.onmouseup = function () {
        console.log("up");
        down = false;
    };

    container.onmouseenter = function () {
        console.log("enter");
    };

    container.onmouseout = function () {
        console.log("out");
    };

    container.onmouseleave = function () {
        console.log("leave");
    };

    container.onmousemove = function (e) {
        // console.log("move");
        // e.x; //clientX
        // e.y; //clientY
        // console.log("clientX : " + e.x);
        // console.log("clientY : " + e.y);
        // console.log("screenX : " + e.screenX);
        // console.log("screenY : " + e.screenY);

        if (down) {
            box.style.left = e.x + "px";
            box.style.top = e.y + "px";
        }
    };

    container.onmouseover = function () {
        console.log("over");
    };

});