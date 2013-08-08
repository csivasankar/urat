package com.cisco.urat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cisco.urat.model.RuleFile;
import com.cisco.urat.service.ItemService;
import com.cisco.urat.service.RuleFileService;
import com.cisco.urat.utilities.ContextUtil;
import com.cisco.urat.utilities.ItemType;
import com.cisco.urat.utilities.RequestContext;

@Controller
@RequestMapping("/ruledatafiles")
public class RuleFileController {

	@Autowired
	RuleFileService ruleDataFileService;

	@Autowired
	ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel, HttpServletRequest req) {
		RequestContext.set(ContextUtil.populateContext(req));
		uiModel.addAttribute("ruleFileList", ruleDataFileService.findAllRuleFiles());
		return "ruledatafile/list";
	}

	@RequestMapping(value = "/add")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new RuleFile());
        return "ruledatafile/create";
    }
	
	private void populateEditForm(Model uiModel, RuleFile ruleDataFile) {
		uiModel.addAttribute("ruleDataFile", ruleDataFile);
	}
	
	@RequestMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
		populateEditForm(uiModel, ruleDataFileService.findRuleFile(id));
        return "ruledatafile/update";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public String update(RuleFile ruleFile, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, ruleFile);
            return "redirect:/ruledatafiles";
        }
        uiModel.asMap().clear();
        ruleDataFileService.updateRuleFile(ruleFile);
        return "redirect:/ruledatafiles";
    }
	
	@RequestMapping(value = "/publish/{id}", method = RequestMethod.POST)
	public String publish(@PathVariable("id") Integer id, @RequestParam(value="publish") Boolean publish) {
		RuleFile ruleFile = ruleDataFileService.findRuleFile(id);
		ruleFile.setPublished(publish);
		ruleDataFileService.updateRuleFile(ruleFile);
		return "redirect:/ruledatafiles";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		RuleFile ruleFile = ruleDataFileService.findRuleFile(id);
		if (ruleFile != null) {
			ruleDataFileService.deleteRuleFile(ruleFile);
		}
		return "redirect:/ruledatafiles";
	}
	
	@RequestMapping(value = "/uploadruledata")
    public String uploadRuleDataForm(Model uiModel) {
		uiModel.addAttribute("ruledDataFileTypeList", itemService.findAllItems(ItemType.RULE_DATA_FILE.getId()));
        return "ruledatafile/upload";
    }
}
