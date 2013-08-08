package com.cisco.urat.dao;

import java.util.Calendar;
import java.util.List;

import com.cisco.urat.model.Item;
import com.cisco.urat.model.Document;

public interface DocumentDao {

	long count();

	void delete(Document document);

	Document findOne(Integer id);

	List<Document> findAll(Integer documentType);

	List<Document> findAllByFirstAndMaxResults(int firstResult, int maxResult);

	Document save(Document document);

	Document update(Document document);

	List<Document> findAllDocumentsByType(int type);
	
	List<Document> findAllDocumentsByType(int type, boolean published);

	List<Document> findAllDocumentsByType(int type, Calendar dateTime);
	
	List<Document> findAllDocumentsByType(int type, Calendar dateTime, boolean published);

	Document findByName(String name, Integer documentType);

	List<Document> findAllDocumentsByCategory(Item category, Integer documentType);
}
