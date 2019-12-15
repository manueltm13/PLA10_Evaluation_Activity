package ga.manuelgarciacr.pla10.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ga.manuelgarciacr.pla10.model.Center;
import ga.manuelgarciacr.pla10.model.dao.ICenterDAO;

@Service("centerService")
public class CenterService implements ICenterDAO {
	
	@Autowired
	private ICenterDAO centerDAO;
	
	@Override
	@Transactional
	public List<Center> getCenters() {
		return centerDAO.getCenters();
	}

	@Override
	@Transactional
	public void save(Center center) {
		centerDAO.save(center);
	}

	@Override
	@Transactional
	public Center getCenter(int cenCode) {
		return centerDAO.getCenter(cenCode);
	}

	@Override
	@Transactional
	public void delete(Center center) {
		centerDAO.delete(center);
	}

}
