package com.xmlkurs.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xmlkurs.demo.dto.XMLDto;
import com.xmlkurs.demo.model.Entitet;
import com.xmlkurs.demo.service.XMLService;

@RestController
@RequestMapping(value = "api/xml", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class XMLController {
	
	private XMLService service;

	public XMLController(XMLService service) {
		super();
		this.service = service;
	}

	@PostMapping()
	public ResponseEntity<XMLDto> getChangedXML(@RequestBody XMLDto dto) throws Exception{
		String response = service.playWithXML(dto);
		return new ResponseEntity<XMLDto>(new XMLDto(response), HttpStatus.OK);
	}
	
	@PostMapping("/jaxB")
	public ResponseEntity<XMLDto> getChangedXMLJaxB(@RequestBody XMLDto dto) throws Exception{
		String response = service.jaxBTest(dto);
		return new ResponseEntity<XMLDto>(new XMLDto(response), HttpStatus.OK);
	}
	
	@PostMapping("/xonomy")
	public ResponseEntity<String> addFakultet(@RequestBody Entitet entitet) throws Exception{
		
		service.saveFileFromString(entitet.getText());
		return new ResponseEntity<String>("Works",HttpStatus.OK);
		
	}
}
