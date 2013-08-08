package com.cisco.urat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.urat.dao.ItemDao;
import com.cisco.urat.model.Item;
import com.cisco.urat.utilities.DateUtil;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDao itemDao;

	public long countAllItems(Integer type) {
		return itemDao.count(type);
	}

	public void deleteItem(Item item) {
		itemDao.delete(item);
	}

	public Item findItem(Integer id) {
		return itemDao.findOne(id);
	}

	public List<Item> findAllItems(Integer type) {
		return itemDao.findAll(type);
	}

	public void saveItem(Item item) {
		populateModifiedDate(item);
		itemDao.save(item);
	}

	public Item updateItem(Item item) {
		populateModifiedDate(item);
		return itemDao.update(item);
	}

	private void populateModifiedDate(Item item) {
		item.setModified(DateUtil.getDate());
	}

	public Item findByNameAndType(String name, Integer type) {
		return itemDao.findByNameAndType(name, type);
	}
	
	public Integer findMaxSortOrder(Integer type) {
		return itemDao.findMaxSortOrder(type) == null ? 0 : itemDao.findMaxSortOrder(type);
	}
}
