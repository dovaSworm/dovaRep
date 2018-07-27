package statistic.service;

import org.springframework.data.domain.Page;

import statistic.model.Player;

public interface PlayerService {

	boolean save(Player p);
	void remove(Long id);
	Player findOne(long id);
	Page<Player> findAll(int pageNum);
	
	void addOneShot(Long id);
	void addOneScore(Long id);
	void addTwoShot(Long id);
	void addTwoScore(Long id);
	void addThreeShot(Long id);
	void addThreeScore(Long id);
	void addSteal(Long id);
	void addTO(Long id);
	void addBlock(Long id);
	void addOffRebound(Long id);
	void addDefRebound(Long id);
	void addAssist(Long id);
	void addFaul(Long id);
	Page<Player> findByTeamId(int pageNum, Long teamId);
}
