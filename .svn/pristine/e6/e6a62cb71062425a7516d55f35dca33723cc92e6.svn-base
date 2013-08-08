package com.cisco.urat.utilities;

public enum ItemType {

	CATEGORY(0, "category", "Category"), 
	STAGE(1, "stage", "Stage"), 
	RULE_DATA_FILE(2, "ruledatafile", "Rule Data File Type"), 
	UPGRADE_DOCUMENT(3,	"upgradedocument", "Upgrade Document Type");

	Integer id;
	String type;
	String description;

	ItemType(Integer id, String type, String description) {
		this.id = id;
		this.type = type;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static ItemType findItem(Integer id) {
		for (ItemType itemType : ItemType.values()) {
			if (itemType.getId() == id) {
				return itemType;
			}
		}
		return null;
	}

	public static Integer findIdByType(String type) {
		for (ItemType itemType : ItemType.values()) {
			if (itemType.getType().equalsIgnoreCase(type)) {
				return itemType.getId();
			}
		}
		return null;
	}

}
