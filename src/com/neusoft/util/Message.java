package com.neusoft.util;

public class Message {
	
	private boolean result;
	private String message;
	
	

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public Message() {
		super();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
