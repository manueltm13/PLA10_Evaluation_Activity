package ga.manuelgarciacr.pla10.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.manuelgarciacr.pla10.model.Department;
import ga.manuelgarciacr.pla10.model.dao.IDepartmentDAO;

@Service("departmentService")
public class DepartmentService implements IDepartmentDAO {

	@Autowired
	private IDepartmentDAO departmentDAO;
	
	@Override
	@Transactional
	public List<Department> getDepartments() {
		return departmentDAO.getDepartments();
	}

	@Override
	@Transactional
	public void save(Department department) {
		departmentDAO.save(department);
	}

	@Override
	@Transactional
	public Department getDepartment(int depCode) {
		return departmentDAO.getDepartment(depCode);
	}

	@Override
	@Transactional
	public void delete(Department department) {
		departmentDAO.delete(department);
	}

}
