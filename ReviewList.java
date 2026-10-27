//Taylor DeBerry and Xandra Quevedo
//Review list object class


package bookwormsVol2;

import java.util.ArrayList;

public class ReviewList {
	//Basic attributes - list and name (book title)
	private ArrayList<Review> reviewList;
	private String name;
	
	//Make list for a specific book
	public ReviewList(String name) {
		this.reviewList = new ArrayList<>();
		this.name = name;
	}
	
	//Add review to list
	public void addReview(Review review) {
		this.reviewList.add(review);
	}
	
	//Getters
	public ArrayList<Review> getReviewList() {
		return reviewList;
	}
	
	public String getName() {
		return name;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public void printReviewList() {
		if (reviewList.size() == 0) {
			System.out.println("No reviews found.");
		}
		else {
	        for (Review review : reviewList) {
	            System.out.println(
	                "Reviewer: " + review.getReviewer()
	                + "\nRating: " + review.getRating()
	                + "\nReview: " + review.getReview()
	                + "\n"
	            );
	        }
		}
    }

	//Calculate average rating for the book based on its reviews
	public double calculateAverageRating() {

		if (reviewList.size() == 0) {
			return 0.0;
		}

		double total = 0;

		for (Review review : reviewList) {
			total += review.getRating();
		}

		return total / reviewList.size();
	}
	
	//toString method override for testing purposes
	  @Override
	  public String toString() {
		  return "\nREVIEWS - Title: " + name + "\n";
	  }
}//end ReviewList


