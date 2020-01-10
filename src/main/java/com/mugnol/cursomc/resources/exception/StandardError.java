package com.mugnol.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public synchronized Integer getStatus() {
		return status;
	}
	
	public synchronized void setStatus(Integer status) {
		this.status = status;
	}
	
	public synchronized String getMsg() {
		return msg;
	}
	
	public synchronized void setMsg(String msg) {
		this.msg = msg;
	}
	
	public synchronized Long getTimeStamp() {
		return timeStamp;
	}
	
	public synchronized void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
