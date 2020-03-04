package wiktoria.appdemo.mainController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

	@GetMapping(value = {"/login"})
	public String showLoginPage() {

		return "login";
	}


}
