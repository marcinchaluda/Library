package data;

import java.util.ArrayList;
import java.util.List;

public class LibraryUser extends User{
	
	private static final long serialVersionUID = 8110324437740947497L;
	private List<Publication> publicationHistory;
	private List<Publication> borrowedPublication;
	
	public List<Publication> getPublicationHistory() {
		return publicationHistory;
	}

	public void setPublicationHistory(List<Publication> publicationHistory) {
		this.publicationHistory = publicationHistory;
	}

	public List<Publication> getBorrowedPublication() {
		return borrowedPublication;
	}

	public void setBorrowedPublication(List<Publication> borrowedPublication) {
		this.borrowedPublication = borrowedPublication;
	}

	public LibraryUser(String firstName, String lastName, String pesel) {
		super(firstName, lastName, pesel);
		publicationHistory = new ArrayList<>();
		borrowedPublication = new ArrayList<>();
	}

	public void addPublicationToHistory(Publication pub){
		publicationHistory.add(pub);
	}

	public void borrowPublication(Publication pub){
		borrowedPublication.add(pub);
	}
	
	public boolean returnPublication(Publication pub){
		boolean result = false;
		if(borrowedPublication.remove(pub)){
			result = true;
			publicationHistory.add(pub);
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((borrowedPublication == null) ? 0 : borrowedPublication.hashCode());
		result = prime * result + ((publicationHistory == null) ? 0 : publicationHistory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryUser other = (LibraryUser) obj;
		if (borrowedPublication == null) {
			if (other.borrowedPublication != null)
				return false;
		} else if (!borrowedPublication.equals(other.borrowedPublication))
			return false;
		if (publicationHistory == null) {
			if (other.publicationHistory != null)
				return false;
		} else if (!publicationHistory.equals(other.publicationHistory))
			return false;
		return true;
	}
}
