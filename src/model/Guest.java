package model;

import java.sql.Date;

public class Guest {

	private String name;
	private String nationality;
	private MyDate dateofbirth;
	private String telephoneNo;
	private String email;
	private String passportNo ;

	// For registration
	public Guest(String name, String nationality, MyDate dateofbirth, 
			String telephoneNo, String email,String passportNo) {
		this.setName(name);
		this.setNationality(nationality);
		this.dateofbirth = dateofbirth;
		this.telephoneNo = telephoneNo;
		this.email = email;
		this.passportNo=passportNo;

	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	// For booking
	public Guest(String name, String email, String telephoneNo) {
		this.setName(name);
		this.telephoneNo = telephoneNo;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public MyDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(MyDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Guest [name=" + name + ", nationality=" + nationality + ", dateofbirth=" + dateofbirth
				+ ", telephoneNo=" + telephoneNo + ", email=" + email + "]";
	}

	

	
}
