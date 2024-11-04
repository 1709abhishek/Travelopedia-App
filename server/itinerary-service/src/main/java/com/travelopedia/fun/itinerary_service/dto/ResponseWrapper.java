package com.travelopedia.fun.itinerary_service.dto;

public class ResponseWrapper<T> {
	
	private String message;
	//private String status;
	private T data;
	public ResponseWrapper() {
		super();
	}
	public ResponseWrapper(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}
	public ResponseWrapper(String message) {
		super();
		this.message = message;
		this.data = null;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	

}
