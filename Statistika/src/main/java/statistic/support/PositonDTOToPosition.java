package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Pozicija;
import statistic.service.PositionService;
import statistic.web.dto.PositionDTO;
@Component
public class PositonDTOToPosition implements Converter<PositionDTO, Pozicija>{

	@Autowired
	private PositionService posServ;
	@Override
	public Pozicija convert(PositionDTO dto) {
		Pozicija p;
		if(dto.getId() == null) {
			p = new Pozicija();
		}else {
			p = posServ.findOne(dto.getId());
		}
		p.setName(dto.getName());
		return p;
	}

	public List<Pozicija> convert(List<PositionDTO> dtos){
		List<Pozicija> ret = new ArrayList<>();
		for(PositionDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}
}
