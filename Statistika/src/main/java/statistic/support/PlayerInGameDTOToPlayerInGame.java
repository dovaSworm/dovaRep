package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.BallGame;
import statistic.model.Player;
import statistic.model.PlayerInGame;
import statistic.model.Team;
import statistic.service.BallGameService;
import statistic.service.PlayerInGameService;
import statistic.service.PlayerService;
import statistic.service.TeamService;
import statistic.web.dto.PlayerInGameDTO;

@Component
public class PlayerInGameDTOToPlayerInGame implements Converter<PlayerInGameDTO, PlayerInGame>{

	@Autowired
	private PlayerInGameService inGameS;
	@Autowired
	private PlayerService plSe;
	@Autowired
	private BallGameService bgS;
	@Autowired
	private TeamService teamS;
	@Override
	public PlayerInGame convert(PlayerInGameDTO dto) {
		PlayerInGame p;
		Player player = plSe.findOne(dto.getPlayerId()) ;
		BallGame bg = bgS.findOne(dto.getGameId());
		Team t = teamS.findOne(dto.getTeamId());
		if(dto.getId() == null) {
			p = new PlayerInGame();
		}else {
			p = inGameS.findOne(dto.getId());
		}
		p.setTeam(t);
		p.setOnRoster(dto.isOnRoster());
		p.setGame(bg);
		p.setPlayer(player);
		p.setAssist(dto.getAssist());
		p.setBlockShot(dto.getBlockShot());
		p.setPersonalFaul(dto.getPersonalFaul());
		p.setReboundOff(dto.getReboundOff());
		p.setReboundDef(dto.getReboundDef());
		p.setOnePointScore(dto.getOnePointScore());
		p.setOnePointShot(dto.getOnePointShot());
		p.setSteal(dto.getSteal());
		p.setThreePointScore(dto.getThreePointScore());
		p.setThreePointShot(dto.getThreePointShot());
		p.setTurnOver(dto.getTurnOver());
		p.setTwoPointScore(dto.getTwoPointScore());
		p.setTwoPointShot(dto.getTwoPointShot());
		p.setFouledOut(dto.isFouledOut());
		p.setTotalPoints(dto.getTotalPoints());
		p.setTotalRebounds(dto.getTotalRebounds());
		return p;
	}
	
	public List<PlayerInGame> convert(List<PlayerInGameDTO> dtos){
		List<PlayerInGame> ret = new ArrayList<>();
		for(PlayerInGameDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}

}
