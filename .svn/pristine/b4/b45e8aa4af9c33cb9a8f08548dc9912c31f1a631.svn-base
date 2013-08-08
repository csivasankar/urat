package com.cisco.urat.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.urat.dao.RuleFileDao;
import com.cisco.urat.model.RuleFile;
import com.cisco.urat.utilities.DateUtil;

@Service
@Transactional
public class RuleFileServiceImpl implements RuleFileService {

	@Autowired
	RuleFileDao ruleFileDao;

	public long countAllRuleFiles() {
		return ruleFileDao.count();
	}

	public void deleteRuleFile(RuleFile ruleFile) {
		ruleFileDao.delete(ruleFile);
	}

	public RuleFile findRuleFile(Integer id) {
		return ruleFileDao.findOne(id);
	}

	public List<RuleFile> findAllRuleFiles() {
		return ruleFileDao.findAll();
	}

	public List<RuleFile> findAllRuleFiles(boolean published) {
		return ruleFileDao.findAll(published);
	}
	
	public RuleFile saveRuleFile(RuleFile ruleFile) {
		ruleFile.setState(true);
		populateModifiedDate(ruleFile);
		ruleFile.setCreated(ruleFile.getModified());
		return ruleFileDao.save(ruleFile);
	}

	private void populateModifiedDate(RuleFile ruleFile) {
		ruleFile.setModified(DateUtil.getDate());
	}

	public RuleFile updateRuleFile(RuleFile ruleFile) {
		RuleFile dbRuleFile = findRuleFile(ruleFile.getId());
		if(dbRuleFile != null) {
			ruleFile.setFile(dbRuleFile.getFile());
		}
		populateModifiedDate(ruleFile);
		return ruleFileDao.update(ruleFile);
	}

	public List<RuleFile> findAllRuleFilesByModifiedDate(Calendar dateTime) {
		return ruleFileDao.findAllRuleFilesByModifiedDate(dateTime);
	}

	public List<RuleFile> findAllRuleFilesByModifiedDate(Calendar dateTime, boolean published) {
		return ruleFileDao.findAllRuleFilesByModifiedDate(dateTime, published);
	}
}
