const languagesList = ['ru', 'en'];
let downloadedLanguagesCount = 0;
const languages = {};
let currentLang;

loadLocalLanguage();
loadJSON();

function setLanguage(element) {
    currentLang = element.value;
    localStorage.setItem("language", element.value);
    changeLanguage();
}
function changeLanguage() {
    updateLanguagesButtons();
    document.body.setAttribute("lang",currentLang)
    const allTranslatedFields = document.querySelectorAll("[data-i18n]");
    for (let field of allTranslatedFields) {
        const wordKey = field.getAttribute("data-i18n");
        const translatedWord = languages[currentLang][wordKey].message;
        if(field.tagName === "INPUT") field.value = translatedWord;
        else field.innerText = translatedWord;
    }
}
function languagesReady() {
    if (++downloadedLanguagesCount < languagesList.length) return;
    let buttons = document.getElementById("lang-buttons");
    buttons.className = "form_toggle";
    changeLanguage();
}
function loadLocalLanguage() {
    let localLang = localStorage.getItem("language")
    if( localLang !== null) {
        currentLang = localLang;
        updateLanguagesButtons();
    }
    else {
        localStorage.setItem("language", 'ru')
        currentLang = 'ru';
        updateLanguagesButtons();
    }
}
function updateLanguagesButtons() {
    let buttons = document.getElementsByClassName("language");
    let currentButtons = [];
    for (let button of buttons) {
        button.removeAttribute("checked");
        if(button.value === currentLang) {
            currentButtons.push(button);
        }
    }
    currentButtons.forEach(button => {
        button.checked = true;
        button.setAttribute("checked",true)
    });
}
function loadJSON() {
    languagesList.forEach(lang => {
        fetch(window.location.origin + '/_locales/'+ lang +'/' + lang + '.json')
            .then((response) => {
                return response.json();
            })
            .then(data => {
                    languages[lang] = data;
                    languagesReady();
                }
            );
    });
}
