package com.xmlkurs.demo.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable {
	@XmlAttribute
	public Long id;
	public String name;
	public String email;
	public String username;
	public String password;
}
