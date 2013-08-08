package com.cisco.urat.model.json;

import java.text.ParseException;
import java.util.Date;

import com.cisco.urat.utilities.DateUtil;

public class BaseDto implements JSONDto {

	private Date datetime = new Date();

	public long getDatetime() throws ParseException {
		return DateUtil.getDate(datetime).getTime();
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}
