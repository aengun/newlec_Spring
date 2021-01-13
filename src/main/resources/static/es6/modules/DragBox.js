class DragBox {

    #container;
    #curItem;
    #offset;
    #containerOffset;

    constructor(container) {

        if (typeof container == "string")
            this.#container = document.querySelector(container);
        else
            this.#container = container;

        this.#curItem = null;
        this.#offset = { x: 0, y: 0 };
        this.#containerOffset = { x: this.#container.offsetLeft, y: this.#container.offsetTop };

        this.#container.addEventListener("mousemove", this.mousemoveHandler.bind(this));
        this.#container.addEventListener("mousedown", this.mousedownHandler.bind(this));
        this.#container.addEventListener("mouseup", this.mouseupHandler.bind(this));

    }

    mousemoveHandler(e) {

        e.preventDefault();
        if (!this.#curItem) return;

        let { x: scrollX, y: scrollY } = this.getScrollPosition();
        let { x: orginX, y: orginY } = this.#containerOffset;
        
        this.#curItem.style.left = e.x - this.#offset.x - orginX + scrollX + "px";
        this.#curItem.style.top = e.y - this.#offset.y - orginY + scrollY + "px";

    }

    mousedownHandler(e) {

        if (!e.target.classList.contains("box")) return;

        this.#curItem = e.target;
        this.#offset = { x: e.offsetX, y: e.offsetY };

    }

    mouseupHandler(e) {

        this.#curItem = null;

    }

    getScrollPosition() {

        let x = window.pageXOffset || document.documentElement.scrollLeft;
        let y = window.pageYOffset || document.documentElement.scrollTop;
        return { x, y };

    }

}

export default DragBox;