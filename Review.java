//Taylor DeBerry and Xandra Quevedo
//Review object class

package bookwormsVol2;

public class Review {
	//Basic review attributes
	private String bookTitle;
	private String reviewer;
	private String review;
	private double rating;
	
	//Parameterized constructor
	public Review(String bookTitle, String reviewer, String review, double rating){
		this.bookTitle = bookTitle;
	    this.reviewer = reviewer;
	    this.review = review;
	    this.rating = rating; 
	}
	  
	//Getters
	public String getBookTitle() {
		return bookTitle;
	}
	  
	public String getReviewer() {
		return reviewer;
	}

	public String getReview() {
		return review;
	}

	public double getRating() {
		return rating;
	}

	//Setters
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	  
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	//toString method override for testing purposes
	@Override
	public String toString() {
		return "\nREVIEW - Reviewer: " + reviewer + ", Review: " + review + 
				", Rating: " + rating + "\n";
	}
}//end Review