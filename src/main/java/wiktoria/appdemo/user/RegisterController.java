package wiktoria.appdemo.user;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import wiktoria.appdemo.validators.UserRegisterValidator;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	@Autowired
	MessageSource messagesource;
	
	@GetMapping(value = "/register")
	public String registerForm(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "register";
	}

	@PostMapping(value = "/adduser")
	public String registerAction(@Valid @ModelAttribute("user")User user, BindingResult result, Model model, Locale locale) {
		
		String returnPage = null;
		User userExist = userService.findUserByEmail(user.getEmail());
		new UserRegisterValidator().validateEmailExist(userExist, result);
		new UserRegisterValidator().validate(user, result);
		
		if(result.hasErrors()) {
			returnPage = "register";
		} else {
			userService.saveUser(user);
			model.addAttribute("message", messagesource.getMessage("user.register.success", null, locale));
			model.addAttribute("user", new User());
			returnPage = "register";
		}
		
		return returnPage;
		
	}
}
