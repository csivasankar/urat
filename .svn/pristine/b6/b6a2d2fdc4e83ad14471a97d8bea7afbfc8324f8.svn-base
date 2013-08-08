package com.cisco.urat.service;

import java.util.Calendar;
import java.util.List;

import com.cisco.urat.model.RuleFile;

public interface RuleFileService {

	public abstract long countAllRuleFiles();

	public abstract void deleteRuleFile(RuleFile ruleFile);

	public abstract RuleFile findRuleFile(Integer id);

	public abstract List<RuleFile> findAllRuleFiles();
	
	public abstract List<RuleFile> findAllRuleFiles(boolean published);

	public abstract RuleFile saveRuleFile(RuleFile ruleFile);

	public abstract RuleFile updateRuleFile(RuleFile ruleFile);

	public abstract List<RuleFile> findAllRuleFilesByModifiedDate(
			Calendar dateTime);
	
	public abstract List<RuleFile> findAllRuleFilesByModifiedDate(
			Calendar dateTime, boolean published);

}
