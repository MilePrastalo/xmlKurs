package com.xmlkurs.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import com.xmlkurs.demo.dto.XMLDto;
import com.xmlkurs.demo.model.Entitet;
import com.xmlkurs.demo.service.XMLService;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "api/xml", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class XMLController {

    private XMLService service;

    public XMLController(XMLService service) {
        super();
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<XMLDto> getChangedXML(@RequestBody XMLDto dto) throws Exception {
        String response = service.playWithXML(dto);
        return new ResponseEntity<XMLDto>(new XMLDto(response), HttpStatus.OK);
    }

    @PostMapping(value = "/jaxB", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<XMLDto> getChangedXMLJaxB(@RequestBody XMLDto dto) throws Exception {
        String response = service.jaxBTest(dto);
        return new ResponseEntity<XMLDto>(new XMLDto(response), HttpStatus.OK);
    }

    @PostMapping(value = "/xonomy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addFakultet(@RequestBody Entitet entitet) throws Exception {

        service.saveFileFromString(entitet.getText());
        return new ResponseEntity<String>("Works", HttpStatus.OK);

    }

    @GetMapping("fusekiSearch/{naziv}/{godina}")
    public ResponseEntity<String> searchFromRDF(@PathVariable("naziv") String naziv, @PathVariable("godina") String godina) throws IOException {
        ArrayList<String> result = service.searchByMetadata(naziv, godina);
        String output = "";
        for (String r : result) {
            output += "\n" + r;
        }
        System.out.println("OUTPUT: " + output);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
