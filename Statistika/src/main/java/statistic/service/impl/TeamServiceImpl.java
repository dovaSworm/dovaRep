package statistic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import statistic.model.Team;
import statistic.repository.TeamRepositiry;
import statistic.service.TeamService;
@Service
@Transactional
public class TeamServiceImpl implements TeamService{

	@Autowired
	public TeamRepositiry tRep;
	
	@Override
	public void save(Team t) {
		// TODO Auto-generated method stub
		
		tRep.save(t);
	}

	@Override
	public Team findOne(Long id) {
		// TODO Auto-generated method stub
		return tRep.findOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tRep.delete(id);
	}

	@Override
	public List<Team> findAll() {
		// TODO Auto-generated method stub
		return tRep.findAll();
	}

}
