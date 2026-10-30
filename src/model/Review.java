
/*
 * MediaMates
 * Review.java
 * Author: Taylor DeBerry
 *
 * This class stores information about a review.
 * A review can be written for a book, movie, or song.
 * It keeps track of who wrote the review, what they rated,
 * their comment, and when they created it.
 */

package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {

    // Stores a unique ID for each review so we can tell them apart.
    private String reviewId;

    // Stores the ID of the book, movie, or song being reviewed.
    // We use the ID instead of the title because different items
    // could have the same name.
    private String mediaId;

    // Stores the username of the person who wrote the review.
    private String username;

    // Stores the star rating the user gave the item.
    // Ratings will be from 1 to 5.
    private int rating;

    // Stores the written comment that the user leaves.
    private String comment;

    // Keeps track of when the review was first created.
    private LocalDateTime date;


    /*
     * Constructor
     *
     * This runs whenever a new Review object is created.
     * It receives the information about the review and stores it.
     *
     * The review ID and date are automatically created
     * instead of having the user enter them.
     */
    public Review(String mediaId, String username, int rating, String comment) {

        // Creates a unique ID for this review.
        this.reviewId = UUID.randomUUID().toString();

        // Connects the review to the correct media item.
        this.mediaId = mediaId;

        // Saves the username of the person writing the review.
        this.username = username;

        // Saves the star rating.
        this.rating = rating;

        // Saves the written comment.
        this.comment = comment;

        // Records the current date and time.
        this.date = LocalDateTime.now();
    }


    /*
     * Getter Methods
     *
     * These methods let other classes access the information
     * stored in this Review object.
     */


    // Returns the unique ID of the review.
    public String getReviewId() {
        return reviewId;
    }

    // Returns the ID of the book, movie, or song being reviewed.
    public String getMediaId() {
        return mediaId;
    }

    // Returns the username of the person who wrote the review.
    public String getUsername() {
        return username;
    }

    // Returns the star rating.
    public int getRating() {
        return rating;
    }

    // Returns the written comment.
    public String getComment() {
        return comment;
    }

    // Returns the date and time the review was created.
    public LocalDateTime getDate() {
        return date;
    }


    /*
     * Setter Methods
     *
     * These let us update certain parts of a review.
     * For example, a user might want to change their rating
     * or fix something in their written comment.
     */

    // Changes the rating of an existing review.
    public void setRating(int rating) {
        this.rating = rating;
    }

    // Changes the written comment of an existing review.
    public void setComment(String comment) {
        this.comment = comment;
    }
}
