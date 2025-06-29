const itemContainer = document.getElementById("item-container")

function checkForCrafted(element, crafted) {
    return ((element.hasAttribute("crafted") && (element.getAttribute("crafted") === "true")) === crafted);
}

function checkForObtention(element, obtention) {
    if (obtention === "") {
        return true;
    }
    const children = element.children[0].children;
    let child;
    for (let i = 0; i < children.length; i++) {
        child = children[i];
        if (child.tagName === "LIPARTEFACT-OBTENTION" && child.hasAttribute("id")) {
            if (Array.isArray(obtention)) {
                if (obtention.includes(child.getAttribute("id"))) {
                    return true
                }
            } else if (child.getAttribute("id") === obtention) {
                return true
            }
        }
    }
    return false
}

function checkForRarity(element, rarity) {
    if (rarity === "") {
        return true;
    }
    if (element.hasAttribute("rarity")) {
        return element.getAttribute("rarity") === rarity;
    }
    return false
}

function filterItemContainerByCrafted(crafted = false) {
    let child;
    const children = itemContainer.children;
    for (let i = 0; i < children.length; i++) {
        child = children[i];
        if (checkForCrafted(child, crafted)) {
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

function filterItemContainerByObtention(obtention = "") {
    let child;
    const children = itemContainer.children;
    for (let i = 0; i < children.length; i++) {
        child = children[i];
        if (checkForObtention(child, obtention)) {
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

function filterItemContainerByRarity(rarity = "") {
    let child;
    const children = itemContainer.children;
    for (let i = 0; i < children.length; i++) {
        child = children[i];
        if (checkForRarity(child, rarity)) {
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

function filterItemContainerReset() {
    let child;
    const children = itemContainer.children;
    for (let i = 0; i < children.length; i++) {
        child = children[i];
        if (child.classList.contains("hide")) {
            child.classList.remove("hide");
        }
    }
}