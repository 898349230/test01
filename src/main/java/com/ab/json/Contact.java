package com.ab.json;

public class Contact {
	private String phone; //移动电话  
    private String address; //地址  
    private String qq; //QQ  
    
    
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Contact() {
		super();
	}

	public Contact(String phone, String address, String qq) {
		super();
		this.phone = phone;
		this.address = address;
		this.qq = qq;
	}

	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", address=" + address + ", qq=" + qq + "]";
	}
    
}
