package statistic.support;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSInput;

import statistic.model.BallGame;
import statistic.model.PlayerInGame;
import statistic.model.Team;
import statistic.service.BallGameService;
import statistic.service.TeamService;
import statistic.web.dto.BallGameDTO;
@Component
public class BallGameDTOToBallGame implements Converter<BallGameDTO, BallGame>{


	@Autowired
	private BallGameService bgs;
	@Autowired
	private TeamService ts;
	@Override
	public BallGame convert(BallGameDTO dto) {
		
		BallGame bg;
		Team host = ts.findOne(dto.getHostId());
		Team guest = ts.findOne(dto.getGuestId());
		
			if(dto.getId() == null) {
				bg  = new BallGame();
			}else {
				bg = bgs.findOne(dto.getId());
			}
			bg.setGuest(guest);
			bg.setHost(host);
			bg.setGuestPoints(dto.getGuestPoints());
			bg.setHostPoints(dto.getHostPoints());
			bg.setGuestTimeOut(dto.getGuestTimeOut());
			bg.setHostTimeOut(dto.getHostTimeOut());
			
		return bg;
	}
	
	public List<BallGame> convert(List<BallGameDTO> dtos){
		
		List<BallGame> ret = new ArrayList<>();
		for(BallGameDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}

}
