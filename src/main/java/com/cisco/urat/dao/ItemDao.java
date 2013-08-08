package com.cisco.urat.dao;

import java.util.List;

import com.cisco.urat.model.Item;

public interface ItemDao {

	long count(Integer type);

	void delete(Item item);

	Item findOne(Integer id);

	Item findByNameAndType(String name, Integer type);
	
	Item save(Item item);

	List<Item> findAll(Integer type);

	Item update(Item item);
	
	Integer findMaxSortOrder(Integer type);
}
