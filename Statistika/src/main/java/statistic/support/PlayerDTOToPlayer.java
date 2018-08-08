package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Player;
import statistic.model.PlayingPosition;
import statistic.model.Team;
import statistic.service.PlayerService;
import statistic.service.PositionService;
import statistic.service.TeamService;
import statistic.web.dto.PlayerDTO;

@Component
public class PlayerDTOToPlayer implements Converter<PlayerDTO, Player> {

	@Autowired
	private PlayerService plSe;
	@Autowired
	private TeamService tSer;
	@Autowired
	private PositionService posSer;

	@Override
	public Player convert(PlayerDTO dto) {

		Player p;
		PlayingPosition pos = posSer.findOne(dto.getPositionId());
		Team t = tSer.findOne(dto.getTeamId());
		if (dto.getId() == null) {
			p = new Player();
		} else {
			p = plSe.findOne(dto.getId());
		}
		p.setTeam(t);
		p.setPlayingPosition(pos);
		p.setName(dto.getName());
		p.setJerseyNumber(dto.getJerseyNmber());
		return p;
	}

	public List<Player> convert(List<PlayerDTO> dtos) {
		List<Player> ret = new ArrayList<>();
		for (PlayerDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}

}
