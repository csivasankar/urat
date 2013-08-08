package com.cisco.urat.service;

import java.util.List;

import com.cisco.urat.model.Item;

public interface ItemService {
	
	public abstract long countAllItems(Integer type);

	public abstract void deleteItem(Item item);

	public abstract Item findItem(Integer id);

	public abstract List<Item> findAllItems(Integer type);
	
	public abstract void saveItem(Item item);

	public abstract Item updateItem(Item Item);

	public abstract Item findByNameAndType(String name, Integer type);
	
	public abstract Integer findMaxSortOrder(Integer type);
}
