package com.soa.concourservice.exception;

import java.util.Date;
import java.util.List;


public class ErrorMessage {
    private String message;
    private int status;
    private Date timestamp ;
    private Object errors;

	public ErrorMessage(String message, int status, Date timestamp, List<String> errors) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Object getErrors() {
		return errors;
	}
	public void setErrors(Object errors) {
		this.errors = errors;
	}


}
