package com.cisco.urat.dao;

import java.util.Calendar;
import java.util.List;

import com.cisco.urat.model.RuleFile;

public interface RuleFileDao {

	long count();

	void delete(RuleFile ruleFile);

	RuleFile findOne(Integer id);

	List<RuleFile> findAll();
	
	List<RuleFile> findAll(boolean published);

	RuleFile save(RuleFile ruleFile);

	RuleFile update(RuleFile ruleFile);

	List<RuleFile> findAllRuleFilesByModifiedDate(Calendar dateTime);
	
	List<RuleFile> findAllRuleFilesByModifiedDate(Calendar dateTime, boolean published);
}
