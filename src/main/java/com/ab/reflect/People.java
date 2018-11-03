package com.ab.reflect;

import java.util.Date;
import java.util.List;

public class People {
	
	private int id;
	private String name;
	private Date birthday;
	private List hobby;
	
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public List getHobby() {
		return hobby;
	}
	public void setHobby(List hobby) {
		this.hobby = hobby;
	}
	
}
