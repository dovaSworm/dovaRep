package statistic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import statistic.model.BallGame;
import statistic.model.PlayerInGame;
import statistic.model.Team;
import statistic.repository.BallGameRepository;
import statistic.repository.TeamRepositiry;
import statistic.service.BallGameService;
import statistic.service.PlayerInGameService;
import statistic.service.PlayerService;

@Service
@Transactional
public class BallGameServiceImpl implements BallGameService {

	@Autowired
	private BallGameRepository ballgameRepository;
	@Autowired
	private TeamRepositiry teamRepositry;
	@Autowired
	private PlayerInGameService playerInGameService;
	@Autowired
	private PlayerService playerService;

	@Override
	public BallGame save(BallGame bg) {
		// TODO Auto-generated method stub
		playerService.makePlayersInGame(bg);
		return ballgameRepository.save(bg);
	}


	@Override
	public BallGame findOne(Long id) {
		// TODO Auto-generated method stub
		BallGame bg = ballgameRepository.findOne(id);
		if(bg.getGuestPlayers().isEmpty()) {
			bg.setGuestPlayers(playerInGameService.findPlayersInGameByTeamAndGame(bg.getGuest().getId(), id));
		}
		if(bg.getHostPlayers().isEmpty()) {
			bg.setHostPlayers(playerInGameService.findPlayersInGameByTeamAndGame(bg.getHost().getId(), id));
		}
		
		return bg;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ballgameRepository.delete(id);
	}

	@Override
	public BallGame result(Long id) {

		BallGame bg = findOne(id);
		int hostPoints = bg.getHostPlayers().stream().mapToInt(PlayerInGame::getTotalPoints).sum();
		int guestPoints = bg.getGuestPlayers().stream().mapToInt(PlayerInGame::getTotalPoints).sum();

		bg.setGuestPoints(guestPoints);
		bg.setHostPoints(hostPoints);
		
		return bg;

	}

	@Override
	public BallGame timeOut(Long gameId, Long teamId) {

		BallGame bg = ballgameRepository.findOne(gameId);
		Team t = teamRepositry.findOne(teamId);
		if (t.equals(bg.getGuest())) {
			bg.setGuestTimeOut(bg.getGuestTimeOut() - 1);
		} else {
			bg.setHostTimeOut(bg.getHostTimeOut() - 1);
		}
		return bg;
	}

	@Override
	public List<BallGame> findAll() {
		// TODO Auto-generated method stub
		return ballgameRepository.findAll();
	}
}
