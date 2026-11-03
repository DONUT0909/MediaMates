
/*
 * MediaMates
 * Main.java
 * Author: Taylor DeBerry
 *
 * This is where the program starts.
 * For now, we're using it to test the review system.
 */

package com.mediamates;

import manager.ReviewManager;
import model.Review;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch();

        // Creates the review manager.
        ReviewManager reviewManager = new ReviewManager();

        // Creates a sample review for testing.
        Review review1 = new Review(
            "BOOK001",
            "Taylor",
            5,
            "I really enjoyed this book!"
        );

        // Adds the review to the manager.
        reviewManager.addReview(review1);

        // Prints the review information.
        System.out.println("MediaMates Review Test");
        System.out.println("----------------------");
        System.out.println("Username: " + review1.getUsername());
        System.out.println("Rating: " + review1.getRating() + " stars");
        System.out.println("Comment: " + review1.getComment());

        // Gets and prints the average rating.
        System.out.println("Average Rating: "
            + reviewManager.getAverageRating("BOOK001"));

        // Tests editing the review.
        reviewManager.editReview(
            review1.getReviewId(),
            4,
            "I enjoyed this book!"
        );

        System.out.println("\nAfter Editing:");
        System.out.println("Rating: " + review1.getRating());
        System.out.println("Comment: " + review1.getComment());

        // Tests deleting the review.
        reviewManager.deleteReview(review1.getReviewId());

        System.out.println("\nAfter Deleting:");
        System.out.println("Number of Reviews: "
            + reviewManager.getAllReviews().size());
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, JavaFX in VS Code!");
        Scene scene = new Scene(new StackPane(label), 400, 250);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }
}
