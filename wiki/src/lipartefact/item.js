class LipartefactItem extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        const children = this.children;
        let properties = {
            itemAttributes: [],
            itemEffects: [],
            itemEnchantments: [],
            itemName: this.id,
            itemObtentions: [],
            itemOthers: [],
        }
        for (var i = 0; i < children.length; i++) {
            var child = children[i];
            if (child.classList.contains("attribute")) {
                properties.itemAttributes.push(child)
            }
            if (child.classList.contains("effect")) {
                properties.itemEffects.push(child)
            }
            if (child.classList.contains("enchantment")) {
                properties.itemEnchantments.push(child)
            }
            if (child.classList.contains("name")) {
                properties.itemName = child.textContent;
            }
            if (child.tagName === "LIPARTEFACT-OBTENTION") {
                properties.itemObtentions.push(child)
            }
            if (child.classList.contains("other")) {
                properties.itemOthers.push(child)
            }
        }
        const element = this.getElement(properties);
        this.innerHTML = "";
        this.appendChild(element);
    }

    getElement(properties) {
        let element = document.createElement("div");
        let previousAttributeSlot = ""
        element.appendChild(this.getElementIcon(this.id));
        element.appendChild(this.getElementName(properties.itemName));
        element.appendChild(document.createElement("br"));
        for (const item of properties.itemOthers) {
            element.appendChild(this.getElementOther(item));
            element.appendChild(document.createElement("br"));
        }
        for (const item of properties.itemEffects) {
            element.appendChild(this.getElementEffect(item));
            element.appendChild(document.createElement("br"));
        }
        for (const item of properties.itemEnchantments) {
            element.appendChild(this.getElementEnchantment(item));
            element.appendChild(document.createElement("br"));
        }
        element.appendChild(this.getElementRarity());
        for (const item of properties.itemAttributes) {
            element.appendChild(document.createElement("br"));
            if (item.hasAttribute("slot")) {
                let slot = item.getAttribute("slot");
                if (previousAttributeSlot !== slot) {
                    element.appendChild(this.getElementAttributeSlot(slot));
                    element.appendChild(document.createElement("br"));
                    previousAttributeSlot = slot;
                }
            }
            else {
                previousAttributeSlot = ""
            }
            element.appendChild(this.getElementAttribute(item));
        }
        element.appendChild(document.createElement("br"));
        element.appendChild(document.createElement("br"));
        element.appendChild(this.getElementObtentionText());
        element.appendChild(document.createElement("br"));
        for (const item of properties.itemObtentions) {
            element.appendChild(this.getElementObtention(item));
        }
        element.classList.add("container");
        return element;
    }

    getElementAttribute(item) {
        let element = document.createElement("a");
        element.style.color = `var(--clr-attribute${this.getElementAttributeColorSuffix(item)})`;
        if (item.classList.contains("attack")) {
            element.innerHTML = "&nbsp;" + item.textContent;
        } else {
            element.innerHTML = item.textContent;
        }
        return element;
    }

    getElementAttributeColorSuffix(itemAttribute) {
        if (itemAttribute.classList.contains("attack")) {
            return "-attack";
        }
        if (itemAttribute.classList.contains("positive")) {
            return "-positive";
        }
        if (itemAttribute.classList.contains("negative")) {
            return "-negative";
        }
        return "";
    }

    getElementAttributeSlot(slot) {
        let element = document.createElement("a");
        const slotDictionary = {
            "chest": "When on Body:",
            "feet": "When on Feet:",
            "head": "When on Head:",
            "legs": "When on Legs:",
            "mainhand": "When in Main Hand:",
            "offhand": "When in Off Hand:",
        };
        element.style.color = `var(--clr-attribute-slot)`;
        element.textContent = slotDictionary[slot];
        return element;
    }

    getElementEnchantment(item) {
        let element = document.createElement("a");
        element.style.color = item.hasAttribute("rarity") ? `var(--clr-enchantment-${item.getAttribute("rarity")})` : "var(--clr-enchantment)";
        element.textContent = item.textContent;
        return element;
    }

    getElementEffect(item) {
        let element = document.createElement("a");
        element.style.color = "var(--clr-effect)";
        element.textContent = item.textContent;
        return element;
    }

    getElementIcon(itemID) {
        let element = document.createElement("img");
        element.classList.add("icon");
        element.classList.add("item");
        element.setAttribute("alt", "");
        element.setAttribute("src", `img/item/${itemID}.png`);
        return element;
    }

    getElementName(itemName) {
        let element = document.createElement("a");
        element.classList.add("name");
        element.style.color = `var(--clr-rarity-${this.getRarity()})`;
        element.textContent = itemName;
        return element;
    }

    getElementObtention(item) {
        return item;
    }

    getElementObtentionText() {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other)";
        element.textContent = "Obtained via:";
        return element;
    }

    getElementOther(item) {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other)";
        element.textContent = item.textContent;
        return element;
    }

    getElementRarity() {
        let element = document.createElement("a");
        const rarityDictionary = {
            "crafted": "Crafted Artefact",
            "common": "Common Artefact",
            "uncommon": "Uncommon Artefact",
            "rare": "Rare Artefact",
            "epic": "Epic Artefact",
        };
        element.classList.add("rarity");
        element.style.color = `var(--clr-rarity-${this.getRarity()}-secondary)`;
        element.textContent = rarityDictionary[this.getRarity()];
        return element;
    }

    getRarity() {
        return this.getAttribute("rarity");
    }
}

customElements.define("lipartefact-item", LipartefactItem);