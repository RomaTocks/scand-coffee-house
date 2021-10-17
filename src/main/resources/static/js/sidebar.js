function navbar() {
    let burger = document.getElementById("burger");
    burger.classList.toggle("burger-active");
    let sidebar = document.getElementById("sidebar");
    if (sidebar.classList.contains("sidebar-active")) {
        sidebar.className = "sidebar-not-active";
    }
    else {
        sidebar.className = "sidebar-active";
    }
    let sidebarElements = document.getElementsByClassName("sidebar-element");
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
    let groupElement = document.getElementById("sidebar-group");
    if(groupElement.classList.contains("right-group-active")) {
        groupElement.classList.remove("right-group-active");
        groupElement.classList.add("right-group-not-active");
    }
    else {
        groupElement.classList.remove("right-group-not-active");
        groupElement.classList.add("right-group-active");
    }
}