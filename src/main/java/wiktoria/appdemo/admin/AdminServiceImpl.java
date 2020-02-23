package wiktoria.appdemo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import wiktoria.appdemo.user.User;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	
	@Override
	public User findUserById(int id) {
		User user = adminRepository.findUserById(id);
		return user;
	}

	@Override
	public void deleteUserById(int id) {
		adminRepository.deleteUserFromUserrole(id);
		adminRepository.deleteUserFromUser(id);
	}

	
}
