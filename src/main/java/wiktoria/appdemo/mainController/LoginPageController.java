package wiktoria.appdemo.mainController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {			//kontroler, ktory tylko i wyłącznie wyświetla str logowania, samym logowaniem zajmuje się spring sec

	@GetMapping(value = {"/login"})			//wyłapuje z url
	public String showLoginPage() {
		
		return "login";
	}

	
}
