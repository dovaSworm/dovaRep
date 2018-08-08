package statistic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import statistic.model.PlayerInGame;

public interface PlayerInGameRepository extends JpaRepository<PlayerInGame, Long>{

	List<PlayerInGame> findByGameId(Long gameId);
	List<PlayerInGame> findByTeamIdAndGameId(Long id, Long gameId);
	List<PlayerInGame> findByGameHostIdAndGameId(Long gameId, Long id);
	PlayerInGame findByPlayerId(Long playerId);
	List<PlayerInGame> findByGameIdAndGameId(Long id, Long gameId);
}
