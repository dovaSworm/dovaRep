package statistic.service;

import java.util.List;

import statistic.model.BallGame;

public interface BallGameService {

	BallGame save(BallGame bg);
	BallGame findOne(Long id);
	void delete(Long id);
	
	BallGame result(Long id);
	BallGame timeOut(Long gameId, Long teamId);
	List<BallGame> findAll();
}
