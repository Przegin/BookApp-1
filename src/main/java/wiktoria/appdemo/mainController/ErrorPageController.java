package wiktoria.appdemo.mainController;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";					//przykrywa każdą stronę z errorem właśnie tym
	}

	@GetMapping(value = "/error")			//obsługa tego wywołania
	public String showErrorPage() {			//łapie wywołanie "/error" i zwraca moją stronę error jsp
		return "error";
	}
	
}
