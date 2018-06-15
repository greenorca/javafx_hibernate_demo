package application.view;

import application.model.Person;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.control.DatePicker;

public class EditViewController {
	@FXML
	private TextField txtVorname;
	@FXML
	private TextField txtNachname;
	@FXML
	private TextField txtStrasse;
	@FXML
	private TextField txtPlz;
	@FXML
	private TextField txtOrt;
	@FXML
	private TextField txtEmail;
	@FXML
	private Label chkBoxNewsletter;
	@FXML
	private DatePicker txtKundeSeit;
	
	private Person currentPerson;
	private Stage myStage;
	private boolean isSaved = false;
	
	public void setPerson(Person person) {
		// TODO Auto-generated method stub
		currentPerson = person;
		txtVorname.setText(currentPerson.vornameProperty().get());
		txtNachname.setText(currentPerson.nachnameProperty().get());
		txtStrasse.setText(currentPerson.strasseProperty().get());
		txtPlz.setText(currentPerson.plzProperty().get());
		txtOrt.setText(currentPerson.ortProperty().get());
	}
	
	/**
	 * speichert aktuelle textFiled-Werte in person-Objekt 
	 * und schliesst das Fenster
	 */
	@FXML public void speichern() {
		currentPerson.vornameProperty().set(txtVorname.getText());
		currentPerson.nachnameProperty().set(txtNachname.getText());
		currentPerson.strasseProperty().set(txtStrasse.getText());
		currentPerson.plzProperty().set(txtPlz.getText());
		currentPerson.ortProperty().set(txtOrt.getText());
		isSaved = true;
		//TODO: Fenster schliessen...
		myStage.close();
	}
	
	@FXML public void abbrechen() {
		myStage.close();
	}

	/**
	 * @return the myStage
	 */
	public Stage getMyStage() {
		return myStage;
	}

	/**
	 * @param myStage the myStage to set
	 */
	public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}

	public boolean isSaved() {
		// TODO Auto-generated method stub
		return isSaved;
	}

}
