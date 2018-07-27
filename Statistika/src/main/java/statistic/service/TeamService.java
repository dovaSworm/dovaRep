package statistic.service;

import java.util.List;


import statistic.model.Team;

public interface TeamService {

	void save(Team t);
	Team findOne(Long id);
	void delete(Long id);
	List<Team> findAll();
}
