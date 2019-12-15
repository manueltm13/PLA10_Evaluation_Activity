package ga.manuelgarciacr.pla10.model.dao;

import java.util.List;

import ga.manuelgarciacr.pla10.model.Center;

public interface ICenterDAO {
	
	List<Center> getCenters();
	
	void save(Center center);
	
	Center getCenter(int cenCode);
	
	void delete(Center center);
}
