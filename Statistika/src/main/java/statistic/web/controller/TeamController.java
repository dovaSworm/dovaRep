package statistic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import statistic.model.Player;
import statistic.model.Team;
import statistic.repository.PlayerRepository;
import statistic.service.TeamService;
import statistic.support.PlayerToDTO;
import statistic.support.TeamDTOToTeam;
import statistic.support.TeamToDTO;
import statistic.web.dto.PlayerDTO;
import statistic.web.dto.TeamDTO;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamToDTO toDTO;
	@Autowired
	private TeamDTOToTeam toTeam;
	@Autowired
	private PlayerToDTO toPlayerDTO;
	@Autowired
	private PlayerRepository playerRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TeamDTO>> get(){
		return new ResponseEntity<>(toDTO.convert(teamService.findAll()),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TeamDTO> get(
			@PathVariable Long id){
		
		Team t = teamService.findOne(id);
		
		if(t == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(t),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<TeamDTO> add(
			@Validated @RequestBody TeamDTO newTeam){
		
		Team t = toTeam.convert(newTeam); 
		teamService.save(t);
		
		return new ResponseEntity<>(toDTO.convert(t),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<TeamDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody TeamDTO edited){
		
		if(!id.equals(edited.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Team t = toTeam.convert(edited); 
		teamService.save(t);
		
		return new ResponseEntity<>(toDTO.convert(t),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<TeamDTO> delete(@PathVariable Long id){
		teamService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{teamId}/players")
	public ResponseEntity<List<PlayerDTO>> findByPlayersTeamId(
			@PathVariable Long teamId){
		List<Player> players = playerRepository.findByTeamId(teamId);
		
		return  new ResponseEntity<>(
				toPlayerDTO.convert(players),
				HttpStatus.OK);
	}
	
}
