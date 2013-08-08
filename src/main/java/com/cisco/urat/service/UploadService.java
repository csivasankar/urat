package com.cisco.urat.service;

import java.util.List;

import com.cisco.urat.model.Upload;

public interface UploadService {

	public abstract long countAllUploads();

	public abstract void deleteUpload(Upload upload);

	public abstract Upload findUpload(Integer id);

	public abstract List<Upload> findAllUploads();

	public abstract Upload saveUpload(Upload upload);

	public abstract Upload updateUpload(Upload upload);

	public abstract List<Upload> findUploadEntries(int startIndex,
			int maxRecords);

}
