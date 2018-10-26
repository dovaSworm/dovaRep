package statistic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import statistic.model.BallGame;
import statistic.model.Player;
import statistic.model.PlayerInGame;
import statistic.model.Team;
import statistic.repository.PlayerInGameRepository;
import statistic.repository.PlayerRepository;
import statistic.repository.TeamRepositiry;
import statistic.service.PlayerService;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	TeamRepositiry teamRepository;
	@Autowired
	private PlayerInGameRepository palyerInGameRepository;
	
	@Override
	public boolean save(Player p) {

		Team t = teamRepository.findOne(p.getTeam().getId());
		if(p.getTeam().getId() == null) {
			return false;
		}
		if(t.addPlayer(p)) {
			
			playerRepository.save(p);
			teamRepository.save(t);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		playerRepository.delete(id);
	}

	@Override
	public Player findOne(long id) {
		// TODO Auto-generated method stub
		return playerRepository.findOne(id);
	}

	@Override
	public List<Player> findAll() {
		// TODO Auto-generated method stub
		return playerRepository.findAll();
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
		List<Player> hostPlayers = playerRepository.findByTeamId(bg.getHost().getId());
		List<Player> guestPlayers = playerRepository.findByTeamId(bg.getGuest().getId());
		
		for(Player p : hostPlayers) {
			PlayerInGame playerInGame = new PlayerInGame(p);
			playerInGame.setGame(bg);
			palyerInGameRepository.save(playerInGame);
		}
		for(Player p : guestPlayers) {
			PlayerInGame playerInGame = new PlayerInGame(p);
			playerInGame.setGame(bg);
			palyerInGameRepository.save(playerInGame);
		}
		
	}


}
