package application.model;

import javax.swing.JOptionPane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * basic data storage class with FX properties
 * @author sven
 *
 */

@Entity
@Table(name = "Person")
@Access(AccessType.PROPERTY)
public class Person {

	public static void main(String[] args) {
		Person hans = new Person(123, "Hans","Heiri","Humbug", "123", "Hasendorf", 1000);
		
		hans.salaryProperty().addListener(
				(obst, alt, neu)->{
					System.out.println("salary changed: "+neu);
					JOptionPane.showMessageDialog(null, "neues Gehalt: "+neu , "Gehaltsänderung-Info", 
							JOptionPane.INFORMATION_MESSAGE);
				}
		);
		
		System.out.println(hans);
		hans.salaryProperty().set(1500);
		System.out.println(hans);
	}
	
    private LongProperty id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
    	if (id==null)
    		id=new SimpleLongProperty();
    	return id.get();
    }
    
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
    	if (this.id==null)
    		this.id=new SimpleLongProperty();
		this.id.setValue(id);
	}
	
	private StringProperty vorname;
	private StringProperty nachname;
	private StringProperty strasse;
	private StringProperty plz;
	private StringProperty ort;
	private IntegerProperty salary;
	
	public Person(long id, String vorname, String nachname, String strasse, String plz,
			String ort, int salary) {
		this.setId(id);
		vornameProperty().set(vorname);
		nachnameProperty().set(nachname);
		strasseProperty().set(strasse);
		plzProperty().set(plz);
		ortProperty().set(ort);
		salaryProperty().set(salary);
	}
	public StringProperty plzProperty() {
		if (plz == null) {
			plz = new SimpleStringProperty();
		}
		return plz;
	}

	@Column(name = "plz")
	public String getPlz() {
		return plzProperty().get();
	}

	public void setPlz(String plz) {
		plzProperty().set(plz);
	}

	public Person(long id, String vorname, String nachname) {
		this.setId(id);
		vornameProperty().set(vorname);
		nachnameProperty().set(nachname);
	}
	
	public Person() {
		
	}
	
	public String toString() {
		return nachnameProperty().get()+","+vornameProperty().get();
	}
	
	public void setVorname(String vorname) {
		vornameProperty().set(vorname);
	}

	public void setNachname(String nachname) {
		this.nachnameProperty().set(nachname);
	}

	public void setStrasse(String strasse) {
		strasseProperty().set(strasse);
	}

	public void setOrt(String ort) {
		ortProperty().set(ort);
	}

	public void setSalary(Integer salary) {
		salaryProperty().set(salary);
	}

	@Column(name = "vorname")
	public String getVorname() {
		return this.vornameProperty().get();
	}
	
	public StringProperty vornameProperty() {
		if (vorname==null) {
			vorname = new SimpleStringProperty();
		}
		return this.vorname;
	}
	
	@Column(name = "nachname")
	public String getNachname() {
		return this.nachnameProperty().get();
	}

	public StringProperty nachnameProperty() {
		if (nachname==null)
			nachname = new SimpleStringProperty();
		return this.nachname;
	}
	
	@Column(name = "strasse")
	public String getStrasse() {
		return this.strasseProperty().get();
	}

	
	public StringProperty strasseProperty() {
		if (strasse==null)
			strasse = new SimpleStringProperty();
		return this.strasse;
	}
	
	@Column(name = "ort")
	public String getOrt() {
		return this.ortProperty().get();
	}

	public StringProperty ortProperty() {
		if (ort==null)
			ort = new SimpleStringProperty();
		return this.ort;
	}
	
	@Column(name = "salary")
	public int getSalary() {
		return this.salaryProperty().get();
	}

	public IntegerProperty salaryProperty() {
		if (salary==null)
			salary = new SimpleIntegerProperty();
		return this.salary;
	}
		

}
