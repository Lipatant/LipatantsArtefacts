class LipartefactObtention extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        const element = this.getElement();
        this.innerHTML = "";
        this.appendChild(element);
    }

    getElement() {
        let element = document.createElement("div");
        element.appendChild(this.getElementIcon(this.id));
        element.appendChild(this.getElementName(this.innerText));
        element.classList.add("container");
        return element;
    }

    getElementIcon(id) {
        let element = document.createElement("img");
        element.classList.add("icon");
        element.classList.add("item");
        element.setAttribute("alt", "");
        element.setAttribute("src", `img/obtention/${id}.png`);
        return element;
    }

    getElementName(itemName) {
        let element = document.createElement("a");
        element.classList.add("name");
        element.style.color = "var(--clr-obtention)";
        element.innerHTML = "&nbsp;" + itemName;
        return element;
    }

}

function replaceObtention(element, data) {
    element.id = data.id;
    if (element.innerText === "") {
        element.innerText = data.text;
    } else {
        element.innerText = data.text + " - " + element.innerText;
    }
}

const replacedList = [
    {
        id: "ancient_cities",
        text: "Ancient Cities",
    },
    {
        id: "abandoned_mineshafts",
        text: "Abandoned Mineshafts",
    },
    {
        id: "bastions",
        text: "Bastions",
    },
    {
        id: "cat_morning_gift",
        text: "Cats - Morning Gift",
    },
    {
        id: "crafting",
        text: "Crafting",
    },
    {
        id: "end_cities",
        text: "End Cities",
    },
    {
        id: "fishing",
        text: "Fishing",
    },
    {
        id: "igloos",
        text: "Igloos",
    },
    {
        id: "jungle_temples",
        text: "Jungle Temples",
    },
    {
        id: "monster_rooms",
        text: "Monster Rooms",
    },
    {
        id: "piglin_bartering",
        text: "Piglins - Bartering",
    },
    {
        id: "pillager_outposts",
        text: "Pillager Outposts",
    },
    {
        id: "shipwrecks",
        text: "Shipwrecks",
    },
    {
        id: "strongholds",
        text: "Strongholds",
    },
    {
        id: "trail_ruins",
        text: "Trail Ruins",
    },
    {
        id: "trial_chambers",
        text: "Trial Chambers",
    },
    {
        id: "trial_chambers_ominous_vault",
        text: "Trial Chambers - Ominous Vault",
    },
    {
        id: "underwater_ruins",
        text: "Underwater Ruins",
    },
    {
        id: "villages",
        text: "Villages",
    },
    {
        id: "woodland_mansions",
        text: "Woodland Mansions",
    }
]
for (const replaced of replacedList) {
    var elementsReplaced = document.getElementsByClassName(replaced.id);
    for (let element of elementsReplaced) {
        if (element.tagName === "LIPARTEFACT-OBTENTION") {
            replaceObtention(element, replaced)
        }
    }
}

customElements.define("lipartefact-obtention", LipartefactObtention);