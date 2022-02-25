package com.rediscache.rediscache.model;

import java.io.Serializable;

public class RangeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Integer from;
	Integer to;
	
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Integer getTo() {
		return to;
	}
	public void setTo(Integer to) {
		this.to = to;
	}
	
	
}
