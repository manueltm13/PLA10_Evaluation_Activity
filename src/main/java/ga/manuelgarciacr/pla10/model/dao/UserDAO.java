package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ga.manuelgarciacr.pla10.model.dao.IUserDAO;
import ga.manuelgarciacr.pla10.model.Authority;
import ga.manuelgarciacr.pla10.model.User;

@Repository
@Transactional
public class UserDAO implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
	}

	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User getUser(String username) {
		return sessionFactory.getCurrentSession().get(User.class, username);
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	public Boolean userEmailExists(String email) {
		return (Long) sessionFactory.getCurrentSession()
			.createQuery("select count(*) from User where userEmail='" + email + "'").uniqueResult() > 0;
	}

	@Override
	public Boolean userNameExists(String name) {
		return (Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from User where username='" + name + "'").uniqueResult() > 0;
	}

}
