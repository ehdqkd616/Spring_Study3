package com.kh.ajax.model.vo;

import org.springframework.stereotype.Component;

@Component
public class Sample2 {
	private String name;
	private int age;
	
	public Sample2() {}
	
	public Sample2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Sample2 [name=" + name + ", age=" + age + "]";
	}
	
	
	
	
}
