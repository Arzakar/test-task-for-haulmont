package main.java.com.haulmont.testtask.model;

import java.util.ArrayList;

public class Bank {
		
	public static ArrayList<Client> clientList;
	public static ArrayList<Credit> creditList;
	
	/**
	 * ����������� ��� ����������, ��������� ������ ������ {@link Client} � {@link Credit}
	 */
	public Bank() {
		clientList = new ArrayList<>();
		creditList = new ArrayList<>();
	}
	
	
	

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public ArrayList<Credit> getCreditList() {
		return creditList;
	}
	
}
