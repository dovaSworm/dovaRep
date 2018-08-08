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
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setJerseyNmber(p.getJerseyNumber());
		dto.setPositionId(p.getPlayingPosition().getId());
		dto.setTeamId(p.getTeam().getId());
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
