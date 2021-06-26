package main.java.com.haulmont.testtask.model;

import java.time.LocalDate;

/**
 * Класс Credit хранит в себе информацию по кредиту:
 * 
 * @author Климашин С.
 *
 */
public class Credit {

	private String clientID;
	private double creditLimit;
	private double interestRate;
	private int creditTerm;
	private LocalDate startDate;

	
	
	/**
	 * Конструктор объекта
	 * @param creditLimit - лимит кредитования
	 * @param interestRate - проценстная ставка
	 * @param creditTerm - срок, на который оформляется кредит
	 * @param clientID - пресональный ID клиента, на которого оформлен кредит
	 */
	public Credit(String clientID, double creditLimit, double interestRate, int creditTerm) {
		this.clientID = clientID;
		this.creditLimit = creditLimit;
		this.interestRate = interestRate;
		this.creditTerm = creditTerm;
		startDate = LocalDate.now();
		
	}

	/**
	 * Конструктор для объекта импортируемого из БД
	 * @param creditLimit - лимит кредитования
	 * @param interestRate - проценстная ставка
	 * @param creditTerm - срок, на который оформляется кредит
	 * @param clientID - пресональный ID клиента, на которого оформлен кредит
	 * @param startDate - дата оформления кредита
	 */
	public Credit(String clientID, double creditLimit, double interestRate, int creditTerm, LocalDate startDate) {
		this.clientID = clientID;
		this.creditLimit = creditLimit;
		this.interestRate = interestRate;
		this.creditTerm = creditTerm;
		this.startDate = startDate;
		
	}
	
	
	
	/**
	 * Метод подсчёта суммы кредита
	 * @return - сумма кредита
	 */
	public double creditAmount() {
		return creditLimit * (1 + (interestRate / 100) );
	}
	
	
	
	@Override
	public String toString() {
		return "[Лимит по кредиту = " + creditLimit + ", Процентная ставка = " + interestRate
				+ ", Срок кредита = " + creditTerm + ", Дата оформления " + startDate + ", Сумма выплаты = " + String.format("%.2f", creditAmount())
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credit other = (Credit) obj;
		if (clientID == null) {
			if (other.clientID != null)
				return false;
		} else if (!clientID.equals(other.clientID))
			return false;
		if (Double.doubleToLongBits(creditLimit) != Double.doubleToLongBits(other.creditLimit))
			return false;
		if (creditTerm != other.creditTerm)
			return false;
		if (Double.doubleToLongBits(interestRate) != Double.doubleToLongBits(other.interestRate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	
	
	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public int getCreditTerm() {
		return creditTerm;
	}
	
	public void setCreditTerm(int creditTerm) {
		this.creditTerm = creditTerm;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public String getClientID() {
		return clientID;
	}
}
