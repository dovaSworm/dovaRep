package statistic.service;

import java.util.List;

import statistic.model.Pozicija;

public interface PositionService {

	void save(Pozicija p);
	void delete(Long id);
	Pozicija findOne(Long id);
	List<Pozicija> findAll();
}
