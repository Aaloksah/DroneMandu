package model;

import java.io.Serializable;;

public class droneModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private static String fullName;
	private static String userName;
	private static String email;
	private static String password;
	private static String gender;
	private static String street;
	private static String phone;

	private static String province;
	private static String city;

	public droneModel() {
	}

	public droneModel(String fullName, String email, String password, String gender, String phone, String province,
			String city, String userName, String street) {
		super();
		this.fullName = fullName;
		this.street = street;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.province = province;
		this.city = city;
	}

	public static String getFullName() {
		return fullName;
	}

	public static void setFullName(String fullName) {
		droneModel.fullName = fullName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		droneModel.userName = userName;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		droneModel.email = email;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		droneModel.password = password;
	}

	public static String getGender() {
		return gender;
	}

	public static void setGender(String gender) {
		droneModel.gender = gender;
	}

	public static String getStreet() {
		return street;
	}

	public static void setStreet(String street) {
		droneModel.street = street;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		droneModel.phone = phone;
	}

	public static String getProvince() {
		return province;
	}

	public static void setProvince(String province) {
		droneModel.province = province;
	}

	public static String getCity() {
		return city;
	}

	public static void setCity(String city) {
		droneModel.city = city;
	}

}