package statistic.service;

import java.util.List;


import statistic.model.PlayerInGame;

public interface PlayerInGameService {
	
	void save(PlayerInGame p);
	void remove(Long id);
	PlayerInGame findOne(long id);
	List<PlayerInGame> findAll();
	
	List<PlayerInGame> findByGameId(Long id);
	List<PlayerInGame> findPlayersInGameByTeamAndGame(Long teamId, Long gameId);
	void changeStatistic(Long id, String action);

}
