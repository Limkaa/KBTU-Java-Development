package systemLogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationOfDatabase {
	
	// Process of data deserialization from file 
	public static boolean deserializeData() {
		try {
			FileInputStream fileInputStream = new FileInputStream("uninetData.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			
			objectInputStream.readObject();
			
			fileInputStream.close();
			objectInputStream.close();
			return true;
			
		} catch (ClassNotFoundException exception) {
			return false;
		} catch (FileNotFoundException exception) {
			return false;
		} catch (IOException exception) {
			return false;
		} 
	}
	
	// Process of data serialization to file
	public static void serializeData() {
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("uninetData.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	
			objectOutputStream.writeObject(Database.getInstance());
			
			fileOutputStream.close();
			objectOutputStream.close();
		}
		catch (IOException exception) {
			System.exit(0);
		}
	}
}
