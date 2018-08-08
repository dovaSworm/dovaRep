package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.PlayerInGame;
import statistic.web.dto.PlayerInGameDTO;
@Component
public class PlayerInGameToDTO implements Converter<PlayerInGame, PlayerInGameDTO>{

	@Override
	public PlayerInGameDTO convert(PlayerInGame p) {
		
		PlayerInGameDTO dto = new PlayerInGameDTO();
		if(p == null || p.equals(null)) {
			System.out.println(" p je null");
		}
		if(p.getGame().getId() == null) {
			dto.setGameId(null);
		}else {
			dto.setGameId(p.getGame().getId());
		}
		dto.setTeamId(p.getTeam().getId());
		dto.setPlayerId(p.getPlayer().getId());
		dto.setAssist(p.getAssist());
		dto.setBlockShot(p.getBlockShot());
		dto.setReboundDef(p.getReboundDef());
		dto.setPersonalFaul(p.getPersonalFaul());
		dto.setId(p.getId());
		dto.setName(p.getPlayer().getName());
		dto.setJerseyNmber(p.getPlayer().getJerseyNumber());
		dto.setReboundOff(p.getReboundOff());
		dto.setOnePointScore(p.getOnePointScore());
		dto.setOnePointShot(p.getOnePointShot());
		dto.setFouledOut(p.isFouledOut());
		dto.setSteal(p.getSteal());
		dto.setThreePointScore(p.getThreePointScore());
		dto.setThreePointShot(p.getThreePointShot());
		dto.setTurnOver(p.getTurnOver());
		dto.setTwoPointScore(p.getTwoPointScore());
		dto.setTwoPointShot(p.getTwoPointShot());
		dto.setTotalPoints(p.getTotalPoints());
		dto.setTotalRebounds(p.getTotalRebounds());
		dto.setPositionName(p.getPlayer().getPlayingPosition().getName());
		dto.setOnRoster(p.isOnRoster());
		return dto;
	}

	public List<PlayerInGameDTO> convert(List<PlayerInGame> players){
		
		ArrayList<PlayerInGameDTO> dtos = new ArrayList<>();
		for(PlayerInGame p : players) {
			dtos.add(convert(p));
		}
		return dtos;
	}
	}


