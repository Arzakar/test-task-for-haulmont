package main.java.com.haulmont.testtask.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.haulmont.testtask.Main;
import main.java.com.haulmont.testtask.model.Client;
import main.java.com.haulmont.testtask.model.Credit;
import main.java.com.haulmont.testtask.model.CreditOffer;


public class CreditOfferShowController {
	
	ObservableList<LocalDate> dates;
	
	Client localClient;
	Credit localCredit;
	CreditOffer localCreditOffer;
	
	@FXML
    private TextField surnameTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private TextField numberPhoneTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passportIDTextField;

    @FXML
    private TextField creditLimitTextField;

    @FXML
    private TextField interestRateTextField;

    @FXML
    private TextField termCreditTextField;

    @FXML
    private Label dateLabel;

    @FXML
    private Label creditSumLabel;
    
    @FXML
    private Label paymentAmoutLabel;

    @FXML
    private Label repaymentCreditBodyLabel;

    @FXML
    private Label repaymentCreditInterestLabel;

    @FXML
    private ComboBox<LocalDate> dateComboBox;

    @FXML
    private Button addButtonAction;

    @FXML
    private Button cancelButtonAction;
    
    
    
    @FXML
    private void initialize() {
    	Client prevClient = Main.getUpdatebleClient();
    	Credit prevCredit = Main.getUpdatebleCredit();
    	
    	surnameTextField.setText(prevClient.getSurname());
    	nameTextField.setText(prevClient.getName());
    	patronymicTextField.setText(prevClient.getPatronymic());
    	numberPhoneTextField.setText(prevClient.getPhoneNumber());
    	emailTextField.setText(prevClient.getEmail());
    	passportIDTextField.setText(prevClient.getPassportId());
    	
    	creditLimitTextField.setText(String.format("%.0f", prevCredit.getCreditLimit()));
    	interestRateTextField.setText(String.format("%.0f", prevCredit.getInterestRate()));
    	termCreditTextField.setText(String.valueOf(prevCredit.getCreditTerm()));
    }
    
    
    
    @FXML
    public void cancelButtonAction(ActionEvent event) {
    	
    	Stage stage = (Stage) cancelButtonAction.getScene().getWindow();
    	stage.close();
    	
    }
    
    

    @FXML
    public void updateCreditGraphic() {
    	
    	if(checkFields()) {
    		localClient = new Client(surnameTextField.getText(), 
									 nameTextField.getText(), 
									 patronymicTextField.getText(),
									 numberPhoneTextField.getText(),
									 emailTextField.getText(),
									 passportIDTextField.getText());

    		localCredit = new Credit(localClient.getId(),
									 Double.parseDouble(creditLimitTextField.getText()),
									 Double.parseDouble(interestRateTextField.getText()),
									 Integer.parseInt(termCreditTextField.getText()));

    		localCreditOffer = new CreditOffer(localClient, localCredit);
    		
    		creditSumLabel.setText(String.format("%.2f", localCreditOffer.getCreditAmount()));
    		
    		
    		dates = FXCollections.observableList(localCreditOffer.getDate());
    		
    		dateComboBox.setItems(dates);
    	
    	}
    }
    
    @FXML
    public void dateComboBoxSetOnAction(ActionEvent event) {
    	
    	if(checkFields()) {
    		int index = localCreditOffer.getDate().indexOf(dateComboBox.getValue());
    		
    		if(index >= 0) {
    		
    			dateLabel.setText(String.valueOf(localCreditOffer.getDate().get(index)));
    			paymentAmoutLabel.setText(String.format("%.2f", localCreditOffer.getPaymentAmount().get(index)));
    			repaymentCreditBodyLabel.setText(String.format("%.2f", localCreditOffer.getRepaymentCreditBody().get(index)));
    			repaymentCreditInterestLabel.setText(String.format("%.2f", localCreditOffer.getRepaymentCreditInterest().get(index)));
    		
    		} else {
    			
    			dateLabel.setText("---");
    			paymentAmoutLabel.setText("---");
    			repaymentCreditBodyLabel.setText("---");
    			repaymentCreditInterestLabel.setText("---");
    			
    		}
    		
    	}
    		
    }
    
    
    
    /**
     * Метод проверки вводимых данных в {@link TextFiels}
     * @return - true / false, если данные введены правильно / неправильно
     */
    public boolean checkFields() {
		
    	/* Проверка ввода данных */
    	List<Boolean> checkList = new ArrayList<>();
    	
    	if(surnameTextField.getText().matches("[а-яА-Я]+") & surnameTextField.getText().length() <= 20) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		surnameTextField.setText("Слишком большая длина или содержит неверные символы");
    	}
    	
    	if(nameTextField.getText().matches("[а-яА-Я]+") & nameTextField.getText().length() <= 20) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		nameTextField.setText("Слишком большая длина или содержит неверные символы");
    	}
    	
    	if(patronymicTextField.getText().matches("[а-яА-Я]+") & patronymicTextField.getText().length() <= 20) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		patronymicTextField.setText("Слишком большая длина или содержит неверные символы");
    	}
    	
    	if(numberPhoneTextField.getText().matches("[0-9]+") & numberPhoneTextField.getText().length() == 11) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		numberPhoneTextField.setText("Некорректный номер");
    	}
    	
    	if(emailTextField.getText().length() <= 40) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		emailTextField.setText("Слишком большой email");
    	}
    	
    	if(passportIDTextField.getText().matches("[0-9]+") & passportIDTextField.getText().length() == 6) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		passportIDTextField.setText("Некорректный номер паспорта");
    	}
    	
    	if(creditLimitTextField.getText().matches("[0-9]+") & creditLimitTextField.getText().length() <= 7) {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		creditLimitTextField.setText("Некорректные данные");
    	}
    	
    	if(interestRateTextField.getText().matches("[0-9]+") & interestRateTextField.getText().length() <= 2 & interestRateTextField.getText() != "0") {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		interestRateTextField.setText("Неверный процент");
    	}
    	
    	if(termCreditTextField.getText().matches("[0-9]+") & termCreditTextField.getText().length() <= 2 & termCreditTextField.getText() != "0") {
    		checkList.add(true);
    	} else {
    		checkList.add(false);
    		termCreditTextField.setText("Неверный срок");
    	}
    	
    	
    	if(!checkList.contains(false)) {
    		return true;
    	} else {
        	return false;
    	}
    }
}
