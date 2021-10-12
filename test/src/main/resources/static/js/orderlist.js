let nameException = document.getElementById("name-exception");
nameException.innerText = "";

function handleSubmit() {
    let answer = true;
    let nameAnswer = validation("name");
    let addressAnswer= validation("address");
    if(!nameAnswer || !addressAnswer){
        answer = false;
    }
    return answer;
}
function validation(id) {
    let answer = true;
    let input = document.getElementById(id);
    let label = document.getElementById(id + "-label")
    let exception = document.getElementById(id + "-exception");
    let lang = document.body.lang;
    input.classList.remove("error");
    exception.innerText="";
    let field = input.value.trim();
    if(field === "") {
        if(lang === 'ru') {
            exception.innerText = label.innerText + " не может быть пустым или содержать пробелы!";
        }
        else {
            exception.innerText = label.innerText + " can't be empty or keep only spaces!";
        }
        answer = false
    }
    if (field.length < 2 || field.length >= 15) {
        document.getElementById(id).classList.add("error");
        if(lang === 'ru') {
            exception.innerText = label.innerText + " должно быть больше 2 символов и меньше 15!";
        }
        else {
            exception.innerText = label.innerText + " must be more than 2 symbols and less than 15 symbols!";
        }
        answer = false;
    }
    return answer;
}