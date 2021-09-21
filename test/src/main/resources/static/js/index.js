function handleSubmit() {
    let answer = true;
    let checkboxCounts = 0;
    let emptyInputsCounts = 0;
    let countException = document.getElementById("count-exception");
    let coffeeException = document.getElementById("coffee-exception");
    let checkboxes = document.getElementsByClassName("checkbox");
    countException.classList.add("disabled");
    coffeeException.classList.add("disabled");
    for (let checkbox of checkboxes) {
        let input = document.getElementById("input" + checkbox.id);
        if(checkbox.checked) {
            checkboxCounts++;
        }
        if(checkbox.checked && !checkCount(checkbox.id)) {
            emptyInputsCounts++;
            input.className = "error-border";
        }
        else {
            input.classList.remove("error-border");
        }
    }
    if(emptyInputsCounts !== 0) {
        countException.classList.remove("disabled");
        answer = false;
    }
    if(checkboxCounts === 0) {
        coffeeException.classList.remove("disabled");
        answer = false;
    }
    return answer;
}
function checkCount(id) {
    let input = document.getElementById("input" + id);
    let value = Number.parseInt(input.value);
    if(value < 1 || isNaN(value) || typeof value === "string") {
        return false
    }
    else {
        return true;
    }
}