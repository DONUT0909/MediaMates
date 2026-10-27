//Taylor DeBerry and Xandra Quevedo
//Book manager class


package bookwormsVol2;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookManager {
	
	//Create our list of books variable
    private ArrayList<Book> books;
    
    //Getter for the list of books
    public ArrayList<Book> getBooks() {
    	return books;
    }

    //Make new ArrayList for that prior variable
    public BookManager() {
        books = new ArrayList<>();
    }
    
    //Add a book to the list
    public void addBook(Book book) {
        books.add(book);
    }
    
    //Print books using a for loop
    //This prints all the details
    public void printBooks() {
        for (Book book : books) {
            System.out.println(
                "Title: " + book.getTitle()
                + "\nAuthor: " + book.getAuthor()
                + "\nGenre: " + book.getGenre()
                + "\nAverage Rating: " + book.getAverageRating()
                //+ "\nReviews: "
                + "\n"
            );
            //Get the correct review list for the book
            //Then use its print method
           ReviewList reviewList = book.getReviewList();
           reviewList.printReviewList();
        }
    }
    
    //Print book titles only, with dashes for readability
    public void viewBooks() {
    	System.out.println("----------------------");
        for (Book book : books) {
            System.out.println(
                book.getTitle() 
                + " by "
                + book.getAuthor()
                + "\n----------------------"
            );
        }
    }
    
    //Print book titles only, with dashes for readability
    public void viewBooksWithReviews() {
        for (Book book : books) {
        	System.out.println("----------------------");
            System.out.println(
                book.getTitle() 
                + " by "
                + book.getAuthor()
                + "\n"
            );
            ReviewList reviewList = book.getReviewList();
            reviewList.printReviewList();
        }
    }
    
    //Print genres
    public void printGenres() {
    	String currGenre = "null";
    	String newGenre = null;
    	
    	for (Book book : books) {
    		newGenre = book.getGenre();
    		if (!currGenre.equals(newGenre)) {
    			currGenre = newGenre;
    			System.out.println("   " + newGenre);
    		}
    	}
    }
    
    //Search by Genre method - searches through the book list and prints out any books that match the genre parameter
    public void searchByGenre(String genre) {

    	boolean found = false;

    	System.out.println("\nBooks in genre: " + genre);
    	
    	for (Book book : books) {

    		if (book.getGenre().equalsIgnoreCase(genre)) {

    			System.out.println(
    					book.getTitle()
    					+ " by "
    					+ book.getAuthor()
    					);

    			found = true;
    		}
    	}

    	if (!found) {
    		System.out.println("No books found.");
    	}
    }
   
    
    //Search by Title method - searches through the book list and prints out any books that match the title parameter
    public void searchByTitle(String title) {
    	
    	System.out.println("\nWe found...");

    	boolean found = false;

    	for (Book book : books) {

    		if (book.getTitle().equalsIgnoreCase(title)) {

    			System.out.println(
    					"\nTitle: " + book.getTitle()
    					+ "\nAuthor: " + book.getAuthor()
    					+ "\nGenre: " + book.getGenre()
    					+ "\nAverage Rating: " + book.getAverageRating()
    					);
    			
    			found = true;
    		}
    	}

    	if (!found) {
    		System.out.println("Book not found.");
    	}
    }
    
    //Search by keyword method
    //Takes a keyword, looks for books that contain it, and prints them
    public void searchByKeyword(String keyword) {
    	
    	System.out.println("\nBooks containing keyword: " + keyword);
    	
    	ArrayList<Book> foundBooks = new ArrayList<>();

    	for (Book book : books) {

    		if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
    			
    			System.out.println(
    					"\nTitle: " + book.getTitle()
    					+ "\nAuthor: " + book.getAuthor()
    					+ "\nGenre: " + book.getGenre()
    					+ "\nAverage Rating: " + book.getAverageRating()
    					);
    			
    			foundBooks.add(book);
    
    		}
    	}
    	
    	if (foundBooks.isEmpty()) {
    		System.out.println("Book not found.");
    	}
    }
    
    //Find a book using its title and return it
    //This may appear redundant but is used for some main methods
    //that do not need anything to be printed
    public Book findBook(String title) {
    	for (Book book : books) {
    		if (book.getTitle().equalsIgnoreCase(title)) {
    			return book;
    		}
    	}

    	return null;
    }
    
    //Remove book
    public void removeBook(String title) {

    	for (int i = 0; i < books.size(); i++) {

    		if (books.get(i).getTitle().equalsIgnoreCase(title)) {
    			books.remove(i);
    			System.out.println("Book removed successfully!");
    			return;
    		}
    	}
    	System.out.println("Book not found.");
    }
    //File I/O - save books to file method - saves the current list of books to a text file called "books.txt"
    public void saveBooksToFile() {

    	try {

    		FileWriter writer = new FileWriter("books.txt");

    		for (Book book : books) {

    			writer.write(
    					book.getTitle() + "," +
    					book.getAuthor() + "," +
    					book.getGenre() + "," +
    					book.getAverageRating() + "\n"
    				);
    		}

    		writer.close();

        	System.out.println("Books saved successfully.");

    	}
    	catch (IOException e) {
    		System.out.println("Error saving books.");
    	}
	}

    //File I/O - load books from file method - loads the list of books from a text file called "books.txt"
    public void loadBooksFromFile() {

    	try {

    		File file = new File("books.txt");
    		Scanner fileScanner = new Scanner(file);

    		while (fileScanner.hasNextLine()) {

    			String line = fileScanner.nextLine();

    			String[] parts = line.split(",");

    			String title = parts[0];
    			String author = parts[1];
    			String genre = parts[2];
    			double averageRating = Double.parseDouble(parts[3]);

    			ReviewList reviews = new ReviewList(title);

    			Book book = new Book(
    					title,
    					author,
    					genre,
    					averageRating,
    					reviews
    					);

    			books.add(book);
    		}

    		fileScanner.close();
    		System.out.println("Books loaded successfully.");
    	}
    	
    	catch (FileNotFoundException e) {

    		System.out.println("books.txt not found.");

    	}
    }
    
    //Main method for handling quicksort
    //Takes a choice int, then uses a different partition function depending on it
	public void quickSortBase(ArrayList<Book> books, int low, int high, int choice) {
		if (low < high) {
			
			int partitionIndex;
			
			switch (choice) {
			
			case 1:
				partitionIndex = partitionByTitle(books, low, high);
				
				quickSortBase(books, low, partitionIndex - 1, choice);
				quickSortBase(books, partitionIndex + 1, high, choice);
				
				break;
				
			case 2:
				partitionIndex = partitionByGenre(books, low, high);
				
				quickSortBase(books, low, partitionIndex - 1, choice);
				quickSortBase(books, partitionIndex + 1, high, choice);
				
				break;
				
			case 3:
				partitionIndex = partitionByRating(books, low, high);
				
				quickSortBase(books, low, partitionIndex - 1, choice);
				quickSortBase(books, partitionIndex + 1, high, choice);
				
				break;
			}
		}
	}//end quickSortByGenre
	
	//Partition function using title and strings
	private static int partitionByTitle(ArrayList<Book> books, int low, int high) {
		Book pivot = books.get(high);
		String pivotTitle = pivot.getTitle();
		int i = (low - 1);
		
		for (int j = low; j < high; j++) {
			if (books.get(j).getTitle().compareToIgnoreCase(pivotTitle) < 0) {
				i++;
				
				Book temp = books.get(i);
				books.set(i, books.get(j));
				books.set(j, temp);
			}
		}//end for
		
		Book temp = books.get(i + 1);
		books.set(i + 1, books.get(high));
		books.set(high, temp);
		
		return i + 1;
	}//end partitionByTitle
	
	//Partition function using genre and strings
	private static int partitionByGenre(ArrayList<Book> books, int low, int high) {
		Book pivot = books.get(high);
		String pivotGenre = pivot.getGenre();
		int i = (low - 1);
		
		for (int j = low; j < high; j++) {
			if (books.get(j).getGenre().compareToIgnoreCase(pivotGenre) < 0) {
				i++;
				
				Book temp = books.get(i);
				books.set(i, books.get(j));
				books.set(j, temp);
			}
		}//end for
		
		Book temp = books.get(i + 1);
		books.set(i + 1, books.get(high));
		books.set(high, temp);
		
		return i + 1;
	}//end partitionByGenre
	
	//Partition function using rating and double
	private static int partitionByRating(ArrayList<Book> books, int low, int high) {
		Book pivot = books.get(high);
		double pivotRating = pivot.getAverageRating();
		int i = (low - 1);
		
		for (int j = low; j < high; j++) {
			if (books.get(j).getAverageRating() <= pivotRating) {
				i++;
				
				Book temp = books.get(i);
				books.set(i, books.get(j));
				books.set(j, temp);
			}
		}//end for
		
		Book temp = books.get(i + 1);
		books.set(i + 1, books.get(high));
		books.set(high, temp);
		
		return i + 1;
	}//end partitionByRating

}//end class
