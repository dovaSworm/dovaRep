package statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import statistic.model.Pozicija;
@Repository
public interface PositionRepository extends JpaRepository<Pozicija, Long>{

}
