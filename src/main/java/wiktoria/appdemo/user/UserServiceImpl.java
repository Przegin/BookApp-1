package wiktoria.appdemo.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")										//adn service - serwis, usługa dost do bazy, pow z konfiguracją do bazy w app.properties
@Transactional												//wł transakcje przede wszystkim o zapis danych, np 2 zapisy - jeśli pierwszy się nie powiedzie, drugi nie zostanie uruchomiony, odwrotnie - wykona rollback
public class UserServiceImpl implements UserService {		//warstwa zapewniająca dost do danych w bazie

	@Autowired												//tworzy bin i wstrzykuje gdzie jest potrzebny
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);			//wywołanie metody z UserRepo, hibernate wtedy generuje zapytanie z warunkiem 'email' - jesli taki user istnieje, wypełni obiekt user z danymi i zwróci obiekt do kontrolera, jesli nie istnieje zwróci null
	}														//odnajdujemy użytkownika w bazie na pdst adresu email, zwracamy obiekt user - zwierającym coś lub null

	@Override
	public void saveUser(User user) {											//bcrypt - haszowanie hasła
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		//odbieramy aktualne hasło, ktore user wpisał, przy pomocy encode przekazujemy do szyfrowania i ustawiamy jako nową wartość obiektu user
		user.setActive(1);														//ust wartości czy użytkownik jest aktywny - może się logować do systemu
		
		Role role = roleRepository.findByRole("ROLE_USER");					//odczyt id roli, zwraca z zapytania hibernate id roli
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));					//odbieramy id roli, ustawiamy wartość tej roli
		
		userRepository.save(user);												//metoda wbudowana do zapisu użytkownika w bazie, wg typu tabel i kolumn w user, odebranie i przekazanie do bazy danych
	}

	@Override
	public void updateUserPassword(String newPassword, String email) {
		userRepository.updateUserPassword(bCryptPasswordEncoder.encode(newPassword), email);		//wywołujemy z repo, haszujemy nowe hasło
	}
	
	@Override
	public void updateUserProfile(String newName, String newLastname, String newEmail, int id) {
		userRepository.updateUserProfile(newName, newLastname, newEmail, id);
	}

	@Override
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		return userList;
	}
	
}
