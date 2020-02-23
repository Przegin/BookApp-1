package wiktoria.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import wiktoria.appdemo.constants.AppDemoConstants;
import wiktoria.appdemo.user.User;
import wiktoria.appdemo.utilities.AppDemoUtils;

public class UserRegisterValidator implements Validator {		//implementacja interfejsu validator

	@Override
	public boolean supports(Class<?> cls) {						//pierwsza metoda interfejsu - przyjmuje jako arg klasę
		return User.class.equals(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {			//właściwy walidator, arg to obiekt
		User u = (User) obj;									//rzutowanie na obiekt User z ogólnego obiektu
		
		ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");			//metody abstrakcyjnej klasy ValidationUtils, error w razie gdy pole puste
		ValidationUtils.rejectIfEmpty(errors, "lastname", "error.userLastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.userPassword.empty");
		
		if (!u.getEmail().equals(null)) {
			boolean isMatch = AppDemoUtils.checkEmailOrPassword(AppDemoConstants.EMAIL_PATTERN, u.getEmail());	//poza metodami 2 wyjątki - gdy pass i mail nie są puste - sprawdzanie hasła i maila
			if(!isMatch) {
				errors.rejectValue("email", "error.userEmailIsNotMatch");										//error inny - odnośni błednego maila
			}
		}
		
		if (!u.getPassword().equals(null)) {
			boolean isMatch = AppDemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, u.getPassword());
			if(!isMatch) {
				errors.rejectValue("password", "error.userPasswordIsNotMatch");
			}
		}
		
	}
	
	public void validateEmailExist(User user, Errors errors) {				//metoda wywoływana w razie istniejącego już maila w bazie = user niepusty, mail już istnieje
		if (user != null) {
			errors.rejectValue("email", "error.userEmailExist");
		}
	}
}
