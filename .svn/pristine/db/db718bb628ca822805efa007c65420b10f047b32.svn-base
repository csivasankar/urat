package com.cisco.urat.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.urat.model.BlobData;
import com.cisco.urat.model.Upload;

public class FileUtil {

	protected static final Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static void loadFile(Upload upload, String uploadPath) {
		File file = null;
		try {
			file = new File(uploadPath + upload.getFilePath());
			create(file, upload.getBlobData());
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private static void create(File file, BlobData blobData) throws IOException, SQLException, FileNotFoundException{
		File currentFile = new File(file.getAbsolutePath());
		if (!currentFile.exists()) {
			if (blobData != null) {
				File filePath = new File(file.getAbsolutePath());
				String makeDir = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
				new File(makeDir).mkdirs();
				FileOutputStream fos = new FileOutputStream(filePath, false);
				Streams.copy(blobData.getContent().getBinaryStream(), fos, true);
				if(log.isDebugEnabled()){
					log.debug("File is created from BLOB in " + file.getPath());
				}
			} else {
				if(log.isDebugEnabled()){
					log.debug("BLOB is not available for " + file.getPath());
				}
			}
		}
	}

}
