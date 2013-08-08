package com.cisco.urat.controller;

import java.io.UnsupportedEncodingException;

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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.cisco.urat.model.Item;
import com.cisco.urat.service.ItemService;
import com.cisco.urat.utilities.ItemType;
import com.cisco.urat.utilities.StringUtil;

@RequestMapping("/items")
@Controller
public class ItemController {

	@Autowired
    ItemService itemService;

	@RequestMapping(method = RequestMethod.POST)
    public String create(Item item, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		String url = httpServletRequest.getHeader("referer");
        String contextPath = httpServletRequest.getContextPath();
        url = url.substring(url.indexOf(contextPath)+contextPath.length());
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, item);
            return "redirect:/items";
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(item.getName()) && !isDuplicateItem(item)) {
			item.setSortOrder(itemService.findMaxSortOrder(item.getType())+1);
			itemService.saveItem(item);
		}
        return "redirect:" + url;
    }

	@RequestMapping(value = "/validateItem", method = RequestMethod.POST)
	@ResponseBody
	public String validateItem(Item item) {
		if (isDuplicateItem(item)) {
			return "{\"errMsg\": \"" + ItemType.findItem(item.getType()).getDescription() + " already exists\"}";
		}
		return "{}";
	}
	
	private boolean isDuplicateItem(Item item) {
		Item itm = itemService.findByNameAndType(item.getName(), item.getType());
		if (itm != null && (item.getId() == null || !item.getId().equals(itm.getId()))) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/add/{type}")
    public String createForm(@PathVariable(value = "type") String type, Model uiModel) {
		Item item = new Item();
		if (type.equalsIgnoreCase(ItemType.CATEGORY.getType())) {
			item.setType(ItemType.CATEGORY.getId());
		} else if (type.equalsIgnoreCase(ItemType.STAGE.getType())) {
			item.setType(ItemType.STAGE.getId());
		} else if (type.equalsIgnoreCase(ItemType.RULE_DATA_FILE.getType())) {
			item.setType(ItemType.RULE_DATA_FILE.getId());
		} else if (type.equalsIgnoreCase(ItemType.UPGRADE_DOCUMENT.getType())) {
			item.setType(ItemType.UPGRADE_DOCUMENT.getId());
		}
        populateEditForm(uiModel, item);
        
        return "item/create";
    }

	@RequestMapping(value = "/show/{id}")
    public String show(@PathVariable("id") Integer id, Model uiModel, HttpServletRequest req) {
		uiModel.addAttribute("item", itemService.findItem(id));
        uiModel.addAttribute("itemId", id);
        return "item/show";
    }

	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public String list(@PathVariable("type") String type, @RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Integer itemTypeId = ItemType.findIdByType(type);
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            float nrOfPages = (float) itemService.countAllItems(itemTypeId) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("itemList", itemService.findAllItems(itemTypeId));
            uiModel.addAttribute("itemType", ItemType.findItem(itemTypeId));
        }
        return "item/list";
    }
    
    @RequestMapping(value = "/{type}", method = RequestMethod.PUT)
    public String update(@PathVariable("type") String type, Item item,
    		BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, item);
            return "redirect:/items/" + type;
        }
        uiModel.asMap().clear();
		if (!StringUtil.isEmptyTrim(item.getName()) && !isDuplicateItem(item)) {
			Item dbItem = itemService.findItem(item.getId());
			dbItem.setName(item.getName());
			itemService.updateItem(dbItem);
		}
        return "redirect:/items/" + type;
    }

	@RequestMapping(value = "/edit/{type}/{id}")
    public String updateForm(@PathVariable("id") Integer id, @PathVariable("type") String type, Model uiModel) {
        populateEditForm(uiModel, itemService.findItem(id));
        uiModel.addAttribute("type", type);
        return "item/update";
    }
	
	@RequestMapping(value = "/{type}/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, @PathVariable("type") String type, Model uiModel) {
        Item item = itemService.findItem(id);
        itemService.deleteItem(item);
        uiModel.asMap().clear();
        return "redirect:/items/" + type;
    }

	void populateEditForm(Model uiModel, Item item) {
        uiModel.addAttribute("item", item);
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
