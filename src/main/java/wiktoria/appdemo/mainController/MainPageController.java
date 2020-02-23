package wiktoria.appdemo.mainController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wiktoria.appdemo.user.User;

@Controller
public class MainPageController {
	
	@GetMapping(value = {"/", "/index"})
	public String showMyPage(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
/*	@GetMapping(value = {"/login"})
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	} */

}
