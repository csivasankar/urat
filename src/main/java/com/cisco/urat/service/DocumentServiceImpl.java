package com.cisco.urat.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.urat.dao.DocumentDao;
import com.cisco.urat.model.Item;
import com.cisco.urat.model.Document;
import com.cisco.urat.utilities.DateUtil;
import com.cisco.urat.utilities.FileType;


@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
    DocumentDao documentDao;
	
	@Autowired
	UploadService uploadService;
	
	public long countAllDocuments() {
        return documentDao.count();
    }

	public void deleteDocument(Document document) {
		if(document.getFile() != null) {
			uploadService.deleteUpload(document.getFile());
		}
		document.setFile(null);
		document.setState(false);
        documentDao.update(document);
    }

	public Document findDocument(Integer id) {
        return documentDao.findOne(id);
    }

	public List<Document> findAllDocuments(Integer documentType) {
        return documentDao.findAll(documentType);
    }
	
	public List<Document> findAllByFirstAndMaxResults(int firstResult, int maxResult) {
        return documentDao.findAllByFirstAndMaxResults(firstResult, maxResult);
    }

	public Document saveDocument(Document document) {
		document.setState(true);
		populateModifiedDate(document);
		document.setCreated(document.getModified());
        return documentDao.save(document);
    }

	private void populateModifiedDate(Document document) {
		document.setModified(DateUtil.getDate());
	}

	public Document updateDocument(Document document) {
		Document dbDocument = findDocument(document.getId());
		if(dbDocument != null) {
			document.setFile(dbDocument.getFile());
		}
		populateModifiedDate(document);
        return documentDao.update(document);
    }

	public List<Document> findAllDocumentsByType(FileType fileType) {
		return documentDao.findAllDocumentsByType(fileType.getType());
	}

	public List<Document> findAllDocumentsByType(FileType fileType, boolean published) {
		return documentDao.findAllDocumentsByType(fileType.getType(), published);
	}
	
	public List<Document> findAllDocumentsByTypeAndModifiedDate(
			FileType docType, Calendar dateTime) {
		return documentDao.findAllDocumentsByType(docType.getType(), dateTime);
	}

	public List<Document> findAllDocumentsByTypeAndModifiedDate(
			FileType docType, Calendar dateTime, boolean published) {
		return documentDao.findAllDocumentsByType(docType.getType(), dateTime, published);
	}
	
	public Document findByName(String name, Integer documentType) {
		return documentDao.findByName(name, documentType);
	}

	public List<Document> findAllDocumentsByCategory(Item category, Integer documentType) {
		return documentDao.findAllDocumentsByCategory(category, documentType);
	}
}
