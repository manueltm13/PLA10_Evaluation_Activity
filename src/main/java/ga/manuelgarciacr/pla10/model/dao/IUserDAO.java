package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import ga.manuelgarciacr.pla10.model.User;

public interface IUserDAO {
	List<User> getUsers();
	
	void save(User user);
	
	User getUser(String username);
	
	void delete(User user);
	
	Boolean userEmailExists(String email);
	
	Boolean userEmailExists(String email, String excludedUsername);
	
	Boolean userNameExists(String name);
	
}
