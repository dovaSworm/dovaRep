package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.BallGame;
import statistic.web.dto.BallGameDTO;
@Component
public class BallGameToDTO implements Converter<BallGame, BallGameDTO> {


	@Override
	public BallGameDTO convert(BallGame bg) {

		BallGameDTO dto = new BallGameDTO();
		dto.setId(bg.getId());
		dto.setGuestId(bg.getGuest().getId());
		dto.setHostId(bg.getHost().getId());
		dto.setGuestPoints(bg.getGuestPoints());
		dto.setHostPoints(bg.getHostPoints());
		dto.setHostTimeOut(bg.getHostTimeOut());
		dto.setGuestTimeOut(bg.getGuestTimeOut());
		dto.setGuestName(bg.getGuest().getName());
		dto.setHostName(bg.getHost().getName());
		return dto;
	}
	
	public List<BallGameDTO> convert(List<BallGame> bgs){
		
		List<BallGameDTO> ret = new ArrayList<>();
		for(BallGame bg : bgs) {
			ret.add(convert(bg));
		}
		return ret;
	}

}
