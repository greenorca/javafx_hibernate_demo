package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;

import application.MyCustomProps;
import application.model.Person;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainViewController implements Initializable {
	@FXML
	private TableView<Person> tableView;
	@FXML
	private TableColumn<Person, String> colVorname;
	@FXML
	private TableColumn<Person, String> colNachname;
	@FXML BorderPane basePane;
	@FXML GridPane centerPane;
	@FXML
	private Button btnNeu, btnLoeschen, btnBearbeiten;
	@FXML
	private Label lblVorname, lblNachname, lblStrasse, lblPlz, lblOrt;
	private Stage primaryStage;

	int cntNewPersonClicks = 0;
	private Person currentPerson;

	private Configuration cfg;
	// private final SessionFactory sf = cfg.buildSessionFactory();
	private StandardServiceRegistry serviceRegistry;
	private SessionFactory sf;

	private MyCustomProps props;
	
	public void setProperties(MyCustomProps props) {
		this.props = props;
	}
	
	/**
	 * starts a new view to edit a persons attributes
	 * @param person the person to edit 
	 * @return true, if save button was pressed in EditView
	 */
	private boolean runEditController(Person person) {

		// erwecke EditView zum Leben
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditView.fxml"));
		try {
			Parent node = loader.load();
			EditViewController editController = loader.getController();
			// zu editierende Person übergeben
			editController.setPerson(person);

			Scene scene = new Scene(node);

			Stage editStage = new Stage();
			editStage.setScene(scene);

			editController.setMyStage(editStage);

			editStage.initOwner(primaryStage);
			editStage.initModality(Modality.APPLICATION_MODAL);

			editStage.showAndWait();
			// wurde "Speichern"-Knopf gedrückt im EditController???
			if (editController.isSaved()) {
				return true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cfg = new Configuration().configure("hibernate.cfg.xml");
		// it is important to configure ech JPA related class
		cfg.configure().addAnnotatedClass(Person.class);

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		try {
		sf = cfg.buildSessionFactory(serviceRegistry);
		} catch(ServiceException sEx) {
			Alert al = new Alert(AlertType.ERROR, "Database configuration messed up. Please setup DB and/or fix settings in hibernate.cfg.xml", ButtonType.FINISH);
			al.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			al.showAndWait();
			
		}
		
		Session session = sf.openSession();
		tableView.getItems().addAll((List<Person>)session.createQuery("FROM Person").getResultList());

		colVorname.setCellValueFactory(value -> value.getValue().vornameProperty());

		System.out.println(colVorname.getOnEditStart());
		System.out.println("colVorname.CellFactory: " + colVorname.getCellFactory());

		colVorname.setEditable(true);
		colVorname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
		colVorname.setOnEditCommit(onVornameEditCommit);

		colNachname.setCellValueFactory(value -> value.getValue().nachnameProperty());
		colNachname.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
		colNachname.setEditable(true);
		colNachname.setOnEditCommit(onNachnameEditCommit);

		tableView.getSelectionModel().selectedItemProperty().
		addListener((observable, alterWert, neuerWert) -> {
			currentPerson = neuerWert;
			if (neuerWert == null)
				return;
			// System.out.println(alterWert+"::"+neuerWert);
			updateLabels();
		});

		colVorname.prefWidthProperty().bind(tableView.widthProperty().divide(2));
		colNachname.prefWidthProperty().bind(tableView.widthProperty().divide(2));
		
		tableView.prefWidthProperty().bind(basePane.widthProperty().divide(2));
		centerPane.prefWidthProperty().bind(basePane.widthProperty().divide(2));

	}

	/**
	 * sorgt dafür, dass alle Labels ein Update erhalten
	 */
	private void updateLabels() {
		lblVorname.setText(currentPerson.vornameProperty().get());
		
		currentPerson.vornameProperty().addListener((observableU, oldValueU, newValueU)->{
			lblVorname.setText(newValueU);
		});
		
		lblNachname.setText(currentPerson.nachnameProperty().get());
		currentPerson.nachnameProperty().addListener((observableU, oldValueU, newValueU)->{
			lblNachname.setText(newValueU);
		});
		
		
		lblStrasse.setText(currentPerson.strasseProperty().get());
		currentPerson.strasseProperty().addListener((observableU, oldValueU, newValueU)->{
			lblStrasse.setText(newValueU);
		});
		
		
		lblPlz.setText(currentPerson.plzProperty().get());
		currentPerson.plzProperty().addListener((observableU, oldValueU, newValueU)->{
			lblPlz.setText(newValueU);
		});
		
		lblOrt.setText(currentPerson.ortProperty().get());
		currentPerson.ortProperty().addListener((observableU, oldValueU, newValueU)->{
			lblOrt.setText(newValueU);
		});

	}

	/**
	 * responsible to table cell changes: Nachname
	 */
	EventHandler<CellEditEvent<Person, String>> onNachnameEditCommit = new EventHandler<CellEditEvent<Person, String>>() {
		@Override
		public void handle(CellEditEvent<Person, String> t) {
			System.out.println("CellEditCommit event catched");
			currentPerson = tableView.getSelectionModel().getSelectedItem();
			currentPerson.nachnameProperty().set(t.getNewValue());
			updateExistingPerson();
		}
	};

	/**
	 * responsible to table cell changes: Vorname
	 */
	EventHandler<CellEditEvent<Person, String>> onVornameEditCommit = new EventHandler<CellEditEvent<Person, String>>() {
		@Override
		public void handle(CellEditEvent<Person, String> t) {

			System.out.println("CellEditCommit event catched");
			currentPerson = tableView.getSelectionModel().getSelectedItem();
			currentPerson.vornameProperty().set(t.getNewValue());
			updateExistingPerson();
		}
	};
	
	/**
	 * stores a new person in DB
	 */
	private void storeNewPerson() {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(currentPerson);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * updates person in DB
	 */
	private void updateExistingPerson() {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(currentPerson);
		session.getTransaction().commit();
		session.close();
	}


	@FXML
	public void closeAction() {
		System.out.println("close request catched, cleaning up now...");
		
		if (props!=null) {
			try {
			props.setProperty("stageWidth", ""+primaryStage.getWidth());
			props.setProperty("stageHeight", ""+primaryStage.getHeight());
			props.setProperty("xPos", ""+primaryStage.getX());
			props.setProperty("yPos", ""+primaryStage.getY());
			
			props.save();
			
			} catch(Exception e) {
				System.out.println("could'nt save properties");
			}
		}
		
		try{
			sf.close();
			//configuration.
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		Platform.exit();
	}

	@FXML
	public void addNeuAction() {

		Person neuePerson = new Person();
		if (runEditController(neuePerson) == true) {
			// speichern
			tableView.getItems().add(neuePerson);
			currentPerson = neuePerson;
			storeNewPerson();
		}

	}

	@FXML
	public void bearbeitenAction() {
		// Alert alert = new Alert(AlertType.ERROR);
		// alert.setTitle("Fehler");
		// alert.setHeaderText("Noch nicht implementiert");
		// alert.setContentText("Diese Funktion wurde noch nicht implementiert. Bitte
		// überweisen Sie all Ihr Geld an die Entwickler.");
		// alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		// alert.initOwner(primaryStage);
		// alert.showAndWait();
		Person currentPerson = tableView.getSelectionModel().getSelectedItem();
		if (runEditController(currentPerson)==true) {
			updateExistingPerson();
		}
	}

	@FXML
	public void loeschenAction() {
		if (currentPerson != null) {
			tableView.getItems().remove(currentPerson);
			Session session = sf.openSession();
			session.beginTransaction();
			session.delete(currentPerson);
			session.getTransaction().commit();
			session.close();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Löschen nicht möglich");
			alert.setContentText("... solange kein Element ausgewählt wurde.");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		}
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param primaryStage
	 *            the primaryStage to set
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
