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

import statistic.model.BallGame;
import statistic.service.BallGameService;
import statistic.support.BallGameDTOToBallGame;
import statistic.support.BallGameToDTO;
import statistic.web.dto.BallGameDTO;

@RestController
@RequestMapping("/api/ballGame")
public class BallGameController {

	@Autowired
	private BallGameService ballgameService;
	@Autowired
	private BallGameToDTO toDTO;
	@Autowired
	private BallGameDTOToBallGame toGame;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BallGameDTO>> get() {
		return new ResponseEntity<>(toDTO.convert(ballgameService.findAll()), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BallGameDTO> get(@PathVariable Long id) {

		BallGame bg = ballgameService.findOne(id);
		if (bg == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(bg), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BallGameDTO> add(@Validated @RequestBody BallGameDTO newBallGame) {

		BallGame bg = toGame.convert(newBallGame);
		BallGame saved = ballgameService.save(bg);
		if(saved == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<BallGameDTO> edit(@PathVariable Long id, @Validated @RequestBody BallGameDTO edited) {

		if (!id.equals(edited.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		BallGame bg = toGame.convert(edited);
		ballgameService.save(bg);

		return new ResponseEntity<>(toDTO.convert(bg), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<BallGameDTO> delete(@PathVariable Long id) {
		ballgameService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/result/{id}")
	public ResponseEntity<BallGameDTO> result(@PathVariable Long id) {
		BallGame bg = ballgameService.result(id);
		return new ResponseEntity<>(toDTO.convert(bg), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeOut?{id}")
	public ResponseEntity<BallGameDTO> result(@PathVariable Long id, @PathVariable Long teamId) {
		BallGame bg = ballgameService.timeOut(id, teamId);
		return new ResponseEntity<>(toDTO.convert(bg), HttpStatus.NO_CONTENT);
	}

}
