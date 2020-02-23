package wiktoria.appdemo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")												//adnotacja
public interface UserRepository extends JpaRepository<User, Integer> {		//silnik sprawdzi admotacje w user, jakiej tablicy dot, jakie kolumny, Integer dotyczy klucza gl - userID

	public User findByEmail(String email);		//metoda dostarczona przez interfejs
												//do sprawdzenia czy dany email jest juz w bazie zarejestrowany, na pdst tego będzie zapytanie typu "select * from user where email = ?"
	
	@Modifying
	@Query("UPDATE User u SET u.password = :newPassword WHERE u.email= :email")								//w query jest update, który przyjmuje parametry (named query bo mają nazwe)
	public void updateUserPassword(@Param("newPassword") String password, @Param("email") String email);	//będzie wykonane przy wywołaniu akcji zmiany hasła - bo nie modyfikujemy całego obiektu, jedynie jedno pole używamy więc query


	@Modifying																																//odebrane z ormularza dane lądują w  query, id decyduje które dane będą zmieniane - nie email bo mimo, że nas uwerzytelnia to można go zmienić, a id nie
	@Query("UPDATE User u SET u.name = :newName, u.lastname = :newLastName, u.email = :newEmail WHERE u.id = :id")							//: bo hibernate wie, że to parmetr						
	public void updateUserProfile(@Param("newName") String newName, @Param("newLastName") String newLastName, @Param("newEmail") String newEmail, @Param("id") Integer id);

}

//repo do obsługi użytkownika