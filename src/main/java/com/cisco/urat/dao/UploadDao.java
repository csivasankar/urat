package com.cisco.urat.dao;

import java.util.List;

import com.cisco.urat.model.Upload;

public interface UploadDao {

	long count();

	void delete(Upload upload);

	Upload findOne(Integer id);

	List<Upload> findAll();

	Upload save(Upload upload);

	Upload update(Upload upload);

	List<Upload> findUploadEntries(int startIndex, int maxRecords);
}
