//Taylor DeBerry and Xandra Quevedo
//Book object class containing constructors, getters, setters, and toString methods


package bookwormsVol2;

public class Book {
	//Basic book attributes
	private String title;
	private String author;
	private String genre;
	private double averageRating;
	private ReviewList reviewList;
	
	//Parameterized constructor
	public Book(String title, String author, String genre, double averageRating,
			ReviewList reviewList
			){
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.averageRating = averageRating; 
		this.reviewList = reviewList;
	}

	//Getters
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public double getAverageRating() {
		return averageRating;
	}
 
	public ReviewList getReviewList() {
		return reviewList;
	}

	//Setters
	//Might not actually use these but good to have anyway -XQ
	public void setTitle(String title) {
		this.title = title;
	}

 	public void setAuthor(String author) {
 		this.author = author;
 	}

 	public void setGenre(String genre) {
 		this.genre = genre;
 	}

 	public void setAverageRating(double averageRating) {
 		this.averageRating = averageRating;
 	}
  
 	public void setReviewList(ReviewList reviewList) {
 		this.reviewList = reviewList;
 	}
  
 	//Update average rating method - updates the average rating of the book based on its reviews
 	public void updateAverageRating() {
 		averageRating = reviewList.calculateAverageRating();
 	}

	
 	//toString method override for testing purposes
 	@Override
 	public String toString() {
 		return "\nBOOK - Title: " + title + ", Author: " + author + ", Genre: " + genre +
 				", Avg. Rating: " + averageRating + "\n";
 	}
}
