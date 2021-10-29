let openness = false;
let overlay = document.getElementById("overlay");
let burger = document.getElementById("burger");
let sidebar = document.getElementById("sidebar");
let sidebarElements = document.getElementsByClassName("sidebar-element");
let groupElement = document.getElementById("sidebar-group");
window.onresize = function (e) {
    if(e.target.innerWidth > 767 && openness) {
        navbar();
    }
}
function navbar() {

    burger.classList.toggle("burger-active");
    if (sidebar.classList.contains("sidebar-active")) {
        getOverlay().className = "overlay-not-active";
        document.body.style.overflow = "visible";
        sidebar.className = "sidebar-not-active";
        openness = false;
        getOverlay().remove();
    }
    else {
        createOverlay();
        getOverlay().className = "overlay-active";
        document.body.style.overflow = "hidden";
        sidebar.className = "sidebar-active";
        openness = true;
    }
    for (let sidebarElement of sidebarElements) {
        if (sidebarElement.classList.contains("element-active")) {
            sidebarElement.classList.remove("element-active");
            sidebarElement.classList.add("element-not-active");
        }
        else {
            sidebarElement.classList.remove("element-not-active");
            sidebarElement.classList.add("element-active");
        }
    }
    if(groupElement.classList.contains("right-group-active")) {
        groupElement.classList.remove("right-group-active");
        groupElement.classList.add("right-group-not-active");
    }
    else {
        groupElement.classList.remove("right-group-not-active");
        groupElement.classList.add("right-group-active");
    }
}
function getOverlay() {
    return document.getElementById("overlay");
}
function createOverlay() {
    let overlayBlock = document.createElement("div");
    overlayBlock.id = "overlay";
    overlayBlock.className = "overlay-not-active";
    document.getElementById("main").after(overlayBlock);
}