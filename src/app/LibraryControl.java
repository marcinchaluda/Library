package app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import data.Book;
import data.Library;
import data.LibraryUser;
import data.Magazine;
import utils.DataReader;
import utils.FileManager;
import utils.LibraryUtils;

public class LibraryControl {

	// zmienna do komunikacji z u�ytkownikiem
	private DataReader dataReader;
	private FileManager fileManager;
	// "biblioteka" przechowuj�ca dane
	private Library library;

	public LibraryControl() {
		dataReader = new DataReader();
		fileManager = new FileManager();
		try {
			library = fileManager.readLibraryFromFile();
			System.out.println("Wczytano dane biblioteki z pliku");
		} catch (ClassNotFoundException | IOException e) {
			library = new Library();
			System.out.println("Utworzono now� baz� biblioteki.");
		}
	}

	/*
	 * G��wna p�tla programu, kt�ra pozwala na wyb�r opcji i interakcj�
	 */
	public void controlLoop() {
		Option option = null;
		while (option != Option.EXIT) {
			try {
				printOptions();
				option = Option.createFromInt(dataReader.getInt());
				switch (option) {
				case ADD_BOOK:
					addBook();
					break;
				case PRINT_BOOKS:
					printBooks();
					break;
				case ADD_MAGAZINE:
					addMagazine();
					break;
				case PRINT_MAGAZINE:
					printMagazines();
					break;
				case ADD_USER:
					addUser();
					break;
				case PRINT_USER:
					printUser();
					break;
				case EXIT:
					;
				}
			} catch (InputMismatchException e) {
				System.out.println("Wprowadzono niepoprawne dane, publikacji nie dodano");
			} catch (NumberFormatException | NoSuchElementException e) {
				System.out.println("wybrana opcja nie istnieje, wybierz ponownie: ");
			}
		}
		// zamykamy strumie� wej�cia
		dataReader.close();
	}

	private void printUser() {
		LibraryUtils.printUser(library);
	}

	private void addUser() {
		LibraryUser user = dataReader.readAndCreateLibraryUser();
		library.addUser(user);
	}

	private void printOptions() {
		System.out.println("Wybierz opcj�: ");
		for (Option option : Option.values()) {
			System.out.println(option);
		}
	}

	private void addBook() {
		Book book = dataReader.readAndCreateBook();
		library.addBook(book);
	}

	private void printBooks() {
		LibraryUtils.printBooks(library);
	}

	private void addMagazine() {
		Magazine magazine = dataReader.readAndCreateMagazine();
		library.addMagazine(magazine);
	}

	private void printMagazines() {
		LibraryUtils.printMagazine(library);
	}

	public void exit() {
		fileManager.writeLibraryToFile(library);
	}

	private enum Option {
		// zmienne do kontrolowania programu
		EXIT(0, "Wyj�cie z programu"), ADD_BOOK(1, "Dodanie ksi��ki"), PRINT_BOOKS(2,
				"Wy�wietlenie dost�pnych ksi��ek"), ADD_MAGAZINE(3, "Dodanie magazynu/gazety"), PRINT_MAGAZINE(4,
						"Wy�wietlenie dost�pnych magazyn�w/gazet"), ADD_USER(5,
								"Dodanie nowego u�ytkownika"), PRINT_USER(6, "Wy�wietlenie listy u�ytkownik�w");

		private int value;
		private String description;

		Option(int value, String description) {
			this.value = value;
			this.description = description;
		}

		@Override
		public String toString() {
			return value + " - " + description;
		}

		// poni�sz metoda pozwala przekszta�cic int na odpowiedni� warto�� typu
		// Option przy urzyciu metody values()
		public static Option createFromInt(int option) throws NoSuchElementException {
			Option result = null;
			try {
				result = Option.values()[option];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new NoSuchElementException("Brak elementu o wskazanym ID");
			}
			return result;
		}
	}
}