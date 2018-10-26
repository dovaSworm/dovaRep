package statistic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import statistic.model.BallGame;
import statistic.model.PlayerInGame;
import statistic.repository.BallGameRepository;
import statistic.repository.PlayerInGameRepository;
import statistic.service.PlayerInGameService;

@Service
@Transactional
public class PlayerInGameServiceImpl implements PlayerInGameService {

	@Autowired
	private BallGameRepository bgR;
	@Autowired
	private PlayerInGameRepository inGameR;

	@Override
	public void save(PlayerInGame p) {
		// TODO Auto-generated method stub
		inGameR.save(p);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		inGameR.delete(id);
	}

	@Override
	public PlayerInGame findOne(long id) {
		// TODO Auto-generated method stub
		return inGameR.findOne(id);
	}

	@Override
	public List<PlayerInGame> findAll() {
		// TODO Auto-generated method stub
		return inGameR.findAll();
	}

	@Override
	public void changeStatistic(Long id, String action) {
		PlayerInGame p = inGameR.findOne(id);
		if (action.equals("onePointShot")) {
			p.setOnePointShot((p.getOnePointShot() + 1));
			inGameR.save(p);
		} else if (action.equals("onePointScore")) {
			p.setOnePointScore((p.getOnePointScore() + 1));
			p.setOnePointShot((p.getOnePointShot() + 1));
			p.setTotalPoints(p.getTotalPoints() + 1);
			inGameR.save(p);
		} else if (action.equals("twoPointShot")) {
			p.setTwoPointShot((p.getTwoPointShot() + 1));
			inGameR.save(p);
		} else if (action.equals("twoPointScore")) {
			p.setTwoPointScore((p.getTwoPointScore() + 1));
			p.setTwoPointShot((p.getTwoPointShot() + 1));
			p.setTotalPoints(p.getTotalPoints() + 2);
			inGameR.save(p);
		} else if (action.equals("threePointShot")) {
			p.setThreePointShot((p.getThreePointShot() + 1));
			inGameR.save(p);
		} else if (action.equals("threePointScore")) {
			p.setThreePointShot((p.getThreePointShot() + 1));
			p.setThreePointScore((p.getThreePointScore() + 1));
			p.setTotalPoints(p.getTotalPoints() + 3);
			inGameR.save(p);
		} else if (action.equals("steal")) {
			p.setSteal((p.getSteal() + 1));
			inGameR.save(p);
		} else if (action.equals("to")) {
			p.setTurnOver((p.getTurnOver() + 1));
			inGameR.save(p);
		} else if (action.equals("off")) {
			p.setReboundOff((p.getReboundOff() + 1));
			p.setTotalRebounds(p.getTotalRebounds() + 1);
			inGameR.save(p);
		} else if (action.equals("def")) {
			p.setReboundDef((p.getReboundDef() + 1));
			p.setTotalRebounds(p.getTotalRebounds() + 1);
			inGameR.save(p);
		} else if (action.equals("block")) {
			p.setBlockShot((p.getBlockShot() + 1));
			inGameR.save(p);
		} else if (action.equals("assist")) {
			p.setAssist((p.getAssist() + 1));
			inGameR.save(p);
		} else if (action.equals("faul")) {
			p.setPersonalFaul((short) (p.getPersonalFaul() + 1));
			if (p.getPersonalFaul() == 5) {
				p.setFouledOut(true);
			}
			inGameR.save(p);
		}

	}

	@Override
	public List<PlayerInGame> findByGameId(Long gameId) {
		// TODO Auto-generated method stub
		return inGameR.findByGameId(gameId);
	}

	@Override
	public List<PlayerInGame> findPlayersInGameByTeamAndGame(Long teamId, Long gameId) {
		// TODO Auto-generated method stub
		List<PlayerInGame> players = new ArrayList<>();
		List<PlayerInGame> returnList;
		BallGame bg = bgR.findOne(gameId);
		if (bg.getHost().getId() == teamId) {
			players = inGameR.findByTeamIdAndGameId(teamId, gameId);
//			System.out.println(players.size() + "players by team id");
			returnList = players.stream().filter(player -> teamId.equals(player.getPlayer().getTeam().getId()))
					.collect(Collectors.toList());
//			System.out.println(returnList.size() + "host by team id");
		} else if (teamId == bg.getGuest().getId()) {
			players = inGameR.findByTeamIdAndGameId(teamId, gameId);
			returnList = players.stream().filter(player -> teamId.equals(player.getPlayer().getTeam().getId()))
					.collect(Collectors.toList());
//			System.out.println(returnList.size() + "guest by team id");
		} else {
			return null;
		}
		return returnList;
	}

}
