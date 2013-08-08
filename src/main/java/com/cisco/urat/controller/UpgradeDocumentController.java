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

import com.cisco.urat.model.Document;
import com.cisco.urat.model.Item;
import com.cisco.urat.service.DocumentService;
import com.cisco.urat.service.ItemService;
import com.cisco.urat.utilities.ContextUtil;
import com.cisco.urat.utilities.DocumentType;
import com.cisco.urat.utilities.ItemType;
import com.cisco.urat.utilities.RequestContext;
import com.cisco.urat.utilities.StringUtil;

@Controller
@RequestMapping("/upgradedocs")
public class UpgradeDocumentController {

	@Autowired
	ItemService itemService;

	@Autowired
	DocumentService documentService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel, HttpServletRequest req) {
		RequestContext.set(ContextUtil.populateContext(req));
		uiModel.addAttribute("documentList", documentService.findAllDocuments(DocumentType.UPGRADE.getType()));
		return "upgradedoc/list";
	}

	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
		uiModel.addAttribute("document", new Document());
        return "upgradedoc/create";
    }

	@RequestMapping(value = "/uploaddocumentdata")
    public String uploadRuleDataForm(Model uiModel) {
		uiModel.addAttribute("collateralDataFileTypeList", itemService.findAllItems(ItemType.UPGRADE_DOCUMENT.getId()));
        return "upgradedoc/upload";
    }
	
	@RequestMapping(value = "/publish/{id}", method = RequestMethod.POST)
	public String publish(@PathVariable("id") Integer id, @RequestParam(value="publish") Boolean publish) {
		Document document = documentService.findDocument(id);
		document.setPublished(publish);
		documentService.updateDocument(document);
		return "redirect:/upgradedocs";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		Document document = documentService.findDocument(id);
		if (document != null) {
			documentService.deleteDocument(document);
		}
		return "redirect:/upgradedocs";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
    public String update(Document document, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("document", document);
            return "redirect:/upgradedocs";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(document.getName())) {
			documentService.updateDocument(document);
		}
        return "redirect:/upgradedocs";
    }

	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
		uiModel.addAttribute("document", documentService.findDocument(id));
		uiModel.addAttribute("collateralDataFileOptions", getCollateralMap());
        return "upgradedoc/update";
    }
	
	private Map<String, String> getCollateralMap() {
		Map<String, String> optionMap = new LinkedHashMap<String, String>();
		for (Item collateral : itemService.findAllItems(ItemType.UPGRADE_DOCUMENT.getId())) {
			optionMap.put(collateral.getName(), collateral.getName());
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
		/*Document doc = documentService.findByName(document.getName(), DocumentType.UPGRADE.getType());
		if (doc != null && (document.getId() == null || !document.getId().equals(doc.getId()))) {
			return true;
		}*/
		return false;
	}
}
