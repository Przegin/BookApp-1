package wiktoria.appdemo.user;

import java.util.List;

public interface UserService {											//opisuje to, co implementacja może robić dalej, np w klasie dao - zapewniająej dost do bazy

	public User findUserByEmail(String email);							//odnajdywanie usera po mailu
	public void saveUser(User user);									//zapis usera
	public void updateUserPassword(String newPassword, String email);	//zmiana hasła
	public void updateUserProfile(String newName, String newLastname, String newEmail, int id);		//update danych profilu
	public List<User> findAll();
}
