package main.java.com.haulmont.testtask.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreditOffer {

	private Client client;
	private Credit credit;
	private double creditAmount;
	
	private ArrayList<LocalDate> date;
	private ArrayList<Double> paymentAmount;
	private ArrayList<Double> repaymentCreditBody;
	private ArrayList<Double> repaymentCreditInterest;
	
	private String clientID;
	
	
	
	/**
	 * Конструктор объекта формирования кредитного предложения
	 * @param client - объект {@link Client}
	 * @param credit - объект {@link Credit}
	 */
	public CreditOffer(Client client, Credit credit) {
		this.client = client;
		this.credit = credit;
		creditAmount = credit.creditAmount();	
		
		/* Создаём график выплаты кредита */
		date = new ArrayList<>();
		
		LocalDate bufferDate = credit.getStartDate();
		
		for(int i = 0; i < credit.getCreditTerm(); i++) {
			bufferDate = bufferDate.plusMonths(1);
			date.add(bufferDate);
		}
		/* Конец блока */
		
		paymentAmount = new ArrayList<>();
		repaymentCreditBody = new ArrayList<>();
		repaymentCreditInterest = new ArrayList<>();
		
		for(int i = 0; i < date.size(); i++) {
			repaymentCreditBody.add(credit.getCreditLimit() / credit.getCreditTerm());
			repaymentCreditInterest.add( (creditAmount - credit.getCreditLimit()) / credit.getCreditTerm());
			paymentAmount.add(creditAmount / credit.getCreditTerm());
		}
	}

	
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	
	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	
	public ArrayList<LocalDate> getDate() {
		return date;
	}
	
	public void setDate(ArrayList<LocalDate> date) {
		this.date = date;
	}
	
	public ArrayList<Double> getPaymentAmount() {
		return paymentAmount;
	}
	
	public void setPaymentAmount(ArrayList<Double> paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public ArrayList<Double> getRepaymentCreditBody() {
		return repaymentCreditBody;
	}
	
	public void setRepaymentCreditBody(ArrayList<Double> repaymentCreditBody) {
		this.repaymentCreditBody = repaymentCreditBody;
	}
	
	public ArrayList<Double> getRepaymentCreditInterest() {
		return repaymentCreditInterest;
	}
	
	public void setRepaymentCreditInterest(ArrayList<Double> repaymentCreditInterest) {
		this.repaymentCreditInterest = repaymentCreditInterest;
	}
	
	public String getClientID() {
		return clientID;
	}
	
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
}
