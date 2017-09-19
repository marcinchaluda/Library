package data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {

	private static final long serialVersionUID = -3259259524229772293L;
	private Map<String, Publication> publications;
	private Map<String, LibraryUser> users;

	public Map<String, LibraryUser> getUsers() {
		return users;
	}

	public Map<String, Publication> getPublication() {
		return publications;
	}

	public int getPublicationNumber() {
		return publications.size();
	}

	public Library() {
		publications = new HashMap<>();
		users = new HashMap<>();
	}

	public void addUser(LibraryUser user) {
		users.put(user.getPesel(), user);
	}

	public void addBook(Book book) {
		addPublication(book);
	}

	public void addMagazine(Magazine magazine) {
		addPublication(magazine);
	}

	public void addPublication(Publication pub) {
		publications.put(pub.getTitle(), pub);
	}

	public void removePublication(Publication publication) {
		if (publications.containsValue(publication)){
			publications.remove(publication.getTitle());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Publication p: publications.values()) {
			builder.append(p);
			builder.append("\n");
		}
		return builder.toString();
	}

	public static class AlphabeticalComparator implements Comparator<Publication> {
		@Override
		public int compare(Publication o1, Publication o2) {
			if (o1 == null && o2 == null) {
				return 0;
			}
			if (o1 == null) {
				return 1;
			}
			if (o2 == null) {
				return -1;
			}
			return o1.getTitle().compareTo(o2.getTitle());
		}
	}

	public static class DateComparator implements Comparator<Publication> {
		@Override
		public int compare(Publication o1, Publication o2) {
			if (o1 == null & o2 == null) {
				return 0;
			}
			if (o1 == null) {
				return 1;
			}
			if (o2 == null) {
				return -1;
			}
			Integer date1 = o1.getYear();
			Integer date2 = o2.getYear();
			return -(date1.compareTo(date2));
		}
	}
}