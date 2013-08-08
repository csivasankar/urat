package com.cisco.urat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.urat.model.BlobData;
import com.cisco.urat.model.Category;
import com.cisco.urat.model.Document;
import com.cisco.urat.model.RuleFile;
import com.cisco.urat.model.Upload;
import com.cisco.urat.service.BlobDataService;
import com.cisco.urat.service.CategoryService;
import com.cisco.urat.service.DocumentService;
import com.cisco.urat.service.RuleFileService;
import com.cisco.urat.service.UploadService;
import com.cisco.urat.utilities.DocumentType;
import com.cisco.urat.utilities.FileUtil;
import com.cisco.urat.utilities.UploadUtil;

@RequestMapping("/upload")
@Controller
public class UploadController {

	protected static final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
    UploadService uploadService;
	
	@Autowired
    DocumentService documentService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	RuleFileService ruleFileService;
	
	@Autowired
	BlobDataService blobDataService;
	
	@Autowired
	UploadUtil uploadUtil;
	
	@RequestMapping(value="/document", method = RequestMethod.POST, consumes="application/octet-stream", produces="application/json")
	@ResponseBody
    public String uploadDocument(@RequestParam("qqfile") String fileName, @RequestParam("qqsize") String fileSize,
    		@RequestParam(value="description", required = false) String description, 
    		@RequestParam(value="category", required = false) Integer category, @RequestParam("page_type") String pageType,
    		@RequestParam("name") String name, @RequestParam(value="stage", required = false) String stage, 
    		HttpServletRequest request) throws FileNotFoundException, IOException, InterruptedException {
		if (request.getInputStream() != null) {
			BlobData blobData = getBlobData(request.getInputStream());
			Upload upload = createUploadRecord(fileName, fileSize, blobData);
			FileUtil.loadFile(upload, uploadUtil.getUploadPath());
			if (pageType.equalsIgnoreCase("document")) {
				Category ctg = null;
				if(category != null) {
					ctg = categoryService.findCategory(category);
				}
				Document document = new Document();
				document.setName(name);
				document.setCategory(ctg);
				document.setStage(stage);
				document.setDescription(description);
				document.setFile(upload);
				if(upload.getExt().equalsIgnoreCase("avi") || upload.getExt().equalsIgnoreCase("mp4")) {
					document.setType(DocumentType.VIDEO.getType());
				} else {
					document.setType(DocumentType.PDF.getType());
				}
				documentService.saveDocument(document);
			} else if (pageType.equalsIgnoreCase("ruleDataFile")) {
				RuleFile ruleFile = new RuleFile();
				ruleFile.setName(name);
				ruleFile.setFile(upload);
				ruleFileService.saveRuleFile(ruleFile);
			}
			return  "{\"success\":true,\"file\":{\"id\":\"" +upload.getId()+ "\",\"ext\":\""+ upload.getExt()+"\",\"path\":\"" + upload.getFilePath() +"\",\"name\":\""+upload.getName()+"\"}}";
	    }
	    else {
	        return  "{\"success\": true}";
	    }
    }
	
	private BlobData getBlobData(InputStream is) throws IOException {
		Blob blob = Hibernate.createBlob(is);
		BlobData blobData = new BlobData();
		blobData.setContent(blob);
		return blobDataService.saveBlobData(blobData);
	}
	
	private Upload createUploadRecord(String file, String size, BlobData blobData) {
		Upload upload = new Upload();
		upload.setBlobData(blobData);
		upload.setName(file);
		upload.setSize(size);
		if (file.indexOf(".") > 0) {
			upload.setExt(file.substring(file.lastIndexOf(".") + 1));
		}
		upload = uploadService.saveUpload(upload);
		upload.setPath("docs" + "/" + upload.getId() + "/");
		return uploadService.updateUpload(upload);
	}
}
