//Taylor DeBerry and Xandra Quevedo
//Review manager class


package bookwormsVol2;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ReviewManager {
	
	//Create our list of reviews variable
    private ArrayList<Review> reviews;
    
    //Make new ArrayList for that prior variable
    public ReviewManager() {
        reviews = new ArrayList<>();
    }
    //Getter for the list of reviews
    public ArrayList<Review> getReviews() {
    return reviews;
}

    //Add a review to the list
    public void addReview(Review review) {
        reviews.add(review);
    }
    
    public void printReviews() {
        for (Review review : reviews) {
            System.out.println(
                "Book: " + review.getBookTitle()
                + "\nReviewer: " + review.getReviewer()
                + "\nRating: " + review.getRating()
                + "\nReview: " + review.getReview()
                + "\n"
            );
        }
    }
    
    //File I//O method to save reviews to a text file
    public void saveReviewsToFile() {
    	try {
            FileWriter writer = new FileWriter("reviews.txt");
            for (Review review : reviews) {
                writer.write(
                    review.getBookTitle() + "," +
                    review.getReviewer() + "," +
                    review.getRating() + "," +
                    review.getReview() + "\n"
                );
            }
            writer.close();
            System.out.println("Reviews saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving reviews.");
        }
    }

    //File I/O method to load reviews from a text file
    public void loadReviewsFromFile() {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader("reviews.txt"));

    		String line;

    		while ((line = reader.readLine()) != null) {
    			String[] parts = line.split(",", 4);

    			String bookTitle = parts[0];
    			String reviewer = parts[1];
    			double rating = Double.parseDouble(parts[2]);
    			String reviewText = parts[3];

    			Review review = new Review(
            		bookTitle,
            		reviewer,
            		reviewText,
            		rating
    			);

    		reviews.add(review);
        }

        reader.close();
        System.out.println("Reviews loaded successfully.");

    	} catch (IOException e) {
    		System.out.println("Error loading reviews.");
    	}
	}
}//end ReviewManager


