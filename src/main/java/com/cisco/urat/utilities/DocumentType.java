package com.cisco.urat.utilities;

public enum DocumentType {

	COLLATERAL(0), UPGRADE(1);

	int type;

	DocumentType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
