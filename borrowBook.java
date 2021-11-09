import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;   

public class borrowBook {

	private static String bookIdTyped;
	
	public static void borrowBooks() throws Exception {
		
		String bookList[] = new String [4];
		String bookID;
		
		Scanner in = new Scanner(System.in);
		ArrayList<String> book = new ArrayList<String>();
		boolean borrow = false;
		String username = login_signup.current_user.username; 
		
		//Welcomes user to the system
		System.out.println("==============================================");
		System.out.println(" Welcome to the library's book rental system! ");
		System.out.println("==============================================");
		System.out.println(" Below are the books we have in the library:\n");
		
		//Prints out all books that are available for borrowing
		Scanner checkArray = new Scanner(new File("booklist.txt"));
		while (checkArray.hasNextLine()) {
			String booklist2 = checkArray.nextLine();
		    String[] printbooklistarray = booklist2.split(", ");
		    System.out.println("Book ID: " + printbooklistarray[0]);
            System.out.println("Name: " + printbooklistarray[1]);
            System.out.println("Author: " + printbooklistarray[2]);
            System.out.println("Category: " + printbooklistarray[3]);
            System.out.println("==============================================");
		}
		
		//User enters book id of book they want to borrow
		System.out.println("\nPlease enter the book id of the book you'd like to borrow:");
		bookIdTyped = in.nextLine();
	
		//System reads booklist.txt file
		File borrowRecord = new File ("borrowRecord.txt");
		File booklist = new File ("booklist.txt");
		FileReader fr = new FileReader(booklist);
		BufferedReader br = new BufferedReader(fr);
		String line;
				
		while ((line = br.readLine()) != null) {
			bookList = line.split(", ");
			bookID = bookList[0];
			
			//If book id is found in booklist.txt:
			if (bookID.equals(bookIdTyped)) {
				 borrow = true;
						 
				//Adds book details (line) into the ArrayList book
				book.add(line);
						
				//Inserts book details to borrowRecord.txt from ArrayList book.
				FileWriter fw = new FileWriter("borrowRecord.txt", true);
				fw.write(username + ", ");
				for (int i = 0; i<book.size(); i++) {
				
					fw.write(book.get(i));
					if (i<book.size()-1) {
						fw.write(", ");
					}
				}	
				fw.write("\n");
				fw.close();
				
				//Deletes book details from booklist.txt (actually rewrites whole file, just skipping the line of the book borrowed)
				delete(bookID);
				
				//After user succesfully borrows a book, user gets sent back to main menu
				System.out.println("You have successfully borrowed this book.");
				System.out.println("Redirecting you back to the menu...");
				login_signup.user_menu();
				break;
			}
		}
		
		//If book is not found in booklist.txt:
		if (!borrow) {
			System.out.println("Invalid book id. Either: \n1. The book doesnt exist in the library. \n2. The book has been borrowed.\n");
			
			//Asks user if user wants to borrow another book or go back to main menu
			while (true) {
				System.out.println("Would you like to go back to main menu or borrow another book instead? \n1. Return to Main Menu \n2. Borrow another book \nPlease enter your selection.");
				int answer = in.nextInt();
			
				switch (answer) {
				//User gets sent back to main menu
				case 1 :
					System.out.println("Redirecting you back to the menu...");
					login_signup.user_menu();
					break;
				//User gets sent back to the start to borrow another book
				case 2 :
					System.out.println("Redirecting you back to the start of the process...");
					borrowBooks();
					break;
				//If input isn't 1 or 2 User will have to input again
				default :
					System.out.println("Please enter the number of your selection again.");
					continue;
				}
			}
		}	
		br.close();
		in.close();
	}
		
	private static void delete (String bookID) throws Exception {
		
		// to count number of row in booklist text file
		Scanner count = new Scanner(new File("booklist.txt"));
		int counter = 0;
		while(count.hasNextLine()) {
            count.nextLine();
            counter++;
        }
		count.close();
		
		// using 2d-array to store initial booklist before deleting the the book that has been borrowed
		String initialBookList[][] = new String [counter][4];
		Scanner store = new Scanner(new File("booklist.txt"));
		while (store.hasNextLine()) {
			for (int i=0; i<counter; i++) {
				String bookData = store.nextLine();
				String[] singleBookData = bookData.split(", ");
				initialBookList[i][0] = singleBookData[0];
				initialBookList[i][1] = singleBookData[1];
				initialBookList[i][2] = singleBookData[2];
				initialBookList[i][3] = singleBookData[3];
			}
		}
		
		// to make the booklist file empty first before we write in the new data
		new FileWriter ("booklist.txt", false).close();
		
		// write in the new data into booklist
		FileWriter writer = new FileWriter("booklist.txt", true);
		for (int i=0; i<counter; i++) {
			if (!initialBookList[i][0].equals(bookID)) {
				for (int j=0; j<4; j++) {
					writer.write(initialBookList[i][j]);
					if (j<3) {
						writer.write(", ");
					}
				}
				writer.write("\n");
			}
		}
		writer.close();
	}
}