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
            itemSmithingTemplate: null,
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
            if (child.classList.contains("smithing_template")) {
                properties.itemSmithingTemplate = child;
            }
        }
        const element = this.getElement(properties);
        this.innerHTML = "";
        this.appendChild(element);
    }

    getElement(properties) {
        let element = document.createElement("div");
        let isCrafted = false
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
        if (properties.itemSmithingTemplate) {
            element.appendChild(this.getElementSmithingTemplate());
            element.appendChild(document.createElement("br"));
            element.appendChild(this.getElementSmithingTemplateAppliesTo());
            element.appendChild(document.createElement("br"));
            element.appendChild(this.getElementSmithingTemplateAppliesToElement(properties.itemSmithingTemplate));
            element.appendChild(document.createElement("br"));
            element.appendChild(this.getElementSmithingTemplateIngredients());
            element.appendChild(document.createElement("br"));
            element.appendChild(this.getElementSmithingTemplateIngredientsElement(properties.itemSmithingTemplate));
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
        if (properties.itemObtentions.length > 0) {
            element.appendChild(document.createElement("br"));
            element.appendChild(document.createElement("br"));
            element.appendChild(this.getElementObtentionText());
            element.appendChild(document.createElement("br"));
            for (const item of properties.itemObtentions) {
                element.appendChild(this.getElementObtention(item));
            }
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
            "common": "Common Artefact",
            "uncommon": "Uncommon Artefact",
            "rare": "Rare Artefact",
            "epic": "Epic Artefact",
        };
        element.classList.add("rarity");
        element.style.color = `var(--clr-rarity-${this.getRarity()}-secondary)`;
        if (this.getIsCrafted()) {
            element.textContent = "Crafted Artefact";
        } else {
            element.textContent = rarityDictionary[this.getRarity()] || "Artefact";
        }
        return element;
    }

    getElementSmithingTemplate() {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other)";
        element.textContent = "Smithing Template"
        return element;
    }
    
    getElementSmithingTemplateAppliesTo() {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other)";
        element.textContent = "Applies to:"
        return element;
    }

    getElementSmithingTemplateAppliesToElement(smithingTemplate) {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other-blue)";
        element.innerHTML = "&nbsp;" + (smithingTemplate.hasAttribute("applies_to") ? smithingTemplate.getAttribute("applies_to") : "???");
        return element;
    }

    getElementSmithingTemplateIngredients() {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other)";
        element.textContent = "Ingredients:"
        return element;
    }

    getElementSmithingTemplateIngredientsElement(smithingTemplate) {
        let element = document.createElement("a");
        element.style.color = "var(--clr-other-blue)";
        element.innerHTML = "&nbsp;" + (smithingTemplate.hasAttribute("ingredients") ? smithingTemplate.getAttribute("ingredients") : "???");
        return element;
    }

    getIsCrafted() {
        return this.hasAttribute("crafted") && (this.getAttribute("crafted") === "true");
    }

    getRarity() {
        return this.getAttribute("rarity");
    }
}

customElements.define("lipartefact-item", LipartefactItem);