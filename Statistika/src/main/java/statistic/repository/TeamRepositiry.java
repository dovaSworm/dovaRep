package statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import statistic.model.Team;
@Repository
public interface TeamRepositiry extends JpaRepository<Team, Long>{

}
