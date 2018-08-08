package statistic.service;

import org.springframework.data.domain.Page;

import statistic.model.BallGame;
import statistic.model.Player;

public interface PlayerService {

	boolean save(Player p);
	void remove(Long id);
	Player findOne(long id);
	Page<Player> findAll(int pageNum);
	void makePlayersInGame(BallGame bg);
}
