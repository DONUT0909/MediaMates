
/*
 * MediaMates
 * ReviewManager.java
 * Author: Taylor DeBerry
 *
 * This class manages all the reviews in MediaMates.
 * It allows users to add, view, edit, and delete reviews.
 *
 * It also calculates the average rating for books, movies,
 * and songs. If a media item gets deleted, this class can
 * also remove all the reviews connected to that item.
 */

package manager;

import model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewManager {

    // This list holds all the reviews currently in the program.
    // Each review is stored as a Review object.
    private List<Review> reviews;


    /*
     * Constructor
     *
     * Creates an empty list when a ReviewManager is first made.
     * New reviews will be added to this list as users submit them.
     */
    public ReviewManager() {
        reviews = new ArrayList<>();
    }


    /*
     * addReview()
     *
     * Adds a new review to the list.
     *
     * Before adding it, we check that the review isn't empty
     * and that its rating is between 1 and 5.
     */
    public void addReview(Review review) {

        // Makes sure an actual Review object was passed in.
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null.");
        }

        // Makes sure the rating follows our 1-to-5 star system.
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        // Adds the review to our list.
        reviews.add(review);
    }


    /*
     * getReviewsForMedia()
     *
     * Finds all the reviews for a specific book, movie, or song.
     *
     * We use the media ID to find the correct item instead
     * of searching by title.
     *
     * This method returns a list because one item can have
     * multiple reviews from different users.
     */
    public List<Review> getReviewsForMedia(String mediaId) {

        // Creates a new list to hold the reviews we find.
        List<Review> mediaReviews = new ArrayList<>();

        // Goes through every review in our main list.
        for (Review review : reviews) {

            // Checks whether this review belongs to the
            // media item we are looking for.
            if (review.getMediaId().equals(mediaId)) {

                // Adds the matching review to our new list.
                mediaReviews.add(review);
            }
        }

        // Returns all the reviews found for that media item.
        return mediaReviews;
    }


    /*
     * getAllReviews()
     *
     * Returns all the reviews currently stored in the program.
     *
     * We return a copy of the list so other classes cannot
     * directly change the original list inside ReviewManager.
     */
    public List<Review> getAllReviews() {

        // Creates and returns a copy of our review list.
        return new ArrayList<>(reviews);
    }


    /*
     * editReview()
     *
     * Finds an existing review and changes its rating and comment.
     *
     * We search using the review ID so we edit the correct review.
     *
     * Returns true if the review was found and changed.
     * Returns false if we couldn't find a matching review.
     */
    public boolean editReview(String reviewId, int rating, String comment) {

        // Makes sure the new rating is between 1 and 5.
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        // Goes through all the reviews to find the one we want.
        for (Review review : reviews) {

            // Checks whether this is the review we are looking for.
            if (review.getReviewId().equals(reviewId)) {

                // Updates the rating with the new one.
                review.setRating(rating);

                // Updates the written comment.
                review.setComment(comment);

                // Lets the rest of the program know the edit worked.
                return true;
            }
        }

        // Returns false if no review with that ID was found.
        return false;
    }


    /*
     * deleteReview()
     *
     * Deletes one specific review from the list.
     *
     * We use the review ID to make sure we delete the correct one.
     *
     * Returns true if a review was deleted.
     * Returns false if no matching review was found.
     */
    public boolean deleteReview(String reviewId) {

        // Checks every review and removes the one with
        // the matching review ID.
        return reviews.removeIf(
            review -> review.getReviewId().equals(reviewId)
        );
    }


    /*
     * deleteReviewsForMedia()
     *
     * Deletes every review connected to a specific media item.
     *
     * For example, if someone deletes a book from MediaMates,
     * we don't want its reviews staying in the program.
     *
     * The MediaManager class can call this method whenever
     * a book, movie, or song is deleted.
     */
    public void deleteReviewsForMedia(String mediaId) {

        // Finds and removes every review with the matching media ID.
        reviews.removeIf(
            review -> review.getMediaId().equals(mediaId)
        );
    }


    /*
     * getAverageRating()
     *
     * Calculates the average star rating for a book, movie, or song.
     *
     * It adds together all the ratings for that item and divides
     * the total by the number of reviews.
     *
     * If there are no reviews, it returns 0.0.
     */
    public double getAverageRating(String mediaId) {

        // Gets all the reviews for the media item.
        List<Review> mediaReviews = getReviewsForMedia(mediaId);

        // Checks if anyone has reviewed this item yet.
        // We return 0.0 instead of dividing by zero.
        if (mediaReviews.isEmpty()) {
            return 0.0;
        }

        // Keeps track of the total number of stars.
        int total = 0;

        // Goes through each review and adds its rating.
        for (Review review : mediaReviews) {
            total += review.getRating();
        }

        // Divides the total stars by the number of reviews.
        // We use double so the average can include decimals.
        return (double) total / mediaReviews.size();
    }
}
