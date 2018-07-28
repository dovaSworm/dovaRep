package statistic.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Player;
import statistic.web.dto.PlayerDTO;
@Component
public class PlayerToDTO implements Converter<Player, PlayerDTO>{

	@Override
	public PlayerDTO convert(Player p) {
		
		PlayerDTO dto = new PlayerDTO();
		dto.setAssist(p.getAssist());
		dto.setBlockShot(p.getBlockShot());
		dto.setReboundDef(p.getReboundDef());
		dto.setPersonalFaul(p.getPersonalFaul());
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setJerseyNmber(p.getJerseyNumber());
		dto.setReboundOff(p.getReboundOff());
		dto.setOnePointScore(p.getOnePointScore());
		dto.setOnePointShot(p.getOnePointShot());
		dto.setFouledOut(p.isFouledOut());
		dto.setPositionId(p.getPlayingPosition().getId());
		dto.setSteal(p.getSteal());
		dto.setTeamId(p.getTeam().getId());
		dto.setThreePointScore(p.getThreePointScore());
		dto.setThreePointShot(p.getThreePointShot());
		dto.setTurnOver(p.getTurnOver());
		dto.setTwoPointScore(p.getTwoPointScore());
		dto.setTwoPointShot(p.getTwoPointShot());
		dto.setTotalPoints(p.getTotalPoints());
		dto.setTotalRebounds(p.getTotalRebounds());
		dto.setPositionName(p.getPlayingPosition().getName());
		dto.setTeamName(p.getTeam().getName());
		
		return dto;
	}

	public List<PlayerDTO> convert(List<Player> players){
		
		ArrayList<PlayerDTO> dtos = new ArrayList<>();
		for(Player p : players) {
			dtos.add(convert(p));
		}
		return dtos;
	}
}
