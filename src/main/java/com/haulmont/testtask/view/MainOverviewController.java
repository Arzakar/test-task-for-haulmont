package main.java.com.haulmont.testtask.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.haulmont.testtask.Main;

public class MainOverviewController {

    @FXML
    private Button createCreditButton;

    @FXML
    private Button openDataBaseButton;
    
    
    
    @FXML
    public void openDataBaseButtonAction(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/DataBaseTable.fxml"));
			AnchorPane dataBaseTable = (AnchorPane) loader.load();
			
			Stage dataBaseStage = new Stage();
			
			dataBaseStage.setTitle("База данных");
			dataBaseStage.setScene(new Scene(dataBaseTable));
			dataBaseStage.show();

		} catch(IOException e) {
			
			System.out.println("IOException message: " + e.getMessage());
			System.out.println("IOException cause: " + e.getCause());
		}
    }
    
    @FXML
    public void createCreditButtonAction(ActionEvent event) {
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
    }
}
