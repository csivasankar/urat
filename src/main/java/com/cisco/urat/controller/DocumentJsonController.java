package com.cisco.urat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.cisco.urat.model.json.DocumentDto;
import com.cisco.urat.model.json.JSONDto;
import com.cisco.urat.service.RuleFileService;
import com.cisco.urat.service.DocumentService;
import com.cisco.urat.utilities.ContextUtil;
import com.cisco.urat.utilities.DateUtil;
import com.cisco.urat.utilities.FileType;
import com.cisco.urat.utilities.RequestContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wservices")
public class DocumentJsonController {

	@Autowired
	DocumentService uploadService;

	@Autowired
	RuleFileService ruleDataFileService;

	@RequestMapping(value = "/documents")
	@ResponseBody
	public String getProductsJson(
			@RequestParam(value = "datetime", required = false) @DateTimeFormat(pattern = DateUtil.DATE_FORMAT) Calendar dateTime,
			@RequestParam(value = "testmode", defaultValue="0") int testmode,
			HttpServletRequest req, ModelMap uiModel) throws JsonGenerationException,
			JsonMappingException, IOException {
		RequestContext.set(ContextUtil.populateContext(req));
		DocumentDto dto = new DocumentDto();
		boolean published = testmode == 0;
		if (dateTime == null) {
			dto.setRuleDataFiles(ruleDataFileService.findAllRuleFiles(published));
			dto.setDocuments(uploadService.findAllDocumentsByType(FileType.PDF, published));
			dto.setVideos(uploadService.findAllDocumentsByType(FileType.VIDEO, published));
		} else {
			dto.setRuleDataFiles(ruleDataFileService.findAllRuleFilesByModifiedDate(dateTime, published));
			dto.setDocuments(uploadService.findAllDocumentsByTypeAndModifiedDate(FileType.PDF, dateTime, published));
			dto.setVideos(uploadService.findAllDocumentsByTypeAndModifiedDate(FileType.VIDEO, dateTime, published));
		}
		return generateJson(dto);
	}

	private String generateJson(JSONDto dto) throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT));
		return mapper.writeValueAsString(dto);
	}
}
