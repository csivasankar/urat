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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.urat.model.BlobData;
import com.cisco.urat.model.Item;
import com.cisco.urat.model.Document;
import com.cisco.urat.model.RuleFile;
import com.cisco.urat.model.Upload;
import com.cisco.urat.service.BlobDataService;
import com.cisco.urat.service.ItemService;
import com.cisco.urat.service.DocumentService;
import com.cisco.urat.service.RuleFileService;
import com.cisco.urat.service.UploadService;
import com.cisco.urat.utilities.DocumentType;
import com.cisco.urat.utilities.FileType;
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
	ItemService itemService;
	
	@Autowired
	RuleFileService ruleFileService;
	
	@Autowired
	BlobDataService blobDataService;
	
	@Autowired
	UploadUtil uploadUtil;
	
	@RequestMapping(value="/document", method = RequestMethod.POST)
	@ResponseBody
    public ModelMap uploadDocument(@RequestParam("qqfile") String fileName, @RequestParam("qqsize") String fileSize,
    		@RequestParam(value="description", required = false) String description, 
    		@RequestParam(value="category", required = false) Integer category, @RequestParam("page_type") String pageType,
    		@RequestParam("name") String name, @RequestParam(value="stage", required = false) Integer stage, 
    		HttpServletRequest request, ModelMap uiModel) throws FileNotFoundException, IOException, InterruptedException {
		String response = "";
		if (request.getInputStream() != null) {
			BlobData blobData = getBlobData(request.getInputStream());
			Upload upload = createUploadRecord(fileName, fileSize, blobData);
			FileUtil.loadFile(upload, uploadUtil.getUploadPath());
			if (pageType.contains("document")) {
				Document document = new Document();
				if (pageType.contains("_c")) {
					Item item = null;
					if(category != null) {
						item = itemService.findItem(category);
					}
					document.setCategory(item);
					item = null;
					if(stage != null) {
						item = itemService.findItem(stage);
					}
					document.setStage(item);
					document.setDescription(description);
					document.setDocumentType(DocumentType.COLLATERAL.getType());
				} else {
					document.setDocumentType(DocumentType.UPGRADE.getType());
				}
				document.setName(name);
				document.setFile(upload);
				if(upload.getExt().equalsIgnoreCase("avi") || upload.getExt().equalsIgnoreCase("mp4")) {
					document.setFileType(FileType.VIDEO.getType());
				} else {
					document.setFileType(FileType.PDF.getType());
				}
				documentService.saveDocument(document);
			} else if (pageType.equalsIgnoreCase("ruleDataFile")) {
				RuleFile ruleFile = new RuleFile();
				ruleFile.setName(name);
				ruleFile.setFile(upload);
				ruleFileService.saveRuleFile(ruleFile);
			}
			response =  "{\"success\":true,\"file\":{\"id\":\"" +upload.getId()+ "\",\"ext\":\""+ upload.getExt()+"\",\"path\":\"" + upload.getFilePath() +"\",\"name\":\""+upload.getName()+"\"}}";
	    }
	    else {
	        response =  "{\"success\": true}";
	    }
		uiModel.addAttribute("response", response);
		return uiModel;
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
