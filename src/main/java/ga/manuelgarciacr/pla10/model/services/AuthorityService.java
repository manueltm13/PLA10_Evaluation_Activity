package ga.manuelgarciacr.pla10.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.manuelgarciacr.pla10.model.Authority;
import ga.manuelgarciacr.pla10.model.dao.IAuthorityDAO;

@Service("authorityService")
public class AuthorityService implements IAuthorityDAO {

	@Autowired
	private IAuthorityDAO authorityDAO;
	
	@Override
	@Transactional
	public List<Authority> getAuthorities() {
		return authorityDAO.getAuthorities();
	}

	//@Override
	//@Transactional
	//public List<Authority> getAuthorities(String authority) {
	//	return authorityDAO.getAuthorities();
	//}

	@Override
	@Transactional
	public void save(Authority authority) {
//System.out.println("XXX222" + authority.getAuthority()+ authority.getUsername());
		authorityDAO.save(authority);
	}

	@Override
	@Transactional
	public void delete(Authority authority) {
		authorityDAO.delete(authority);
	}

}
