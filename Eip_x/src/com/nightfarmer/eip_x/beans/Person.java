package com.nightfarmer.eip_x.beans;

public class Person {
	private String name;
	private String tel;
	private String sortLetters; // ��ʾ����ƴ��������ĸ

	
	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
