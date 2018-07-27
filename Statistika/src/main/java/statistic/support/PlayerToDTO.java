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
		dto.setAssists(p.getAssists());
		dto.setBlock(p.getBlock());
		dto.setDefRebound(p.getDefRebound());
		dto.setFaul(p.getFaul());
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setNumber(p.getNumber());
		dto.setOffRebound(p.getOffRebound());
		dto.setOnePointScore(p.getOnePointScore());
		dto.setOnePointShoot(p.getOnePointShoot());
		dto.setOut(p.isOut());
		dto.setPositionId(p.getPosition().getId());
		dto.setSteal(p.getSteal());
		dto.setTeamId(p.getTeam().getId());
		dto.setThreePointScore(p.getThreePointScore());
		dto.setThreePointShoot(p.getThreePointShoot());
		dto.setTurnOver(p.getTurnOver());
		dto.setTwoPointScore(p.getTwoPointScore());
		dto.setTwoPointShoot(p.getTwoPointShoot());
		dto.setPoeniTotal(p.getPoeniTotal());
		dto.setSkokTotal(p.getSkokTotal());
		
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
