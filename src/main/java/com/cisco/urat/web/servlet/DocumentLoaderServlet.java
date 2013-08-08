package com.cisco.urat.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.cisco.urat.model.Upload;
import com.cisco.urat.service.UploadService;
import com.cisco.urat.utilities.FileUtil;
import com.cisco.urat.utilities.UploadUtil;

/**
 * @author Sudhakar Nallam
 * 
 *         Document Loader Servlet responsible for loading the uploaded
 *         documents from Blob table into the temp folder.
 */
@Configurable
@SuppressWarnings("serial")
public class DocumentLoaderServlet extends HttpServlet {
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	UploadUtil uploadUtil;
	
	protected static final Logger log = LoggerFactory.getLogger(DocumentLoaderServlet.class);
	
	public void init() {
		loadDocuments();
		try {
			if(log.isDebugEnabled()) {
				log.debug("com.ibm.websphere.servlet.temp.dir = " + System.getProperty("com.ibm.websphere.servlet.temp.dir"));
				log.debug("upload path " + uploadUtil.getUploadPath());
			}
		} catch (Exception e){
			
		}
	}
	
	/**
	 * Loads documents in batches 
	 */
	public void loadDocuments() {
		int startIndex = 0;
        int maxRecords = 250;
        
        long totalFiles = uploadService.countAllUploads();
        
        while(startIndex < totalFiles){        	
        	List<Upload> uploads = uploadService.findUploadEntries(startIndex, maxRecords);
        	for (Upload upload: uploads) {
        		FileUtil.loadFile(upload, uploadUtil.getUploadPath());
        	}
        	startIndex += maxRecords;
        }
	}
}
