package wiktoria.appdemo.admin;

import wiktoria.appdemo.user.User;

public interface AdminService {
	
	User findUserById(int id);
	
	void deleteUserById(int id);
	

}
