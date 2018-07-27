package statistic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import statistic.model.Team;
import statistic.web.dto.TeamDTO;
@Component
public class TeamToDTO implements Converter<Team, TeamDTO>{

	@Override
	public TeamDTO convert(Team t) {
		TeamDTO dto = new TeamDTO();
		dto.setCity(t.getCity());
		dto.setCoach(t.getCoach());
		dto.setCountry(t.getCountry());
		dto.setId(t.getId());
		dto.setName(t.getName());
		return dto;
	}

	public List<TeamDTO> convert(List<Team> teams){
		List<TeamDTO> ret = new ArrayList<>();
		for(Team t : teams) {
			ret.add(convert(t));
		}
		return ret;
	}
}
