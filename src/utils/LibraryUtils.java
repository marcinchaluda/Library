package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data.Book;
import data.Library;
import data.LibraryUser;
import data.Magazine;
import data.Publication;

public class LibraryUtils {

	public static void printBooks(Library library) {
		List<Publication> publications = new ArrayList<>();
		publications.addAll(library.getPublication().values());
		Collections.sort(publications, new Library.AlphabeticalComparator());
		int countBooks = 0;
		for (Publication p : publications) {
			if (p instanceof Book) {
				System.out.println(p);
				countBooks++;
			}
		}
		if (countBooks == 0) {
			System.out.println("Brak ksi¹¿ek w bibliotece");
		}
	}

	public static void printMagazine(Library library) {
		List<Publication> publications = new ArrayList<>();
		publications.addAll(library.getPublication().values());
		Collections.sort(publications, new Library.AlphabeticalComparator());
		int countMagazines = 0;
		for (Publication p : publications) {
			if (p instanceof Magazine) {
				System.out.println(p);
				countMagazines++;
			}
		}
		if (countMagazines == 0) {
			System.out.println("Brak magazynów w bibliotece");
		}
	}

	public static void printUser(Library library) {
		List<LibraryUser> users = new ArrayList<>();
		users.addAll(library.getUsers().values());
		Collections.sort(users, new Comparator<LibraryUser>(
				) {
					@Override
					public int compare(LibraryUser u1, LibraryUser u2) {
						return u1.getLastName().compareTo(u2.getLastName());
					}
		});
		for (LibraryUser u : users) {
			System.out.println(u);
		}
	}
}
