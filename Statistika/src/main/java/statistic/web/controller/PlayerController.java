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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import statistic.model.Player;
import statistic.service.PlayerService;
import statistic.support.PlayerDTOToPlayer;
import statistic.support.PlayerToDTO;
import statistic.web.dto.PlayerDTO;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	@Autowired
	private PlayerToDTO toDTO;
	@Autowired
	private PlayerDTOToPlayer toPlayer;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PlayerDTO>> get(@RequestParam(defaultValue = "0") int pageNum) {

		List<Player> players;
		players = playerService.findAll();
		return new ResponseEntity<>(toDTO.convert(players), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PlayerDTO> get(@PathVariable Long id) {
		Player p = playerService.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PlayerDTO> add(@Validated @RequestBody PlayerDTO newPlayer) {

		Player p = toPlayer.convert(newPlayer);
		if (!playerService.save(p)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<PlayerDTO> edit(@PathVariable Long id, @Validated @RequestBody PlayerDTO edited) {

		if (!id.equals(edited.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Player p = toPlayer.convert(edited);
		playerService.save(p);

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<PlayerDTO> delete(@PathVariable Long id) {
		playerService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
