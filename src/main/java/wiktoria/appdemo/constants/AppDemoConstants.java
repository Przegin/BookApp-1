package wiktoria.appdemo.constants;

public class AppDemoConstants {
	
public static final String EMAIL_PATTERN = "^[a-zA-z0-9]+[\\._a-zA-Z0-9]*@[a-zA-Z0-9]+{2,}\\.[a-zA-Z]{2,}[\\.a-zA-Z0-9]*$";          //czy poprawna składnia
	
	public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$"; 		//czy duże i małe, czy cyfry, nie zwiera spacji, ma znak specjalny, a dł 8-12

}
