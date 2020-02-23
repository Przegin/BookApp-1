package wiktoria.appdemo.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wiktoria.appdemo.user.User;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<User, Integer>  {
	
	User findUserById(int id);
	
	@Modifying																					//nie nadpisywanie tylko niszczenie danych
	@Query(value = "DELETE FROM userrole WHERE userid = :id", nativeQuery = true)				//najpierw usuwamy relacje miedzy rolą a userem, nativequery bo ma być od razu do hibernata bez tlumaczenia itd
	void deleteUserFromUserrole(@Param("id") int id);
	
	@Modifying																					
	@Query(value = "DELETE FROM user WHERE userid = :id", nativeQuery = true)					//usuwanie juz z tablicy
	void deleteUserFromUser(@Param("id") int id);
	

}
