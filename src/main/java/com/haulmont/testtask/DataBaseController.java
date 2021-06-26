package main.java.com.haulmont.testtask;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import main.java.com.haulmont.testtask.model.Client;
import main.java.com.haulmont.testtask.model.Credit;


public class DataBaseController {

	private Connection connection;
	
	public final String URL_CLIENTS = "jdbc:hsqldb:file:database/clientsTable";
	public final String URL_CREDITS = "jdbc:hsqldb:file:database/creditsTable";
	private final String USERNAME = "SA";
	private final String PASSWORD = "";
	
	
	public final String CLIENT_TABLE = "resources/clientsTable.sql";
	private final String INSERT_CLIENT = "resources/insertClient.sql";
	private final String UPDATE_CLIENT = "resources/updateClient.sql";
	private final String SELECT_CLIENT = "resources/selectClient.sql";
	private final String DELETE_CLIENT = "resources/deleteClient.sql";
	
	
	public final String CREDIT_TABLE = "resources/creditTable.sql";
	private final String INSERT_CREDIT = "resources/insertCredit.sql";
	private final String UPDATE_CREDIT = "resources/updateCredit.sql";
	private final String SELECT_CREDIT = "resources/selectCredit.sql";
	private final String DELETE_CREDIT = "resources/deleteCredit.sql";
	
	
	private boolean isConnected = false;
	private String objectType;
	
	
	
	/**
	 * ����� ��������� ���������� � �� � �������� �������
	 * @param URL - ����� ��
	 * @param sqlTable - ������ �� ������ �������� �������
	 */
	public void runDB(String URL, String sqlTable) {
	
		if(URL == URL_CLIENTS) {
			objectType = "��������";
		} else {
			objectType = "��������";
		}
		
		//�������� DB ��������
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			System.out.println("������ ��������");
		} catch (ClassNotFoundException e) {
			System.out.println("�� ������� ��������� �������!");
			System.out.println("ClassNotFoundException message: " + e.getMessage());
			System.out.println("ClassNotFoundException cause: " + e.getCause());
		}
		
		//���������� � ����� ������ ��������
		try {
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			isConnected = true;
			
			if(!connection.isClosed()) {
				System.out.println("���������� � �� " + objectType + " �����������");
			}
			
			Statement stm = connection.createStatement();
			stm.executeUpdate(readToString(sqlTable));
			
		} catch (SQLException e) {
			
			System.out.println("�� ������� ���������� ���������� � �� " + objectType + "!");
			System.out.println("SQLException message: " + e.getMessage());
			System.out.println("SQLException SQL state: " + e.getSQLState());
			System.out.println("SQLException SQL error code: " + e.getErrorCode());
			
		} 
	}
	
	/**
	 * ����� �������� ���������� � ��
	 */
	public void closeDB() {
		
		if(isConnected) {
			
			try {
				
				Statement stm = connection.createStatement();
				stm.execute("SHUTDOWN");
				connection.close();
				isConnected = false;
				System.out.println("���������� � �� " + objectType + " ������� �������.");
				System.out.println();
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ������� ���������� � �� " + objectType + "!");
				
				System.out.println("SQLException message: " + e.getMessage());
			}
			
		} else {
			
			System.out.println("���������� � �� " + objectType + "�� �����������!");
			
		}
	}
	
	
	
	/**
	 * �������� ������ {@link Client} � ��
	 * @param client - ������ �������
	 */
	public void insertObject(Client client) {
		
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(INSERT_CLIENT));
				
				connection.setAutoCommit(false);
				
				prpstm.setString(1, client.getId());
				prpstm.setString(2, client.getSurname());
				prpstm.setString(3, client.getName());
				prpstm.setString(4, client.getPatronymic());
				prpstm.setString(5, client.getPhoneNumber());
				prpstm.setString(6, client.getEmail());
				prpstm.setString(7, client.getPassportId());
				
				prpstm.addBatch();
				
				prpstm.executeBatch(); 
				
				connection.commit();
				
				System.out.println(Client.class.getSimpleName() + " " +  client.toString() +" ������� ��������");
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ���������� " + Client.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
				
			}
			
		} else {
			System.out.println("����������� ���������� � ��" + objectType);
			System.out.println();
		}
	}

	/**
	 * �������� ������ {@link Credit} � ��
	 * @param credit - ������ �������
	 */
	public void insertObject(Credit credit) {
		
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(INSERT_CREDIT));
				
				connection.setAutoCommit(false);
				
				prpstm.setString(1, credit.getClientID());
				prpstm.setDouble(2, credit.getCreditLimit());
				prpstm.setDouble(3, credit.getInterestRate());
				prpstm.setInt(4, credit.getCreditTerm());
				prpstm.setDate(5, Date.valueOf(credit.getStartDate()));
				
				prpstm.addBatch();
				
				prpstm.executeBatch(); 
				
				connection.commit();
				
				System.out.println(Credit.class.getSimpleName() + " " +  credit.toString() +" ������� ��������");
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ���������� " + Credit.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
			}
			
		} else {
			System.out.println("����������� ���������� � �� " + objectType);
		}
	}
	
	
	
	/**
	 * �������� ������ {@link Client} � ��
	 * @param client - ���������� ������ �������
	 */
	public void updateObject(Client client) {
		
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(UPDATE_CLIENT));
				
				prpstm.setString(1, client.getSurname());
				prpstm.setString(2, client.getName());
				prpstm.setString(3, client.getPatronymic());
				prpstm.setString(4, client.getPhoneNumber());
				prpstm.setString(5, client.getEmail());
				prpstm.setString(6, client.getPassportId());
				prpstm.setString(7, client.getId());
				
				prpstm.executeUpdate(); 
				
				System.out.println(Client.class.getSimpleName() + " ������� �������");
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ���������� " + Client.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
			}
			
		} else {
			System.out.println("����������� ���������� � ��" + objectType);
		}
	}
	
	
	/**
	 * �������� ������ {@link Credit}  ��
	 * @param credit - ���������� ������ �������
	 */
	public void updateObject(Credit credit) {
		
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(UPDATE_CREDIT));
				
				prpstm.setDouble(1, credit.getCreditLimit());
				prpstm.setDouble(2, credit.getInterestRate());
				prpstm.setInt(3, credit.getCreditTerm());
				prpstm.setString(4, credit.getClientID());
				
				prpstm.executeUpdate(); 
				
				System.out.println(Credit.class.getSimpleName() + " ������� �������");
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ���������� " + Credit.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
			}
			
		} else {
			System.out.println("����������� ���������� � �� " + objectType);
		}
	}
	
	
	
	/**
	 * �������� ������ {@link Client} �� ��
	 * @param id - ID �������
	 * @return - ������ {@link Client}
	 */
	public Client selectClient(String id) {
		
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(SELECT_CLIENT));
				
				prpstm.setString(1, id);
				
				ResultSet rset = prpstm.executeQuery();
				
				if(rset.next()) {
					Client client = new Client(rset.getString(1), 
						   				  	   rset.getString(2), 
						   				  	   rset.getString(3), 
						   				  	   rset.getString(4), 
						   				  	   rset.getString(5),
						   				  	   rset.getString(6),
						   				  	   rset.getString(7));
				  				
					return client;
				} else {
					return null;
				}
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ��������� " + Client.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
				
				return null;
			}
			
		} else {
			System.out.println("����������� ���������� � �� " + objectType);
			return null;
		}
		
	}
	
	/**
	 * �������� ������ {@link Credit} �� ��
	 * @param clientID - ID �������
	 * @return - ������ {@link Credit}
	 */
	public Credit selectCredit(String clientID) {
		
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(SELECT_CREDIT));
				
				prpstm.setString(1, clientID);
				
				ResultSet rset = prpstm.executeQuery();
				
				if(rset.next()) {
					Credit credit = new Credit(rset.getString(1), 
						   				   	   rset.getDouble(2), 
						   				   	   rset.getDouble(3), 
						   				   	   rset.getInt(4), 
						   				   	   rset.getDate(5).toLocalDate());
				  				   
					return credit;
				} else {
					return null;
				}
				
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ��������� " + Credit.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
				
				return null;
			}
			
		} else {
			System.out.println("����������� ���������� � �� " + objectType);
			return null;
		}
	}
	
	
	
	/**
	 * ������� ������ {@link Client} �� ��
	 * @param client
	 */
	public void deleteObject(Client client) {
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(DELETE_CLIENT));
				
				connection.setAutoCommit(false);
				
				prpstm.setString(1, client.getId());
				
				prpstm.executeUpdate();
				
				connection.commit();
				
				System.out.println(Client.class.getSimpleName() + " " +  client.toString() +" ������� �����");
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ���������� " + Client.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
			}
			
		} else {
			System.out.println("����������� ���������� � ��" + objectType);
		}
	}

	/**
	 * ������� ������ {@link Credit} �� ��
	 * @param credit
	 */
	public void deleteObject(Credit credit) {
		if(isConnected) {
			
			try {
				
				PreparedStatement prpstm = connection.prepareStatement(readToString(DELETE_CREDIT));
				
				connection.setAutoCommit(false);
				
				prpstm.setString(1, credit.getClientID());
				
				prpstm.executeUpdate();
				
				connection.commit();
				
				System.out.println(Credit.class.getSimpleName() + " " +  credit.toString() +" ������� �����");
				
			} catch (SQLException e) {
				
				System.out.println("�� ������� ��������� ������ ���������� " + Credit.class.getSimpleName());
				System.out.println("SQLException message: " + e.getMessage());
				System.out.println("SQLException SQL state: " + e.getSQLState());
				System.out.println("SQLException SQL error code: " + e.getErrorCode());
			}
			
		} else {
			System.out.println("����������� ���������� � ��" + objectType);
			System.out.println();
		}
	}
	
	
	
	/**
	 * ���������� ������ �������� {@link Client} �� ��
	 * @return - ������ ��������
	 */
	public List<Client> getClients() {
		
		List<Client> clients = new ArrayList<>();
		
		try {
			
			ResultSet rset = connection.prepareStatement("SELECT * FROM clientsTable").executeQuery();
			
			while(rset.next()) {
				clients.add(new Client(rset.getString(1), 
									   rset.getString(2), 
									   rset.getString(3), 
									   rset.getString(4), 
									   rset.getString(5),
									   rset.getString(6),
									   rset.getString(7)));
			}
			
		} catch (SQLException e) {
			
			System.out.println("�� ������� ��������� ������ ��������� ������ " + Client.class.getSimpleName() + ". ��������� ������ ������.");
			System.out.println("SQLException message: " + e.getMessage());
			System.out.println("SQLException SQL state: " + e.getSQLState());
			System.out.println("SQLException SQL error code: " + e.getErrorCode());
			
		}
		
		return clients;
		
	}

	/**
	 * ���������� ������� �������� {@link Credit} �� ��
	 * @return - ������ ��������
	 */
	public List<Credit> getCredits() {
		
		List<Credit> credits = new ArrayList<>();
		
		try {
			
			ResultSet rset = connection.prepareStatement("SELECT * FROM creditsTable").executeQuery();
			
			while(rset.next()) {
				credits.add(new Credit(rset.getString(1), 
									   rset.getDouble(2), 
									   rset.getDouble(3), 
									   rset.getInt(4), 
									   rset.getDate(5).toLocalDate()));
			}
			
		} catch (SQLException e) {
			
			System.out.println("�� ������� ��������� ������ ��������� ������ " + Credit.class.getSimpleName() + ". ��������� ������ ������.");
			System.out.println("SQLException message: " + e.getMessage());
			System.out.println("SQLException SQL state: " + e.getSQLState());
			System.out.println("SQLException SQL error code: " + e.getErrorCode());
			
		}
		
		return credits;
		
	}

	
	
	/**
	 * ����� ���������� ����� � ������
	 * @param filePath - ����� � �����
	 * @return - 
	 */
	private String readToString(String filePath) {

		File file = new File(filePath);
		
		String string;
		
		try {
			
			string = FileUtils.readFileToString(file, "utf-8");
			return string;
			
		} catch (IOException e) {
			
			System.out.println("���������� ����� �� �������, ���������� ������ ������!");
			System.out.println("IOException message: " + e.getMessage());
			System.out.println("IOException cause: " + e.getCause());
			return "";
			
		}
	}
}
