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

@Controller											//kontroler do wyświetlenia formularza
public class RegisterController {

	@Autowired
	UserService userService;
	@Autowired
	MessageSource messagesource;					//pozwala pobierać z msg.properties komunikaty w kodzie java
	
	@GetMapping(value = "/register")			//reqMapp - obsługa wywołania
	public String registerForm(Model model) {		//arg model(zas mapy), pozwala przekazać do strony User - obiekt
		User u = new User();
		model.addAttribute("user", u);				//modelAtt ten sam
		return "register";							//zwracamy nazwę strony, do której sięgnie spring, bez rozszerzenia bu sufix ustawiony na .jsp(w app.properties)
	}
												//ozn get - odebranie danych
	/*@GetMapping(value = "/error")			//reqMapp - obsługa wywołania
	public String errorForm(Model model) {		//arg model(zas mapy), pozwala przekazać do strony User - obiekt
		User u = new User();
		model.addAttribute("user", u);				//modelAtt ten sam
		return "error";							//zwracamy nazwę strony, do której sięgnie spring, bez rozszerzenia bu sufix ustawiony na .jsp(w app.properties)
	} */
											//wsad do bazy
	@PostMapping(value = "/adduser")
	public String registerAction(@Valid @ModelAttribute("user")User user, BindingResult result, Model model, Locale locale) {				//user to co odbierzemy z formularza, binding czy zawiera błedy formularz, model wrzucimy pewne rzeczy po rejestracji, locale pozwoli posłużyć się messagesource
		
		String returnPage = null;																			//strona zwrócona po rejestracji niezależnie czy są błedy
		
		User userExist = userService.findUserByEmail(user.getEmail());										//czy dany email jest już w bazie
		
		new UserRegisterValidator().validateEmailExist(userExist, result);									//jeśli jest, wynik do walidatora przekazujemy	
		
		new UserRegisterValidator().validate(user, result);													//wywołanie validatora czy wszystkie dane są poprawne, jeśli nie istnieje taki user
																											//validator uruchamiany dwukrotnie - pierwsze dla maila, drugie dla reszty danych czy poprawne
		
		if(result.hasErrors()) {
			returnPage = "register";																		//jeśli są błędy, od razu na stronę rejestrowania
		} else {
			userService.saveUser(user);																			//błedów nie ma - wywołujemy zapis usera
			model.addAttribute("message", messagesource.getMessage("user.register.success", null, locale));		//komunikat o poprawnej rejestracji
			model.addAttribute("user", new User());																//nowy obiekt user do modelu, żeby nie powielić wypełnionego formularza, zeby wyświetlił się pusty
			returnPage = "register";
		}
		
		return returnPage;																						//tu mamy zostać przekierowani
		
	}															//przekazujemy do tej metody obiekt user, któremu zostanie rzypisane to, co wróci z formularza
																//obsługa błędów - do result trafią wszystkie błedy z walidatora
																//model do przekazania jakichś informacji po sukcesie zarejestrowaniu
}
