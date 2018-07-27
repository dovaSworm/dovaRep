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

import statistic.model.Pozicija;
import statistic.service.PositionService;
import statistic.support.PositionToDTO;
import statistic.support.PositonDTOToPosition;
import statistic.web.dto.PositionDTO;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
		
		@Autowired
		private PositionService pSer;
		@Autowired
		private PositionToDTO toDTO;
		@Autowired
		private PositonDTOToPosition toPosition;
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<PositionDTO>> get(){
			return new ResponseEntity<>(toDTO.convert(pSer.findAll()),HttpStatus.OK);
		}
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<PositionDTO> get(
				@PathVariable Long id){
			
			Pozicija p = pSer.findOne(id);
			
			if(p == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(
					toDTO.convert(p),
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<PositionDTO> add(
				@Validated @RequestBody PositionDTO newPosition){
			
			Pozicija p = toPosition.convert(newPosition); 
			pSer.save(p);
			
			return new ResponseEntity<>(toDTO.convert(p),
					HttpStatus.CREATED);
		}
		
		@RequestMapping(method=RequestMethod.PUT,
				value="/{id}")
		public ResponseEntity<PositionDTO> edit(
				@PathVariable Long id,
				@Validated @RequestBody PositionDTO izmenjen){
			
			if(!id.equals(izmenjen.getId())){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Pozicija p = toPosition.convert(izmenjen); 
			pSer.save(p);
			
			return new ResponseEntity<>(toDTO.convert(p),
					HttpStatus.OK);
		}
		
		@RequestMapping(method=RequestMethod.DELETE,
				value="/{id}")
		public ResponseEntity<PositionDTO> delete(@PathVariable Long id){
			pSer.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
}
