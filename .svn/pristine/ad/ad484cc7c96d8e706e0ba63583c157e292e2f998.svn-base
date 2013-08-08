package com.cisco.urat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.cisco.urat.model.Document;
import com.cisco.urat.model.Item;
import com.cisco.urat.model.RuleFile;
import com.cisco.urat.service.DocumentService;
import com.cisco.urat.service.ItemService;
import com.cisco.urat.service.RuleFileService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
	}
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	RuleFileService ruleDataFileService;
	
	@Autowired
    ItemService itemService;
	
	public Converter<Integer, Item> getIdToItemConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, com.cisco.urat.model.Item>() {
            public com.cisco.urat.model.Item convert(java.lang.Integer id) {
                return itemService.findItem(id);
            }
        };
    }
	
	public Converter<String, Item> getStringToItemConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.cisco.urat.model.Item>() {
            public com.cisco.urat.model.Item convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Item.class);
            }
        };
    }
	
	public Converter<Item, String> getItemToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.cisco.urat.model.Item, java.lang.String>() {
            public String convert(Item item) {
                return new StringBuilder().append(item.getModified()).append(' ').append(item.getName()).toString();
            }
        };
    }
	
	public Converter<Integer, Document> getIdToDocumentsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, com.cisco.urat.model.Document>() {
            public com.cisco.urat.model.Document convert(java.lang.Integer id) {
                return documentService.findDocument(id);
            }
        };
    }
	
	public Converter<Integer, RuleFile> getIdToRuleFileConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, com.cisco.urat.model.RuleFile>() {
            public com.cisco.urat.model.RuleFile convert(java.lang.Integer id) {
                return ruleDataFileService.findRuleFile(id);
            }
        };
    }
	
	public Converter<String, RuleFile> getStringToRuleFileConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.cisco.urat.model.RuleFile>() {
            public com.cisco.urat.model.RuleFile convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), RuleFile.class);
            }
        };
    }
	
	public Converter<String, Document> getStringToDocumentsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.cisco.urat.model.Document>() {
            public com.cisco.urat.model.Document convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Document.class);
            }
        };
    }
	
	public void installLabelConverters(FormatterRegistry registry) {
		 registry.addConverter(getIdToItemConverter());
		 registry.addConverter(getIdToDocumentsConverter());
		 registry.addConverter(getStringToItemConverter());
		 registry.addConverter(getStringToDocumentsConverter());
		 registry.addConverter(getIdToRuleFileConverter());
		 registry.addConverter(getStringToRuleFileConverter());
	}
	
	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
