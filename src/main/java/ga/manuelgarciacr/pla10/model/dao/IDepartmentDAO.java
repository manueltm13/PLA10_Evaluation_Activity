package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import ga.manuelgarciacr.pla10.model.Department;

public interface IDepartmentDAO {

	List<Department> getDepartments();
	
	void save(Department department);
	
	Department getDepartment(int depCode);
	
	void delete(Department department);
}
