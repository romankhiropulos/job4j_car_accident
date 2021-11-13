function validate() {
    let valid = true;
    let name = document.getElementById('name').value;
    let address = document.getElementById('address').value;
    let options = document.querySelectorAll('#rules option:checked');
    let rules = Array.from(options).map(option => option.value) ;
    if (name === '') {
        valid = false;
        alert("Пожалуйста заполните поле \"Название\"");
    }
    if (address === '') {
        valid = false;
        alert("Пожалуйста заполните поле \"Адрес\"");
    }
    if (rules.length === 0) {
        valid = false;
        alert("Пожалуйста заполните поле \"Статьи\"");
    }
    return valid;
}