package statistic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import statistic.model.BallGame;
import statistic.model.Player;
import statistic.model.PlayerInGame;
import statistic.service.PlayerService;
import statistic.support.BallGameDTOToBallGame;
import statistic.support.BallGameToDTO;
import statistic.support.PlayerDTOToPlayer;
import statistic.support.PlayerInGameToDTO;
import statistic.support.PlayerToDTO;
import statistic.web.dto.BallGameDTO;
import statistic.web.dto.PlayerDTO;
import statistic.web.dto.PlayerInGameDTO;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerService pSer;
	@Autowired
	private PlayerToDTO toDTO;
	@Autowired
	private PlayerDTOToPlayer toPlayer;
	@Autowired
	private BallGameDTOToBallGame toGame;
	@Autowired
	private PlayerInGameToDTO toPlayerInGameDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PlayerDTO>> get(@RequestParam(defaultValue = "0") int pageNum) {

		Page<Player> players;
		players = pSer.findAll(pageNum);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(players.getTotalPages()));
		return new ResponseEntity<>(toDTO.convert(players.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PlayerDTO> get(@PathVariable Long id) {
		Player p = pSer.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PlayerDTO> add(@Validated @RequestBody PlayerDTO newPlayer) {

		Player p = toPlayer.convert(newPlayer);
		if (!pSer.save(p)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<PlayerDTO> edit(@PathVariable Long id, @Validated @RequestBody PlayerDTO izmenjen) {

		if (!id.equals(izmenjen.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Player p = toPlayer.convert(izmenjen);
		pSer.save(p);

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<PlayerDTO> delete(@PathVariable Long id) {
		pSer.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
