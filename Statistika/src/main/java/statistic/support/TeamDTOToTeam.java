package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Team;
import statistic.service.TeamService;
import statistic.web.dto.TeamDTO;
@Component
public class TeamDTOToTeam implements Converter<TeamDTO, Team>{

	@Autowired
	private TeamService tSer;
	@Override
	public Team convert(TeamDTO dto) {
		Team t;
		if(dto.getId() == null) {
			t = new Team();
		}else {
			t = tSer.findOne(dto.getId());
		}
		t.setCity(dto.getCity());
		t.setCoach(dto.getCoach());
		t.setCountry(dto.getCountry());
		t.setName(dto.getName());
		return t;
	}

	public List<Team> convert(List<TeamDTO> dtos){
		List<Team> ret =  new ArrayList<>();
		for(TeamDTO dto : dtos) {
			ret.add(convert(dto));
		}
		return ret;
	}
}
