package main.java.com.haulmont.testtask;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.haulmont.testtask.model.Bank;
import main.java.com.haulmont.testtask.model.Client;
import main.java.com.haulmont.testtask.model.Credit;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Stage mainOverviewStage;
	private static Client updatebleClient;
	private static Credit updatebleCredit;
	private static Bank bank = new Bank();
	
	@Override
	public void start(Stage primaryStage) {
		this.mainOverviewStage = primaryStage;
		
		showMainOverview();
		
	}
	
	public void showMainOverview() { 
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainOverview.fxml"));
			AnchorPane mainOverview = (AnchorPane) loader.load();
	
			mainOverviewStage.setTitle("Тестовое задание");
			mainOverviewStage.setScene(new Scene(mainOverview));
			mainOverviewStage.show();
			
		} catch(IOException e) {
			
			System.out.println("IOException message: " + e.getMessage());
			System.out.println("IOException cause: " + e.getCause());
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	public static Bank getBank() {
		return bank;
	}
	
	public static void setUpdatebleClient(Client client) {
		updatebleClient = client;
	}
	
	public static Client getUpdatebleClient() {
		return updatebleClient;
	}
	
	public static void setUpdatebleCredit(Credit credit) {
		updatebleCredit = credit;
	}
	
	public static Credit getUpdatebleCredit() {
		return updatebleCredit;
	}
}
