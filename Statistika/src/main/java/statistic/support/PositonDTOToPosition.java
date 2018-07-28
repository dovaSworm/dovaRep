package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.PlayingPosition;
import statistic.service.PositionService;
import statistic.web.dto.PositionDTO;
@Component
public class PositonDTOToPosition implements Converter<PositionDTO, PlayingPosition>{

	@Autowired
	private PositionService posServ;
	@Override
	public PlayingPosition convert(PositionDTO dto) {
		PlayingPosition p;
		if(dto.getId() == null) {
			p = new PlayingPosition();
		}else {
			p = posServ.findOne(dto.getId());
		}
		p.setName(dto.getName());
		return p;
	}

	public List<PlayingPosition> convert(List<PositionDTO> dtos){
		List<PlayingPosition> ret = new ArrayList<>();
		for(PositionDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}
}
