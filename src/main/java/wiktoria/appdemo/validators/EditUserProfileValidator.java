package wiktoria.appdemo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import wiktoria.appdemo.constants.AppDemoConstants;
import wiktoria.appdemo.user.User;
import wiktoria.appdemo.utilities.AppDemoUtils;

public class EditUserProfileValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User u = (User) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastname", "error.userlastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");

		
		if (!u.getEmail().equals(null)) {
			boolean isMatch = AppDemoUtils.checkEmailOrPassword(AppDemoConstants.EMAIL_PATTERN, u.getEmail());
			if(!isMatch) {
				errors.rejectValue("email", "error.userEmailIsNotMatch");
			}
		}
	}

}
