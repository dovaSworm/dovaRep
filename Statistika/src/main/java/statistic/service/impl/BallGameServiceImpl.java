package statistic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import statistic.model.BallGame;
import statistic.model.PlayerInGame;
import statistic.model.Team;
import statistic.repository.BallGameRepository;
import statistic.repository.PlayerInGameRepository;
import statistic.repository.TeamRepositiry;
import statistic.service.BallGameService;
import statistic.service.PlayerInGameService;
import statistic.service.PlayerService;

@Service
@Transactional
public class BallGameServiceImpl implements BallGameService {

	@Autowired
	private BallGameRepository bgr;
	@Autowired
	private TeamRepositiry tr;
	@Autowired
	private PlayerInGameService inGamS;
	@Autowired
	private PlayerService playerS;

	@Override
	public BallGame save(BallGame bg) {
		// TODO Auto-generated method stub
		playerS.makePlayersInGame(bg);
		return bgr.save(bg);
	}


	@Override
	public BallGame findOne(Long id) {
		// TODO Auto-generated method stub
		BallGame bg = bgr.findOne(id);
		if(bg.getGuestPlayers().isEmpty()) {
			bg.setGuestPlayers(inGamS.findPlayersInGameByTeamAndGame(bg.getGuest().getId(), id));
		}
		if(bg.getHostPlayers().isEmpty()) {
			bg.setHostPlayers(inGamS.findPlayersInGameByTeamAndGame(bg.getHost().getId(), id));
			System.out.println(bg.getHostPlayers().size() + "u find one");
		}
		
		return bg;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		bgr.delete(id);
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

		BallGame bg = bgr.findOne(gameId);
		Team t = tr.findOne(teamId);
		if (t.equals(bg.getGuest())) {
			bg.setGuestTimeOut(bg.getGuestTimeOut() - 1);
		} else {
			bg.setGuestTimeOut(bg.getHostTimeOut() - 1);
		}
		return bg;
	}

	@Override
	public List<BallGame> findAll() {
		// TODO Auto-generated method stub
		return bgr.findAll();
	}
}
