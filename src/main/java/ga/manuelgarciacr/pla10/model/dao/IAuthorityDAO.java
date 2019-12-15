package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import ga.manuelgarciacr.pla10.model.Authority;

public interface IAuthorityDAO {
	
	List<Authority> getAuthorities();
	
	void save(Authority authority);
	
	Authority getAuthority(String authority);
	
	void delete(Authority authority);
}
