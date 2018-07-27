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

import statistic.model.Player;
import statistic.service.PlayerService;
import statistic.support.PlayerDTOToPlayer;
import statistic.support.PlayerToDTO;
import statistic.web.dto.PlayerDTO;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerService pSer;
	@Autowired
	private PlayerToDTO toDTO;
	@Autowired
	private PlayerDTOToPlayer toPlayer;

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

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PlayerDTO> add(
			@Validated @RequestBody PlayerDTO newPlayer){
		
		Player p = toPlayer.convert(newPlayer); 
		if(!pSer.save(p)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<PlayerDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody PlayerDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Player p = toPlayer.convert(izmenjen); 
		pSer.save(p);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/onePointShoot")
	public ResponseEntity<PlayerDTO> oneShot(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addOneShot(id);
		
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/onePointScore")
	public ResponseEntity<PlayerDTO> oneScore(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addOneShot(id);
		pSer.addOneScore(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/twoPointShoot")
	public ResponseEntity<PlayerDTO> twoShot(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addTwoShot(id);
		
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/twoPointScore")
	public ResponseEntity<PlayerDTO> twoScore(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addTwoShot(id);
		pSer.addTwoScore(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/threePointShoot")
	public ResponseEntity<PlayerDTO> threeShot(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addThreeShot(id);
		
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/threePointScore")
	public ResponseEntity<PlayerDTO> threeScore(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addThreeShot(id);
		pSer.addThreeScore(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/assist")
	public ResponseEntity<PlayerDTO> assist(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addAssist(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/faul")
	public ResponseEntity<PlayerDTO> faul(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addFaul(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/to")
	public ResponseEntity<PlayerDTO> to(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addTO(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/def")
	public ResponseEntity<PlayerDTO> defReb(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addDefRebound(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/off")
	public ResponseEntity<PlayerDTO> offReb(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addOffRebound(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/steal")
	public ResponseEntity<PlayerDTO> steal(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addSteal(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}/block")
	public ResponseEntity<PlayerDTO> block(
			@PathVariable Long id
			){
		Player p = pSer.findOne(id);
		if(p == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		pSer.addBlock(id);
		
		return new ResponseEntity<>(toDTO.convert(p),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<PlayerDTO> delete(@PathVariable Long id){
		pSer.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
