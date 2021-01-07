package com.xmlkurs.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;

import com.xmlkurs.demo.db.ExistManager;
@Repository
public class FakultetRepository {
	private String collectionId = "/db/fakulteti";
	@Autowired
	private ExistManager existManager;
	
	public void saveFakultet(String text) throws Exception {
		existManager.storeFromText(collectionId, "MojFakultet", text);
	}

}
