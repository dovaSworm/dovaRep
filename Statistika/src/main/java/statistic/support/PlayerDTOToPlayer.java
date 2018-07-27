package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Player;
import statistic.model.Pozicija;
import statistic.model.Team;
import statistic.service.PlayerService;
import statistic.service.PositionService;
import statistic.service.TeamService;
import statistic.web.dto.PlayerDTO;

@Component
public class PlayerDTOToPlayer implements Converter<PlayerDTO, Player>{

	@Autowired
	private PlayerService plSe;
	@Autowired
	private TeamService tSer;
	@Autowired
	private PositionService posSer;
	@Override
	public Player convert(PlayerDTO dto) {
		Player p;
		Pozicija pos = posSer.findOne(dto.getPositionId());
		Team t = tSer.findOne(dto.getTeamId());
		if(dto.getId() == null) {
			p = new Player();
		}else {
			p = plSe.findOne(dto.getId());
		}
		p.setTeam(t);
		p.setPosition(pos);
		p.setAssists(dto.getAssists());
		p.setBlock(dto.getBlock());
		p.setFaul(dto.getFaul());
		p.setName(dto.getName());
		p.setNumber(dto.getNumber());
		p.setOffRebound(dto.getOffRebound());
		p.setDefRebound(dto.getDefRebound());
		p.setOnePointScore(dto.getOnePointScore());
		p.setOnePointShoot(dto.getOnePointShoot());
		p.setSteal(dto.getSteal());
		p.setThreePointScore(dto.getThreePointScore());
		p.setThreePointShoot(dto.getThreePointShoot());
		p.setTurnOver(dto.getTurnOver());
		p.setTwoPointScore(dto.getTwoPointScore());
		p.setTwoPointShoot(dto.getTwoPointShoot());
		p.setOut(dto.isOut());
		p.setPoeniTotal(dto.getPoeniTotal());
		p.setSkokTotal(dto.getSkokTotal());
		return p;
	}
	
	public List<Player> convert(List<PlayerDTO> dtos){
		List<Player> ret = new ArrayList<>();
		for(PlayerDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}

}
