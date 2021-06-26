package main.java.com.haulmont.testtask.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.haulmont.testtask.DataBaseController;
import main.java.com.haulmont.testtask.Main;
import main.java.com.haulmont.testtask.model.Bank;
import main.java.com.haulmont.testtask.model.Client;
import main.java.com.haulmont.testtask.model.Credit;

public class DataBaseTableController {


	ObservableList<Client> clients;
	
    @FXML
    private TableView<Client> dataBaseTable;

    @FXML
    private TableColumn<Client, String> surnameColumn;
    
    @FXML
    private TableColumn<Client, String> nameColumn;
    
    @FXML
    private TableColumn<Client, String> patronymicColumn;
    
    @FXML
    private TableColumn<Client, String> phoneNumberColumn;
    
    @FXML
    private TableColumn<Client, String> emailColumn;
    
    @FXML
    private TableColumn<Client, String> passportIDColumn;
    
    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button exitButton;
	
    @FXML
    private Button showButton;
    
    
    
    @FXML
    private void initialize() {
    	
    	DataBaseController clientController = new DataBaseController();
    	clientController.runDB(clientController.URL_CLIENTS, clientController.CLIENT_TABLE);
    	Bank.clientList.clear();
    	Bank.clientList.addAll(clientController.getClients());
    	clientController.closeDB();
    	
    	DataBaseController creditController = new DataBaseController(); 	
    	creditController.runDB(creditController.URL_CREDITS, creditController.CREDIT_TABLE);
    	Bank.creditList.clear();
    	Bank.creditList.addAll(creditController.getCredits());
    	creditController.closeDB();
    	
    	clients = FXCollections.observableList(Bank.clientList);
    	
    	surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    	patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
    	phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    	emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    	passportIDColumn.setCellValueFactory(new PropertyValueFactory<>("passportId"));
    	
    	dataBaseTable.getItems().addAll(clients);
    	
    	TableSelectionModel<Client> selectionModel = dataBaseTable.getSelectionModel();
    	selectionModel.setSelectionMode(SelectionMode.SINGLE);
    }
    

    
    @FXML
    public void addButtonAction(ActionEvent event) {
    	try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CreditOffer.fxml"));
			AnchorPane creditOffer = (AnchorPane) loader.load();
			
			Stage creditOfferStage = new Stage();
			
			creditOfferStage.setTitle("Окно формирования кредитного предложения");
			creditOfferStage.setScene(new Scene(creditOffer));
			creditOfferStage.show();
			
		} catch(IOException e) {
			
			System.out.println("IOException message: " + e.getMessage());
			System.out.println("IOException cause: " + e.getCause());
		}
    	
    	Stage stage = (Stage) addButton.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    public void updateButton(ActionEvent event) {
    	
    	try {
			
    		TableSelectionModel<Client> selectionModel = dataBaseTable.getSelectionModel();
        	
        	Client selectedClient = selectionModel.getSelectedItem();
        	Main.setUpdatebleClient(selectedClient);
        	
        	DataBaseController creditController = new DataBaseController();
    		creditController.runDB(creditController.URL_CREDITS, creditController.CREDIT_TABLE);
    		Credit selectedCredit = creditController.selectCredit(selectedClient.getId());
    		Main.setUpdatebleCredit(selectedCredit);
    		creditController.closeDB();
    		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CreditOfferUpdate.fxml"));
			AnchorPane creditOffer = (AnchorPane) loader.load();
			
			Stage creditOfferStage = new Stage();
			
			creditOfferStage.setTitle("Окно редактирования кредитного предложения");
			creditOfferStage.setScene(new Scene(creditOffer));
			creditOfferStage.show();
			
		} catch(IOException e) {
			
			System.out.println("IOException message: " + e.getMessage());
			System.out.println("IOException cause: " + e.getCause());
		}
    	
    	Stage stage = (Stage) addButton.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    public void deleteButtonAction(ActionEvent event) {
    	
    	TableSelectionModel<Client> selectionModel = dataBaseTable.getSelectionModel();
    	
    	Client selectedClient = selectionModel.getSelectedItem();
    	
    	/**
    	 * Удаляем клиента из БД
    	 */
    	DataBaseController clientController = new DataBaseController();
		clientController.runDB(clientController.URL_CLIENTS, clientController.CLIENT_TABLE);
		clientController.deleteObject(selectedClient);
		System.out.println("Клиент найден и удалён из общей базы");
		clientController.closeDB();
		
		if(Bank.clientList.contains(selectedClient)) {
			Bank.clientList.remove(selectedClient);
			System.out.println("Клиент найден и удалён из локальной базы");
		} else {
			System.out.println("Клиент не найден в локальной базе");
		}

		dataBaseTable.getItems().remove(selectionModel.getSelectedIndex());
		
		/**
		 * Удаляем кредит из БД
		 */
		DataBaseController creditController = new DataBaseController();
		creditController.runDB(creditController.URL_CREDITS, creditController.CREDIT_TABLE);
		
		Credit selectedCredit = creditController.selectCredit(selectedClient.getId());
		
		creditController.deleteObject(selectedCredit);
		System.out.println("Кредит найден и удалён из общей базы");
		creditController.closeDB();
		
		if(Bank.creditList.contains(selectedCredit)) {
			Bank.creditList.remove(selectedCredit);
			System.out.println("Кредит найден и удалён из локальной базы");
		} else {
			System.out.println("Кредит не найден в локальной базе");
		}
		
    }

    @FXML
    public void exitButtonAction(ActionEvent event) {
    	Stage stage = (Stage) exitButton.getScene().getWindow();
    	stage.close();
    }
    
    public void showButtonAction(ActionEvent event) {
    	
    	try {
			
    		TableSelectionModel<Client> selectionModel = dataBaseTable.getSelectionModel();
        	
        	Client selectedClient = selectionModel.getSelectedItem();
        	Main.setUpdatebleClient(selectedClient);
        	
        	DataBaseController creditController = new DataBaseController();
    		creditController.runDB(creditController.URL_CREDITS, creditController.CREDIT_TABLE);
    		Credit selectedCredit = creditController.selectCredit(selectedClient.getId());
    		Main.setUpdatebleCredit(selectedCredit);
    		creditController.closeDB();
    		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CreditOfferShow.fxml"));
			AnchorPane creditOffer = (AnchorPane) loader.load();
			
			Stage creditOfferStage = new Stage();
			
			creditOfferStage.setTitle("Окно просмотра кредитного предложения");
			creditOfferStage.setScene(new Scene(creditOffer));
			creditOfferStage.show();
			
		} catch(IOException e) {
			
			System.out.println("IOException message: " + e.getMessage());
			System.out.println("IOException cause: " + e.getCause());
			
		}
    
    }
    
}
