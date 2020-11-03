package com.xmlkurs.demo.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xmlkurs.demo.dom.DOMParser;
import com.xmlkurs.demo.dto.XMLDto;
import com.xmlkurs.demo.jaxb.JaxB;
import com.xmlkurs.demo.model.User;

@Service
public class XMLService {

	private final DOMParser domParser;
	private final JaxB jaxB;

	public XMLService(DOMParser domParser, JaxB jaxB) {
		this.domParser = domParser;
		this.jaxB = jaxB;
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

}
