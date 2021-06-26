package main.java.com.haulmont.testtask.model;

import java.util.UUID;

/**
 * Класс Client хранит в себе информацию о клиенте
 * 
 * @author Климашин С.
 *
 */

public class Client {
	
	private String id;
	private String surname;
	private String name;
	private String patronymic;
	private String phoneNumber;
	private String email;
	private String passportId;
	
	
	
	/**
	 * Конструктор для нового объекта
	 * @param surname - фамилия
	 * @param name - имя
	 * @param patronymic - отчество
	 * @param phoneNumber - номер телефона
	 * @param email - электронная почта
	 * @param passportId - номер паспорта
	 */
	public Client(String surname, String name, String patronymic, String phoneNumber, String email, String passportId) {
		id = UUID.randomUUID().toString();
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.passportId = passportId;
	}

	/**
	 * Конструктор для объекта импортируемого из БД
	 * @param id - UUID в формате строки
	 * @param surname - фамилия
	 * @param name - имя
	 * @param patronymic - отчество
	 * @param phoneNumber - номер телефона
	 * @param email - электронная почта
	 * @param passportId - номер паспорта
	 */
	public Client(String id, String surname, String name, String patronymic, String phoneNumber, String email, String passportId) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.passportId = passportId;
	}



	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Client other = (Client) obj;
		
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passportId == null) {
			if (other.passportId != null)
				return false;
		} else if (!passportId.equals(other.passportId))
			return false;
		if (patronymic == null) {
			if (other.patronymic != null)
				return false;
		} else if (!patronymic.equals(other.patronymic))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "[" + surname + " " + name + " " + patronymic + ", "
				+ phoneNumber + ", " + email + ", " + passportId + "]";
	}
	
	
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassportId() {
		return passportId;
	}
	
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
}
