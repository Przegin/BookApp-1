package wiktoria.appdemo.mainController;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@GetMapping(value = "/error")
	public String showErrorPage() {
		return "error";
	}
	
}
