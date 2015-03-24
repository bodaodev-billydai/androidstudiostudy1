package spark.sample;

public class Book {

	private String title;
	private String author;

	public Book(String initAuthor, String initTitle) {
		title = initTitle;
		author = initAuthor;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}
}
