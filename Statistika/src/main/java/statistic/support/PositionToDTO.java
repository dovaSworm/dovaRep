package statistic.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Pozicija;
import statistic.web.dto.PositionDTO;
@Component
public class PositionToDTO implements Converter<Pozicija, PositionDTO>{

	@Override
	public PositionDTO convert(Pozicija p) {

		PositionDTO dto = new PositionDTO();
		dto.setId(p.getId());
		dto.setName(p.getName());
		
		return dto;
	}

	public List<PositionDTO> convert(List<Pozicija> poss){
		ArrayList<PositionDTO> dtos = new ArrayList<>();
		for(Pozicija p : poss) {
			dtos.add(convert(p));
		}
		return dtos;
	}
}
