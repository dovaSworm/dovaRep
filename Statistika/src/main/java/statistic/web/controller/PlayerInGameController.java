package statistic.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import statistic.model.PlayerInGame;
import statistic.service.PlayerInGameService;
import statistic.support.PlayerInGameDTOToPlayerInGame;
import statistic.support.PlayerInGameToDTO;
import statistic.web.dto.PlayerInGameDTO;

@RestController
@RequestMapping("/api/playersInGame")
public class PlayerInGameController {

	@Autowired
	private PlayerInGameService inGameS;
	@Autowired
	private PlayerInGameToDTO toDTO;
	@Autowired
	private PlayerInGameDTOToPlayerInGame toInGame;
//	@Autowired
//	private HttpServletRequest request;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PlayerInGameDTO>> get() {
		System.out.println("usao uu metodu========================================");
		return new ResponseEntity<>(toDTO.convert(inGameS.findAll()), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PlayerInGameDTO> get(@PathVariable Long id) {

		PlayerInGame p = inGameS.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PlayerInGameDTO> add(@Validated @RequestBody PlayerInGameDTO newInGame) {

		PlayerInGame p = toInGame.convert(newInGame);
		inGameS.save(p);

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<PlayerInGameDTO> edit(@PathVariable Long id,
			@Validated @RequestBody PlayerInGameDTO izmenjen) {

		if (!id.equals(izmenjen.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		PlayerInGame p = toInGame.convert(izmenjen);
		inGameS.save(p);

		return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<PlayerInGameDTO> delete(@PathVariable Long id) {
		inGameS.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/change")
	public ResponseEntity<PlayerInGameDTO> change(@PathVariable Long id, @RequestParam String action) {
		PlayerInGame p = inGameS.findOne(id);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		PlayerInGameDTO ret = toDTO.convert(p);

//		String path = request.getRequestURI().substring(request.getContextPath().length());
//		String change = path.substring(path.lastIndexOf('/') + 1);
		inGameS.changeStatistic(id, action);
		System.out.println(action + "========================================");

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/byTeamId")
	public ResponseEntity<List<PlayerInGameDTO>> getPlayersInGame(@PathVariable Long id, @RequestParam Long gameId) {
		List<PlayerInGame> ret = inGameS.findPlayersInGameByTeamAndGame(id, gameId);
		if (ret == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println(ret.size() + " controleler");

		return new ResponseEntity<>(toDTO.convert(ret), HttpStatus.OK);
	}

}
