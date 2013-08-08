package com.cisco.urat.utilities;

public enum FileType {
	PDF(0), VIDEO(1);

	int type;

	FileType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}