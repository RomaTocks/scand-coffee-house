let nameException = document.getElementById("name-exception");
nameException.innerText = "";
function handleSubmit() {
    let answer = true;
    let nameAnswer = validation("ФИО","name");
    let addressAnswer= validation("Адрес","address");
    if(!nameAnswer || !addressAnswer){
        answer = false;
    }
    return answer;
}
function validation(name, id) {
    let answer = true;
    let element = document.getElementById(id);
    let exception = document.getElementById(id + "-exception");
    element.classList.remove("error");
    exception.innerText="";
    let field = element.value.trim();
    if(field === "") {
        exception.innerText = name + " не может быть пустым или содержать пробелы!";
        answer = false;
    }
    if (field.length < 2 || field.length >= 15) {
        document.getElementById(id).classList.add("error");
        exception.innerText = name + " должно быть больше 2 символов и меньше 15!";
        answer = false;
    }
    return answer;
}