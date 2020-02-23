function doSomeWork() {
	
	alert('NICOLAS CAGE BY OCENIŁ');
	return true;
}

function rate(i){
	
	alert("Oceniłeś książkę na "+i);

}

function loginValidate() {
    var email = document.getElementById('email').value;
    var pswrd = document.getElementById('password').value;
    var temp = 0;
    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
        document.getElementById('emailValidation').innerHTML = 'Nieprawidłowy adres e-mail';
        temp = 1;
    } else {
        document.getElementById('emailValidation').innerHTML = '';
    }

    if (!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/.test(pswrd)) {
        document.getElementById('passwordValidation').innerHTML = 'Nieprawidłowe hasło';					//jeśli wydarzy się cokolwiek co wywołuje temp=1, bład logowania
        temp = 1;
    } else {
        document.getElementById('passwordValidation').innerHTML = '';
    }

    if (temp == 1) {
        return false;
    	alert('Nie zalogowałeś się');
    }
}