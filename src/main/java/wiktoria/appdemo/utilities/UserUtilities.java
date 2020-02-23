package wiktoria.appdemo.utilities;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtilities {
	
	public static String getLoggedUser() {			//nazwa zalog usera
		String username = null;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();			//przechwycenie kontekstu		
		if(!(auth instanceof AnonymousAuthenticationToken)) {									//jeśli w kontekście jest user niezautentykowany, nieznany to pobiera z kontekstu nazwę zalogowanego
			username = auth.getName();															//inaczej null
		}
		
		return username;
	}

}
