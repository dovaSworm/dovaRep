package statistic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import statistic.model.PlayingPosition;
import statistic.repository.PositionRepository;
import statistic.service.PositionService;
@Service
@Transactional
public class PositionServiceImpl implements PositionService{

	@Autowired
	public PositionRepository pRep;
	@Override
	public void save(PlayingPosition p) {
		// TODO Auto-generated method stub
		pRep.save(p);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pRep.delete(id);
	}

	@Override
	public PlayingPosition findOne(Long id) {
		// TODO Auto-generated method stub
		return pRep.findOne(id);
	}

	@Override
	public List<PlayingPosition> findAll() {
		// TODO Auto-generated method stub
		return pRep.findAll();
	}

	

}
