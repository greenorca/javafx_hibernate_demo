/**
 * 
 */
package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author sven
 * class extents java.util.Properties only to incorporate the file where the properties are stored 
 */
public class MyCustomProps extends Properties {
	
	private String propertiesFilePath;
	
	/**
	 * ctor
	 * @param filePath where the properties are stored
	 */
	public MyCustomProps(String filePath) {
		super();
		this.propertiesFilePath = filePath;
	}
	
	public void loadProperties() {
		try {
			this.load(new FileInputStream(new File(propertiesFilePath)));
		} catch(Exception e) {
			System.out.println("died loading properties");
		}
	}
	
	public void save() {
		try {
			this.store(new FileOutputStream(new File(propertiesFilePath)),"");
		} catch(Exception e) {
			System.out.println("died saving properties");
		}
	}

}
