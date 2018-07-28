package statistic.service;

import java.util.List;

import statistic.model.PlayingPosition;

public interface PositionService {

	void save(PlayingPosition p);
	void delete(Long id);
	PlayingPosition findOne(Long id);
	List<PlayingPosition> findAll();
}
