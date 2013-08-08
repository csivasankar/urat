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

import com.cisco.urat.model.Item;
import com.cisco.urat.model.Document;

@Repository
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public DocumentDaoImpl() {

	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Document.class)
				.add(Restrictions.eq("state", true))
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Document document) {
		getSession().delete(document);
	}

	public Document findOne(Integer id) {
		return (Document) getSession().get(Document.class, id);
	}

	public List<Document> findAll(Integer documentType) {
		return getSession().createCriteria(Document.class)
				.add(Restrictions.eq("state", true))
				.add(Restrictions.eq("documentType", documentType))
				.addOrder(Order.desc("modified")).list();
	}
	
	public List<Document> findAllByFirstAndMaxResults(int fs, int ms) {
		return getSession().createCriteria(Document.class)
				.add(Restrictions.eq("state", true)).setFirstResult(fs).setMaxResults(ms)
				.addOrder(Order.desc("modified")).list();
	}

	public Document save(Document document) {
		Integer pk = (Integer) getSession().save(document);
		return findOne(pk);
	}

	public Document update(Document document) {
		return (Document)getSession().merge(document);
	}

	public List<Document> findAllDocumentsByType(int fileType) {
		Criteria crit = getSession().createCriteria(Document.class);
		crit.add(Restrictions.eq("fileType", fileType));
		return crit.list();
	}
	
	public List<Document> findAllDocumentsByType(int fileType, boolean published) {
		Criteria crit = getSession().createCriteria(Document.class);
		crit.add(Restrictions.eq("published", published));
		crit.add(Restrictions.eq("fileType", fileType));
		return crit.list();
	}

	public List<Document> findAllDocumentsByType(int fileType, Calendar dateTime) {
		Criteria crit = getSession().createCriteria(Document.class);
		crit.add(Restrictions.eq("fileType", fileType));
		crit.add(Restrictions.gt("modified", dateTime));
		return crit.list();
	}

	public List<Document> findAllDocumentsByType(int fileType, Calendar dateTime, boolean published) {
		Criteria crit = getSession().createCriteria(Document.class);
		crit.add(Restrictions.eq("fileType", fileType));
		crit.add(Restrictions.eq("published", published));
		crit.add(Restrictions.gt("modified", dateTime));
		return crit.list();
	}
	
	public Document findByName(String name, Integer documentType) {
		Criteria crit = getSession().createCriteria(Document.class);
		crit.add(Restrictions.eq("name", name));
		crit.add(Restrictions.eq("documentType", documentType));
		crit.add(Restrictions.eq("state", true));
		return (Document) (crit.uniqueResult());
	}

	public List<Document> findAllDocumentsByCategory(Item category, Integer documentType) {
		Criteria crit = getSession().createCriteria(Document.class);
		crit.add(Restrictions.eq("category", category));
		crit.add(Restrictions.eq("documentType", documentType));
		return crit.list();
	}

}
