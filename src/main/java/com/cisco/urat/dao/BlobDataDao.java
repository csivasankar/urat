package com.cisco.urat.dao;

import com.cisco.urat.model.BlobData;

public interface BlobDataDao {

	void delete(BlobData blobData);

	BlobData findOne(Integer id);

	BlobData save(BlobData blobData);

	BlobData update(BlobData blobData);

}
