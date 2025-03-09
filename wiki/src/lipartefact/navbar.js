class LipartefactNavbar extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        const navbar = this.getNavbar();
        this.appendChild(navbar);
    }

    getNavbar() {
        let navbar = document.createElement("ul");
        const pages = {
            "index.html": "Welcome",
            "https://github.com/Lipatant/LipatantsArtefacts/releases/": "Download",
            "item_list.html": "Item List",
            "changelog.html": "Changelog",
            "https://github.com/Lipatant/LipatantsArtefacts": "Source code",
        };
        for (const [link, title] of Object.entries(pages)) {
            let navbarPage = document.createElement("li");
            let navbarPageUrl = document.createElement("a");
            navbarPageUrl.href = link;
            navbarPageUrl.textContent = title;
            navbarPage.appendChild(navbarPageUrl);
            navbar.appendChild(navbarPage);
        }
        return navbar;
    }
}

customElements.define("lipartefact-navbar", LipartefactNavbar);