//Taylor DeBerry and Xandra Quevedo
//Main class for the project


package bookwormsVol2;


import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	
    	//Create our object managers
    	//These will handle their lists and sort/search
        BookManager bookManager = new BookManager();
        ReviewManager reviewManager = new ReviewManager();
        
        //Create a scanner for user input
         Scanner scanner = new Scanner(System.in);
         
        //Load books and reviews from file
        bookManager.loadBooksFromFile();
        reviewManager.loadReviewsFromFile();
        
        //Associate reviews with their respective books
        for (Review review : reviewManager.getReviews()) {
        	Book loadedBook = bookManager.findBook(review.getBookTitle());
        	
        	if (loadedBook != null) {
        		loadedBook.getReviewList().addReview(review);
        	}
        }

        boolean running = true;
        
        // menu
        while (running) {

        	System.out.println("\n=== BOOKWORMS ===");
        	System.out.println("1. View All Books");
        	System.out.println("2. Search by Genre");
        	System.out.println("3. Search by Title");
        	System.out.println("4. Search by Keyword");
        	System.out.println("5. Add Book");
        	System.out.println("6. Add Review");
        	System.out.println("7. Remove Book");
        	System.out.println("8. Modify Book");
        	System.out.println("9. Sort Books");
        	System.out.println("10. Exit");

        	System.out.print("Enter choice: ");
        	int choice = scanner.nextInt();
        	scanner.nextLine();
        	System.out.println();

        	switch (choice) {
        	
        	//View all books - Either just titles, or with reviews
        	case 1:
        		System.out.println("Would you like to view: ");
        		System.out.println("1. Titles with authors only" + 
        		"\n2. Titles and reviews");
        		System.out.print("Enter choice: ");
        		int viewChoice = scanner.nextInt();
        		
        		System.out.println();
            	
            	switch (viewChoice) {
            		case 1:
            			bookManager.viewBooks();
            			break;
            		case 2:
            			bookManager.viewBooksWithReviews();
            			break;
            		default:
                		System.out.println("Invalid choice.");
            	}
            	break;
        		
        	//Search by genre
        	case 2:
        		ArrayList<Book> bookList = bookManager.getBooks();
        		bookManager.quickSortBase(bookList, 0, bookList.size() - 1, 2);
        		
        		System.out.println("Genres include the following: ");
        		bookManager.printGenres();
        		System.out.println("Enter genre: ");
        		String genre = scanner.nextLine();
        		bookManager.searchByGenre(genre);
        		break;
        		
        	//Search by title
        	case 3:
        		System.out.println("Enter title: ");
        		String title = scanner.nextLine();
        		bookManager.searchByTitle(title);
        		break;
        	
        	//Search by keyword
        	case 4:
        		System.out.println("Enter keyword: ");
        		String keyword = scanner.nextLine();
        		bookManager.searchByKeyword(keyword);
        		break;
        	
        	//Add book
        	case 5:
        		System.out.print("Enter title (No Commas): ");
        		String newTitle = scanner.nextLine();
        		
        		System.out.print("Enter author: ");
        		String author = scanner.nextLine();

        		System.out.print("Enter genre: ");
        		String newGenre = scanner.nextLine();
        		
        		System.out.print("Enter rating: ");
        		double averageRating = scanner.nextDouble();
        		scanner.nextLine();
        		
        		System.out.println();

        		ReviewList reviews = new ReviewList(newTitle);
        		
        		Book newBook = new Book(
        				newTitle,
        				author,
        				newGenre,
        				averageRating,
        				reviews
        				);

        		bookManager.addBook(newBook);
        		bookManager.saveBooksToFile();
        		System.out.println("Book added successfully!");
        		break;
        		
        	//Add review
        	case 6:
        		
        		//Check to see if the book exists already
        		System.out.print("Enter book title: ");
        		String bookTitle = scanner.nextLine();

        		Book book = bookManager.findBook(bookTitle);

        		if (book != null) {

        			System.out.print("Enter reviewer name: ");
        			String reviewer = scanner.nextLine();

        			System.out.print("Enter rating: ");
        			double rating = scanner.nextDouble();
        			scanner.nextLine();

        			System.out.print("Enter review (No Commas): ");
        			String reviewText = scanner.nextLine();

        			Review newReview = new Review(
        					bookTitle,
        					reviewer,
        					reviewText,
        					rating
        					);

        			book.getReviewList().addReview(newReview);
        			book.updateAverageRating();
        			bookManager.saveBooksToFile();
        			reviewManager.addReview(newReview);
        			reviewManager.saveReviewsToFile();
        			System.out.println("Review added successfully!");

        		}
        		else {

        			System.out.println("Book not found.");
        			
        		}

        		break;
        	
        	//Remove book
        	case 7:

        		System.out.print("Enter title of book to remove: ");
        		String removeTitle = scanner.nextLine();

        		bookManager.removeBook(removeTitle);
        		bookManager.saveBooksToFile();
        		break;
        	
        	//Modify book
        	case 8:
            	System.out.println("\nWhat book would you like to modify?");
            	String titleM = scanner.nextLine();

        		Book bookM = bookManager.findBook(titleM);

        		if (bookM != null) {
        			
                	System.out.println("\nWhat detail would you like to modify?");
                	System.out.println("1. Edit Title");
                	System.out.println("2. Edit Author");

                	System.out.print("Enter choice: ");
                	int decision = scanner.nextInt();
                	scanner.nextLine();
                	
                	switch (decision) {
                	
                	case 1:
            			System.out.print("Enter new title: ");
            			String newTitleM = scanner.nextLine();
            			
            			bookM.setTitle(newTitleM);
                		
                		break;
                	
                	case 2:
                		System.out.print("Enter new author: ");
            			String newAuthorM = scanner.nextLine();
            			
            			bookM.setAuthor(newAuthorM);
                		
                		break;
                		
                		
                	default:
                		System.out.println("Invalid choice.");
                	}

        			
                	bookManager.saveBooksToFile();
            		System.out.println("Book modified successfully!");

        		}
        		
        		else {

        			System.out.println("Book not found.");
        			
        		}

        		break;
        		
        	//Sort books
        	case 9:
        		ArrayList<Book> books = bookManager.getBooks();
        		
    			System.out.println("How would you like to sort?");
               	System.out.println("1. Sort by Title");
               	System.out.println("2. Sort by Genre");
               	System.out.println("3. Sort by Average Rating (Lowest to Highest)");

            	System.out.print("Enter choice: ");
            	int sortChoice = scanner.nextInt();
            	scanner.nextLine();
            	
            	switch (sortChoice) {
            	
            	case 1:
            		bookManager.quickSortBase(books, 0, books.size() - 1, sortChoice);
            		
            		break;
            	
            	case 2:
            		bookManager.quickSortBase(books, 0, books.size() - 1, sortChoice);
            		
            		break;
            		
            	case 3:
            		bookManager.quickSortBase(books, 0, books.size() - 1, sortChoice);
            		
            		break;
            		
            	default:
            		System.out.println("Invalid choice.");
            	}
        		
        		bookManager.saveBooksToFile();
    			System.out.println("Books sorted successfully!");
        		
        		break;

        	case 10:
        		System.out.println("Goodbye!");
        		running = false;
        		break;

        	default:
        		System.out.println("Invalid choice.");
        	}//end switch
        	
        }//end while
        scanner.close();
    }//end main
}//end class