package problemTwo;

public class AlmatyLibrary {
	
	private static int activeReadersNum = 0;
	private final String libraryCity = "Almaty";
	private String name;
	private String currentBook;
	BookGenres favoriteGenre;
	
	{
		activeReadersNum++;
	}
	public AlmatyLibrary(String name, String currentBook) {
		this.name = name;
		this.currentBook = currentBook;
	}
	public AlmatyLibrary(String name, String currentBook, BookGenres favoriteGenre) {
		this(name, currentBook);
		this.favoriteGenre = favoriteGenre;
	}
	
	public void setInfo (String currentBook) {
		this.currentBook = currentBook;
	}
	public void setInfo (String currentBook, BookGenres favoriteGenre) {
		setInfo(currentBook);
		this.favoriteGenre = favoriteGenre;
	}
	
	public String getActiveReadersNum() {
		return "Number of readers in " + libraryCity + " is " + Integer.toString(activeReadersNum);
	}
	public String getPersonInfo() {
		if (favoriteGenre == null) {
			return name + " is reading '" + currentBook + "' now. Doesn't have favorite genre.";
		} else {
			return name + " is reading '" + currentBook + "' now. Favorite genre: " + favoriteGenre;
		}
	}
}
