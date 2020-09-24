package com.ibm.entity;

public class Department {

	private int id;
	private String name;
	private Manager manager;
	
	public Department() {
	}

	public Department(String name, Manager manager) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
