package statistic.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.PlayingPosition;
import statistic.web.dto.PositionDTO;
@Component
public class PositionToDTO implements Converter<PlayingPosition, PositionDTO>{

	@Override
	public PositionDTO convert(PlayingPosition p) {

		PositionDTO dto = new PositionDTO();
		dto.setId(p.getId());
		dto.setName(p.getName());
		
		return dto;
	}

	public List<PositionDTO> convert(List<PlayingPosition> poss){
		ArrayList<PositionDTO> dtos = new ArrayList<>();
		for(PlayingPosition p : poss) {
			dtos.add(convert(p));
		}
		return dtos;
	}
}
