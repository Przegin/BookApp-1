package wiktoria.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import wiktoria.appdemo.constants.AppDemoConstants;
import wiktoria.appdemo.user.User;
import wiktoria.appdemo.utilities.AppDemoUtils;

public class ChangePasswordValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		@SuppressWarnings("unused")
		User u = (User) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "error.userPassword.empty");			//gdy pole jest puste
		
	}
	
	public void checkPasswords(String newPass, Errors errors) {										//metoda sprawdza czy hasło  odpowiada wzorcowi
		
		if (!newPass.equals(null)) {
			boolean isMatch = AppDemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, newPass);
			if(!isMatch) {
				errors.rejectValue("newPassword", "error.userPasswordIsNotMatch");
			}
		}
	}
	
}
