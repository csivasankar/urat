package com.cisco.urat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.urat.dao.UploadDao;
import com.cisco.urat.model.Upload;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

	@Autowired
	UploadDao uploadDao;

	@Autowired
	BlobDataService blobDataService;
	
	public long countAllUploads() {
		return uploadDao.count();
	}

	public void deleteUpload(Upload upload) {
		if(upload.getBlobData() != null) {
			blobDataService.deleteBlobData(upload.getBlobData());
		}
		uploadDao.delete(upload);
	}

	public Upload findUpload(Integer id) {
		return uploadDao.findOne(id);
	}

	public List<Upload> findAllUploads() {
		return uploadDao.findAll();
	}

	public Upload saveUpload(Upload upload) {
		return uploadDao.save(upload);
	}

	public Upload updateUpload(Upload upload) {
		return uploadDao.update(upload);
	}

	public List<Upload> findUploadEntries(int startIndex, int maxRecords) {
		return uploadDao.findUploadEntries(startIndex, maxRecords);
	}
}
