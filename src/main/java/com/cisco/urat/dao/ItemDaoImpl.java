package com.cisco.urat.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cisco.urat.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public long count(Integer type) {
		return ((Number) getSession().createCriteria(Item.class)
				.add(Restrictions.eq("type", type))
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public Item findOne(Integer id) {
		return (Item) getSession().get(Item.class, id);
	}

	public Item findByNameAndType(String name, Integer type) {
		Criteria crit = getSession().createCriteria(Item.class);
		crit.add(Restrictions.eq("name", name));
		crit.add(Restrictions.eq("type", type));
		return (Item) (crit.uniqueResult());
	}
	
	public Item save(Item item) {
		Integer pk = (Integer) getSession().save(item);
		return findOne(pk);
	}

	public Item update(Item item) {
		getSession().update(item);
		return findOne(item.getId());
	}

	public List<Item> findAll(Integer type) {
		return getSession().createCriteria(Item.class).add(Restrictions.eq("type", type)).list();
	}

	public void delete(Item item) {
		getSession().delete(item);
	}
	
	public Integer findMaxSortOrder(Integer type) {
		Criteria crit = getSession().createCriteria(Item.class);
		crit.setProjection(Projections.max("sortOrder"));
		crit.add(Restrictions.eq("type", type));
		return (Integer) crit.uniqueResult();
	}
}
