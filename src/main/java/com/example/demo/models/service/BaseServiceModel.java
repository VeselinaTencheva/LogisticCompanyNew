package com.example.demo.models.service;

public abstract class BaseServiceModel {
	
	private String id;

	protected BaseServiceModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
