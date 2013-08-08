package com.cisco.urat.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cisco.urat.model.RuleFile;

@Repository
public class RuleFileDaoImpl implements RuleFileDao {

	@Autowired
	private SessionFactory sessionFactory;

	public RuleFileDaoImpl() {

	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public long count() {
		return ((Number) getSession().createCriteria(RuleFile.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(RuleFile ruleFile) {
		getSession().delete(ruleFile);
	}

	public RuleFile findOne(Integer id) {
		return (RuleFile) getSession().get(RuleFile.class, id);
	}

	public List<RuleFile> findAll() {
		return getSession().createCriteria(RuleFile.class)
				.add(Restrictions.eq("state", true))
				.addOrder(Order.desc("modified")).list();
	}

	public List<RuleFile> findAll(boolean published) {
		return getSession().createCriteria(RuleFile.class)
				.add(Restrictions.eq("state", true))
				.add(Restrictions.eq("published", published))
				.addOrder(Order.desc("modified")).list();
	}
	
	public RuleFile save(RuleFile ruleFile) {
		Integer pk = (Integer) getSession().save(ruleFile);
		return findOne(pk);
	}

	public RuleFile update(RuleFile ruleFile) {
		return (RuleFile)getSession().merge(ruleFile);
	}

	public List<RuleFile> findAllRuleFilesByModifiedDate(Calendar dateTime) {
		Criteria crit = getSession().createCriteria(RuleFile.class);
		crit.add(Restrictions.gt("modified", dateTime));
		return crit.list();
	}

	public List<RuleFile> findAllRuleFilesByModifiedDate(Calendar dateTime, boolean published) {
		Criteria crit = getSession().createCriteria(RuleFile.class);
		crit.add(Restrictions.eq("published", published));
		crit.add(Restrictions.gt("modified", dateTime));
		return crit.list();
	}
}
