package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ga.manuelgarciacr.pla10.model.Authority;

@Repository
@Transactional
public class AuthorityDAO implements IAuthorityDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Authority> getAuthorities() {
		return sessionFactory.getCurrentSession().createQuery("from Authority order by authority", Authority.class).list();
	}

	//@Override
	//public List<Authority> getAuthorities(String authority) {
	//	return sessionFactory.getCurrentSession().createQuery("from Authority where authority = '" + authority + "'", Authority.class).list();
	//}

	@Override
	public void save(Authority authority) {
		Session session = sessionFactory.getCurrentSession();
		try {
//System.out.println("XXX" + authority.getAuthority()+ authority.getUsername());
    		session.save(authority);
            //authority.getUser().getAuthorities().add(authority);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void delete(Authority authority) {
		Session session = sessionFactory.getCurrentSession();
		try {
    		session.delete(authority);
            //authority.getUser().getAuthorities().remove(authority);
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
	}

}
