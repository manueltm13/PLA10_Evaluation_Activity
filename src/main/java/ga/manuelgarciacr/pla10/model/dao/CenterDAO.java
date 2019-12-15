package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ga.manuelgarciacr.pla10.model.Center;

@Repository
public class CenterDAO implements ICenterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Center> getCenters() {
		return sessionFactory.getCurrentSession().createQuery("from Center", Center.class).list();
	}

	@Override
	public void save(Center center) {
		sessionFactory.getCurrentSession().saveOrUpdate(center);
	}

	@Override
	public Center getCenter(int cenCode) {
		return sessionFactory.getCurrentSession().get(Center.class, cenCode);
	}

	@Override
	public void delete(Center center) {
		sessionFactory.getCurrentSession().delete(center);
	}

}
