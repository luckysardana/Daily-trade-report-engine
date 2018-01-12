package com.assignment.jpmc.tradereporting.model;

public class Entity {

	private final String name;

	public Entity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + "]";
	}
	
	
}
