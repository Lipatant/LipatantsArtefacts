class LipartefactSlideshow extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        let id = 0;
        const children = this.children;
        for (var i = 0; i < children.length; i++) {
            var child = children[i];
            if (child.tagName === "IMG") {
                child.id = id;
                id += 1;
            }
        }
        this.imgCount = id;
        this.imgCurrent = id;
        this.dotContainer = this.getDotContainer();
        this.appendChild(this.dotContainer);
        this.select(0)
    }

    getDot(id) {
        var dot = document.createElement("a");
        dot.classList.add("dot");
        dot.id = id;
        dot.text = id + 1;
        dot.setAttribute("onclick", `lipartefactSlideshowDot(this)`);
        return dot;
    }

    getDotPrevious() {
        var dot = document.createElement("a");
        dot.classList.add("dot");
        dot.text = "<";
        dot.setAttribute("onclick", `lipartefactSlideshowButtonPrevious(this)`);
        return dot;
    }

    getDotNext() {
        var dot = document.createElement("a");
        dot.classList.add("dot");
        dot.text = ">";
        dot.setAttribute("onclick", `lipartefactSlideshowButtonNext(this)`);
        return dot;
    }

    getDotContainer() {
        var container = document.createElement("div");
        container.classList.add("dot-container");
        container.appendChild(this.getDotPrevious());
        for (var i = 0; i < this.imgCount; i++) {
            container.appendChild(this.getDot(i));
        }
        container.appendChild(this.getDotNext());
        return container;
    }

    select(id) {
        const children = this.children;
        const dotContainerChildren = this.dotContainer.children;
        for (var i = 0; i < children.length; i++) {
            var child = children[i];
            if (child.tagName === "IMG") {
                if (child.id == id) {
                    if (child.classList.contains("hide")) {
                        child.classList.remove("hide");
                    }
                } else {
                    if (!child.classList.contains("hide")) {
                        child.classList.add("hide");
                    }
                }
            }
        }
        for (var i = 0; i < dotContainerChildren.length; i++) {
            var child = dotContainerChildren[i];
            if (child.hasAttribute("id") && child.id == id) {
                if (!child.classList.contains("active")) {
                    child.classList.add("active");
                }
            } else {
                if (child.classList.contains("active")) {
                    child.classList.remove("active");
                }
            }
        }
        this.imgCurrent = id;
    }
}

function lipartefactSlideshowButtonNext(element) {
    const slideshow = element.parentNode.parentNode;
    if (slideshow.imgCount >= 2) {
        slideshow.select((slideshow.imgCurrent + 1) % slideshow.imgCount)
    }
}

function lipartefactSlideshowButtonPrevious(element) {
    const slideshow = element.parentNode.parentNode;
    if (slideshow.imgCount >= 2) {
        slideshow.select(((slideshow.imgCurrent < 1) ? slideshow.imgCount : slideshow.imgCurrent) - 1);
    }
}

function lipartefactSlideshowDot(element) {
    element.parentNode.parentNode.select(element.id);
}

customElements.define("lipartefact-slideshow", LipartefactSlideshow);