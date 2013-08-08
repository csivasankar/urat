package com.cisco.urat.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.urat.model.Item;
import com.cisco.urat.model.Document;
import com.cisco.urat.service.ItemService;
import com.cisco.urat.service.DocumentService;
import com.cisco.urat.utilities.ContextUtil;
import com.cisco.urat.utilities.DocumentType;
import com.cisco.urat.utilities.ItemType;
import com.cisco.urat.utilities.RequestContext;
import com.cisco.urat.utilities.StringUtil;

@Controller
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	DocumentService documentService;
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel, HttpServletRequest req) {
		RequestContext.set(ContextUtil.populateContext(req));
		uiModel.addAttribute("documentList", documentService.findAllDocuments(DocumentType.COLLATERAL.getType()));
		uiModel.addAttribute("categoryList", itemService.findAllItems(ItemType.CATEGORY.getId()));
		return "document/list";
	}

	@RequestMapping(value = "/byCategory/{id}", method = RequestMethod.GET)
	public String byCategory(@PathVariable("id") Integer id, Model uiModel, HttpServletRequest req) {
		Item item = itemService.findItem(id);
		RequestContext.set(ContextUtil.populateContext(req));
		uiModel.addAttribute("documentList", documentService.findAllDocumentsByCategory(item, DocumentType.COLLATERAL.getType()));
		uiModel.addAttribute("categoryList", itemService.findAllItems(ItemType.CATEGORY.getId()));
		uiModel.addAttribute("selectedCategory", item);
		return "document/list";
	}
	
	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Document());
        return "document/create";
    }
	
	private void populateEditForm(Model uiModel, Document document) {
		uiModel.addAttribute("document", document);
		uiModel.addAttribute("categoryOptions", getCategoryMap());
		uiModel.addAttribute("stageOptions", getStageMap());
	}
	
	/* build category map */
	private Map<String, String> getCategoryMap() {
		Map<String, String> optionMap = new LinkedHashMap<String, String>();
		optionMap.put("", "Select");
		for (Item category : itemService.findAllItems(ItemType.CATEGORY.getId())) {
			optionMap.put(category.getId().toString(), category.getName());
		}
		return optionMap;
	}
	
	private Map<String, String> getStageMap() {
		Map<String, String> optionMap = new LinkedHashMap<String, String>();
		optionMap.put("", "Select");
		for (Item stage : itemService.findAllItems(ItemType.STAGE.getId())) {
			optionMap.put(stage.getId().toString(), stage.getName());
		}
		return optionMap;
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@ResponseBody
	public String validateDocument(Document document) {
		if (isDuplicateDocument(document)) {
			return "{\"errMsg\": \"Document Name already exists\"}";
		}
		return "{}";
	}
	
	private boolean isDuplicateDocument(Document document) {
		Document doc = documentService.findByName(document.getName(), DocumentType.COLLATERAL.getType());
		if (doc != null && (document.getId() == null || !document.getId().equals(doc.getId()))) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		Document document = documentService.findDocument(id);
		if (document != null) {
			documentService.deleteDocument(document);
		}
		return "redirect:/documents";
	}
	
	@RequestMapping(value = "/publish/{id}", method = RequestMethod.POST)
	public String publish(@PathVariable("id") Integer id, @RequestParam(value="publish") Boolean publish) {
		Document document = documentService.findDocument(id);
		document.setPublished(publish);
		documentService.updateDocument(document);
		return "redirect:/documents";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
    public String update(Document document, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, document);
            return "redirect:/documents";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(document.getName()) && !isDuplicateDocument(document)) {
			documentService.updateDocument(document);
		}
        return "redirect:/documents";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, documentService.findDocument(id));
        return "document/update";
    }
}
