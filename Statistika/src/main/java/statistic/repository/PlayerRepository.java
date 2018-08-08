package statistic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import statistic.model.Player;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

	Page<Player> findByTeamId(Long teamId,  Pageable pageRequest);
	List<Player> findByTeamId(Long id);
}
