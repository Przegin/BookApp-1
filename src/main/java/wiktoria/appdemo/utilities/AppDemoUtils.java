package wiktoria.appdemo.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppDemoUtils {
		
	public static boolean checkEmailOrPassword(String pattern, String pStr) {		//łańcuch znaków(któryś z wzroców), 2 arg co wpisał użytkownik w formularzu
		Pattern p = Pattern.compile(pattern);		//metoda sprawdzająca email i hasło czy pasują do wzorca wyr. regularnych
		Matcher m = p.matcher(pStr);				
		return m.matches();
	}
}
