package com.cisco.urat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.urat.dao.BlobDataDao;
import com.cisco.urat.model.BlobData;

@Service
@Transactional
public class BlobDataServiceImpl implements BlobDataService {

	@Autowired
	BlobDataDao blobDataDao;

	public void deleteBlobData(BlobData blobData) {
		blobDataDao.delete(blobData);
	}

	public BlobData findBlobData(Integer id) {
		return blobDataDao.findOne(id);
	}

	public BlobData saveBlobData(BlobData blobData) {
		return blobDataDao.save(blobData);
	}

	public BlobData updateBlobData(BlobData blobData) {
		return blobDataDao.update(blobData);
	}

}
