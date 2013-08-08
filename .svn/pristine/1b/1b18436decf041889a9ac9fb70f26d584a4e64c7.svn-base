package com.cisco.urat.service;

import java.util.Calendar;
import java.util.List;

import com.cisco.urat.model.Item;
import com.cisco.urat.model.Document;
import com.cisco.urat.utilities.DocumentType;
import com.cisco.urat.utilities.FileType;

public interface DocumentService {

	public abstract long countAllDocuments();

	public abstract void deleteDocument(Document document);

	public abstract Document findDocument(Integer id);

	public abstract List<Document> findAllDocuments(Integer documentType);
	
	public abstract List<Document> findAllByFirstAndMaxResults(int firstResult, int maxResult);

	public abstract Document saveDocument(Document document);

	public abstract Document updateDocument(Document document);

	public abstract List<Document> findAllDocumentsByType(FileType docType);
	
	public abstract List<Document> findAllDocumentsByType(FileType docType, boolean published);

	public abstract List<Document> findAllDocumentsByTypeAndModifiedDate(
			FileType docType, Calendar dateTime);

	public abstract List<Document> findAllDocumentsByTypeAndModifiedDate(
			FileType docType, Calendar dateTime, boolean published);
	
	public abstract Document findByName(String name, Integer documentType);

	public abstract List<Document> findAllDocumentsByCategory(Item category, Integer documentType);

}
