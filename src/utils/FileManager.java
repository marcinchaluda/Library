package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.Library;

public class FileManager {

	// nazwa pliku zapisana w formie sta³ej jako objekt
	public static final String FILE_NAME = "Library.o";
	
	public void writeLibraryToFile(Library library){
		try(
		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(library);
		}catch (FileNotFoundException e){
			System.err.println("Nie odnaleziono pliku: " + FILE_NAME);
		}catch (IOException e){
			System.err.println("B³¹d podczas zapisu danych do pliku: " + FILE_NAME);
		}
	}
	
	public Library readLibraryFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		Library library = null;
		try(
		FileInputStream fis = new FileInputStream(FILE_NAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		){
			library = (Library) ois.readObject();
		}catch (FileNotFoundException e){
			System.err.println("Nie odnaleziono pliku: " + FILE_NAME);
			throw e;
		}catch (IOException e){
			System.err.println("B³¹d podczas zapisu danych do pliku: " + FILE_NAME);
			throw e;
		}catch (ClassNotFoundException e){
			System.err.println("Nieprawid³owy format pliku");
			throw e;
	}
		return library;
}
}
