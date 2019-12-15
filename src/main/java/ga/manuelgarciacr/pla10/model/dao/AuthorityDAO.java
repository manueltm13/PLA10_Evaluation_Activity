package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ga.manuelgarciacr.pla10.model.Authority;

@Repository
public class AuthorityDAO implements IAuthorityDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Authority> getAuthorities() {
		return sessionFactory.getCurrentSession().createQuery("from Authority", Authority.class).list();
	}

	@Override
	public void save(Authority authority) {
		sessionFactory.getCurrentSession().saveOrUpdate(authority);
	}

	@Override
	public Authority getAuthority(String authority) {
		return sessionFactory.getCurrentSession().get(Authority.class, authority);
	}

	@Override
	public void delete(Authority authority) {
		sessionFactory.getCurrentSession().delete(authority);
	}

}
