package statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import statistic.model.BallGame;


@Repository
public interface BallGameRepository extends JpaRepository<BallGame, Long>{

}
