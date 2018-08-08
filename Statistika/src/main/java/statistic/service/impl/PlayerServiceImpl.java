package statistic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import statistic.model.BallGame;
import statistic.model.Player;
import statistic.model.PlayerInGame;
import statistic.model.Team;
import statistic.repository.BallGameRepository;
import statistic.repository.PlayerInGameRepository;
import statistic.repository.PlayerRepository;
import statistic.repository.TeamRepositiry;
import statistic.service.PlayerService;
import statistic.web.dto.PlayerInGameDTO;
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	PlayerRepository plRe;
	@Autowired
	TeamRepositiry tr;
	@Autowired
	private PlayerInGameRepository inGameR;
	@Autowired
	private BallGameRepository bgR;
	@Override
	public boolean save(Player p) {

		Team t = tr.findOne(p.getTeam().getId());
		if(p.getTeam().getId() == null) {
			return false;
		}
		if(t.addPlayer(p)) {
			
			plRe.save(p);
			tr.save(t);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		plRe.delete(id);
	}

	@Override
	public Player findOne(long id) {
		// TODO Auto-generated method stub
		return plRe.findOne(id);
	}

	@Override
	public Page<Player> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return plRe.findAll(
				new PageRequest(pageNum, 5));
	}

//	@Override
//	public List<PlayerInGame> findByTeamId(Long teamId) {
//		// TODO Auto-generated method stub
//		List<Player> players = plRe.findByTeamId(teamId);
//		List<PlayerInGame> inGamePlayers= new ArrayList<>();
//		for(Player p : players) {
//			PlayerInGame playerInGame;
//			if(inGameR.findByPlayerId(p.getId()) == null) {
//				playerInGame = new PlayerInGame(p);
//				inGameR.save(playerInGame);
//				inGamePlayers.add(playerInGame);
//			}else {
//				playerInGame = inGameR.findByPlayerId(p.getId());
//				inGamePlayers.add(playerInGame);
//			}
//			
//		}
//		return inGamePlayers;
//	}

	@Override
	public void makePlayersInGame(BallGame bg) {
		List<Player> hostPlayers = plRe.findByTeamId(bg.getHost().getId());
		List<Player> guestPlayers = plRe.findByTeamId(bg.getGuest().getId());
//		BallGame bg = bgR.findOne(gameId);
//		List<PlayerInGame> inGamePlayers= new ArrayList<>();
		for(Player p : hostPlayers) {
			PlayerInGame playerInGame = new PlayerInGame(p);
			playerInGame.setGame(bg);
			inGameR.save(playerInGame);
//			inGamePlayers.add(playerInGame);
		}
		for(Player p : guestPlayers) {
			PlayerInGame playerInGame = new PlayerInGame(p);
			playerInGame.setGame(bg);
			inGameR.save(playerInGame);
//			inGamePlayers.add(playerInGame);
		}
		
//		return inGamePlayers;
	}


}
