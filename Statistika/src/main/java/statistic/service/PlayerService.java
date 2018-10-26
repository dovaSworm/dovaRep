package statistic.service;

import java.util.List;

import statistic.model.BallGame;
import statistic.model.Player;

public interface PlayerService {

	boolean save(Player p);
	void remove(Long id);
	Player findOne(long id);
	List<Player> findAll();
	void makePlayersInGame(BallGame bg);
}
