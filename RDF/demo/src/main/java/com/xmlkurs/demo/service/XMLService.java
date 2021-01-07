package com.xmlkurs.demo.service;

import javax.xml.bind.JAXBContext;

import com.xmlkurs.demo.fuseki.FusekiReader;
import com.xmlkurs.demo.fuseki.FusekiWriter;
import com.xmlkurs.demo.fuseki.MetadataExtractor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xmlkurs.demo.dom.DOMParser;
import com.xmlkurs.demo.dto.XMLDto;
import com.xmlkurs.demo.jaxb.JaxB;
import com.xmlkurs.demo.model.User;
import com.xmlkurs.demo.repository.FakultetRepository;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class XMLService {

    private final DOMParser domParser;
    private final JaxB jaxB;
    private final FakultetRepository fakultetRepository;
    private final MetadataExtractor metadataExtractor;

    public XMLService(DOMParser domParser, JaxB jaxB, FakultetRepository fakultetRepository, MetadataExtractor metadataExtractor) {
        this.domParser = domParser;
        this.jaxB = jaxB;
        this.fakultetRepository = fakultetRepository;
        this.metadataExtractor = metadataExtractor;
    }

    public String playWithXML(XMLDto dto) throws Exception {
        Document document = domParser.buildDocumentFromText(dto.getText());
        NodeList profesori = document.getElementsByTagName("profesor");

        for (int i = 0; i < profesori.getLength(); i++) {
            Element profesor = (Element) profesori.item(i);
            profesor.setAttribute("id", "prof" + i);

            Element titula = document.createElement("Titila");
            titula.appendChild(document.createTextNode("Profesor"));
            profesor.appendChild(titula);
        }

        return domParser.getDocumentAsString(document);

    }

    public String jaxBTest(XMLDto dto) throws Exception {
        JAXBContext context = JAXBContext.newInstance(User.class);


        User user = (User) jaxB.unmarshall(User.class, dto.getText());
        user.name = "novo ime";
        user.email = "novi email";
        System.out.println(user.id);

        return jaxB.marshall(User.class, user);


    }

    public void saveFileFromString(String text) throws Exception {
        fakultetRepository.saveFakultet(text);
        metadataExtractor.extractMetadata(text);
        FusekiWriter.saveRDF();
    }

    public ArrayList<String> searchByMetadata(String naziv, String godina) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("naziv", naziv);
        params.put("godina", godina);

        ArrayList<String> result = FusekiReader.executeQuery(params);
        return result;
    }

}
