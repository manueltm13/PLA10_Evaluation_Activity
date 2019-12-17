package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import ga.manuelgarciacr.pla10.model.Authority;

public interface IAuthorityDAO {
	
	List<Authority> getAuthorities();
	
	//List<Authority> getAuthorities(String authority);

	void save(Authority authority);
	
	void delete(Authority authority);
}
