package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ga.manuelgarciacr.pla10.model.Department;

@Repository
public class DepartmentDAO implements IDepartmentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Department> getDepartments() {
		return sessionFactory.getCurrentSession().createQuery("from Department", Department.class).list();
	}

	@Override
	public void save(Department department) {
		sessionFactory.getCurrentSession().saveOrUpdate(department);
	}

	@Override
	public Department getDepartment(int depCode) {
		return sessionFactory.getCurrentSession().get(Department.class, depCode);
	}

	@Override
	public void delete(Department department) {
		sessionFactory.getCurrentSession().delete(department);
	}

}
