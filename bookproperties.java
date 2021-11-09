
import java.io.*;
import java.util.Scanner;

public class bookproperties {
	public static File bookFile = new File("booklist.txt");

	public static void menu() throws IOException {
    	Scanner input = new Scanner(System.in);
        System.out.println("========== LIBRARY SYSTEM MENU ==========");
        System.out.println("1. Add book" + "\n" + "2. Edit book" + "\n" + "3. Delete book");
        System.out.println("");
	    System.out.println("Pick the number of your choice: ");
	    int choice = input.nextInt();
	    
	    switch (choice) {
	    	//direct to book adding menu
	        case 1:
	            addition();
	            break;
	        
	        //direct to edit menu
	        case 2:
	            edit();
	            break;
	        
	        //direct to delete menu
	        case 3:
	        	delete();
	        	break;
	
	        default:
	            System.out.println("Invalid Command, please try again.");
	            login_signup.admin_menu();        
	    }
        input.close();
	}

	// add books to library feature
	public static void addition() throws IOException {
		Scanner input = new Scanner(System.in);
        String bookid, bookname, author, category;
        
        // interface
        System.out.println("============ Add books ===========");
        
        //Request for the new book ID.
        System.out.println("Please enter the id of your book: ");
        bookid = input.nextLine();
        Scanner check1 = new Scanner(new File("booklist.txt"));
        
        //Investigate the existent of the entered book ID in the library.
        while (check1.hasNextLine()) {
        	String bookListCheck1 = check1.nextLine();
        	String[] bookListArray1 = bookListCheck1.split(", ");
        	if (bookid.equals(bookListArray1[0])) {
        		System.out.println("The book id already exist in the system. Please try again.");
        		addition();
        	}
        }
        Scanner check2 = new Scanner(new File("borrowRecord.txt"));
        while (check2.hasNextLine()) {
        	String bookRecord1 = check2.nextLine();
        	String[] bookRecordArray1 = bookRecord1.split(", ");
        	if (bookid.equals(bookRecordArray1[1])) {
        		System.out.println("The book id already exist in the system. Please try again.");
        		addition();
        	}
        }
        
        // Request for the book name to be entered into the library
        System.out.println("Please enter the name of your book: ");
        bookname = input.nextLine();
        Scanner check3 = new Scanner(new File("booklist.txt"));
        
        // Checking the presence of the book name entered in the library
        while (check3.hasNextLine()) {
        	String bookListCheck2 = check3.nextLine();
        	String[] bookListArray2 = bookListCheck2.split(", ");
        	if (bookname.toLowerCase().equals(bookListArray2[1].toLowerCase())) {
        		System.out.println("The book you named already exist in the system. Please try again.");
        		addition();
        	}
        }
        
        Scanner check4 = new Scanner(new File("borrowRecord.txt"));
        while (check4.hasNextLine()) {
        	String bookRecord2 = check4.nextLine();
        	String[] bookRecordArray2 = bookRecord2.split(", ");
        	if (bookname.toLowerCase().equals(bookRecordArray2[2].toLowerCase())) {
        		System.out.println("The book you named already exist in the system. Please try again.");
        		addition();
        	}
        }
        
        // Requesting user input about new book's attribute
		System.out.println("Author: ");
        author = input.nextLine();
        System.out.println("Category: ");
        category = input.nextLine();
        
        // Double-checking if user correctly entered the book category
        switch (category.toLowerCase()) {
	    case "biography":
	        break;
	    case "novel":
	        break;
	    case "magazine":
	        break;
	    case "educational books":
	    	break;
	    case "comics":
	    	break;
	    case "literature":
	    	break;
	    default:
	    	System.out.println("You have entered an invalid category name. Please re-enter your book info correctly.");
	    	addition();
	    }
        
        // Store the info of the new book in an array
        String[] newbook = new String[4];
        newbook[0] = bookid;
        newbook[1] = bookname;
        newbook[2] = author;
        newbook[3] = category;
        FileWriter writer = new FileWriter("booklist.txt", true);
        
        // Appending new book to the library
        for (int i = 0; i < newbook.length; i++) {
            writer.write(newbook[i]);
            if (i < (newbook.length-1)) {
                writer.write(", ");
            }
        }
        writer.write("\n");
        writer.close();
        // Interface
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("New book has been added to the library.");
		System.out.println("---------------------------------------");
		System.out.println("");

        login_signup.admin_menu();
        input.close();
	}
	
	//edit book feature
	public static void edit() throws IOException {
		
		//interface
		Scanner input = new Scanner(System.in);
		Scanner checkArray = new Scanner(new File("booklist.txt"));
		Scanner checkArray2 = new Scanner(new File("booklist.txt"));
		System.out.println("========== Edit book details ==========");
		System.out.println("");
		
		// Display current book list
		while (checkArray.hasNextLine()) {
			String booklist2 = checkArray.nextLine();
		    String[] printbooklistarray = booklist2.split(", ");
		    System.out.println("Book ID: " + printbooklistarray[0]);
            System.out.println("Name: " + printbooklistarray[1]);
            System.out.println("Author: " + printbooklistarray[2]);
            System.out.println("Category: " + printbooklistarray[3]);
            System.out.println("=========================================");
		}
		
		//Request book ID
		System.out.println("");
		System.out.println("Please enter Book ID of the book desired to be edited: ");
		String bookid = input.next();
		boolean exist = false;
		String[] selectedbook = new String [4];
		
		// Check the presence book of that ID
		while (checkArray2.hasNextLine()) {
			String booklist = checkArray2.nextLine();
		    String[] booklistarray = booklist.split(", ");
		    if (bookid.equals(booklistarray[0])) {
		    	exist = true;
		        for (int i = 0; i < booklistarray.length; i++) {
		        	selectedbook[i] = booklistarray[i];
		        }
		    }
		}
		checkArray.close();
		checkArray2.close();
		
		//book of that id do not exist
		if (!exist) {
		    System.out.println("The book does not exist.");
		    System.out.println("Please key in a valid BookID.");
		    edit();
		}
		
		//display current properties of the book
		System.out.println("");
		for (int i = 0; i < selectedbook.length; i++) {
			switch (i) {
		    case 1:
		        System.out.println("1. Book Title: " + selectedbook[1]);
		        break;
		    case 2:
		        System.out.println("2. Author: " + selectedbook[2]);
		        break;
		    case 3:
		        System.out.println("3. Category : " + selectedbook[3]);
		        break;
		    }
		}
		
		// Request part to edit from user
		System.out.println("");
		System.out.println("Please enter the number of the part you would like to edit: ");
		Scanner sc = new Scanner(System.in);
		String decision;
		decision = sc.nextLine().toLowerCase();
		
		// Request for the new input of the editing section
		switch (decision) {
		case "1": 
			System.out.println("New book title: ");
		    selectedbook[1] = sc.nextLine();
		    break;
		case "2":
		    System.out.println("New author: ");
		    selectedbook[2] = sc.nextLine();
		    break;
		case "3":
		    System.out.println("New category: ");
		    selectedbook[3] = sc.nextLine();
		    break;
		default:
		    System.out.println("Please enter the number correctly.");
		    edit();
		}
		
		//Check if category is valid
		switch (selectedbook[3].toLowerCase()) {
	    case "biography":
	        break;
	    case "novel":
	        break;
	    case "magazine":
	        break;
	    case "educational books":
	    	break;
	    case "comics":
	    	break;
	    case "literature":
	    	break;
	    default:
	    	System.out.println("You have entered an invalid category name. Please re-enter your book info correctly.");
	    	edit();
	    }
		
		//calculate the size of library aka total row of text file
		int count = 0;
        Scanner checkrow = new Scanner(new File("booklist.txt"));
		while(checkrow.hasNextLine()) {
			checkrow.nextLine();
		    count++;
		}
		checkrow.close();
		
		//edit desired part of the array
		String [][] bookarray = new String [count][4];
		Scanner scanArray = new Scanner(new File("booklist.txt"));
		int row = 0;
		
		while (scanArray.hasNextLine()) {
			String booklist = scanArray.nextLine();
		    String[] booklistarray = booklist.split(", ");
		    for (int i = 0; i < booklistarray.length; i++) {
		    	bookarray[row][i] = booklistarray[i];
		    }
		    row++;
		}
		scanArray.close();
		
		for (int i = 0; i < bookarray.length; i++) {
			if (bookarray[i][0].equals(selectedbook[0])) {
				for (int j = 0; j < bookarray[0].length; j++) {
					bookarray[i][j] = selectedbook[j];
				}
			}
		}
		
		//rewrite text file with edited information
		new FileWriter ("booklist.txt", false).close();
		FileWriter writer = new FileWriter("booklist.txt", true);
		for (int i = 0; i < bookarray.length; i++) {
			for (int j = 0; j < bookarray[0].length; j++) {
				writer.write(bookarray[i][j]);
		        if (j < (bookarray[0].length-1)) {
		        	writer.write(", ");
		        }
			}
			writer.write("\n");
		}
		writer.close();
		//Interface
		System.out.println("");
		System.out.println("--------------------------------------------------------");
		System.out.println("The edited info has successfully uploaded to the system.");
		System.out.println("--------------------------------------------------------");
		System.out.println("");
		login_signup.admin_menu();
		sc.close();
		input.close();
	}
	
	// delete book feature
	public static void delete() throws IOException {
		// Interface
		Scanner input = new Scanner(System.in);
		Scanner checkid = new Scanner(new File("booklist.txt"));
		Scanner print = new Scanner(new File("booklist.txt"));
		System.out.println("========== delete book ==========");
		
		// Display current book list
		while (print.hasNextLine()) {
			String printlist = print.nextLine();
		    String[] printlistarray = printlist.split(", ");
		    System.out.println("Book ID: " + printlistarray[0]);
            System.out.println("Name: " + printlistarray[1]);
            System.out.println("Author: " + printlistarray[2]);
            System.out.println("Category: " + printlistarray[3]);
            System.out.println("=========================================");
		}
		print.close();
		System.out.println("Please enter ID of the book you wish to be removed: ");
		// Request for ID of the book to delete
		String bookid = input.next();
		boolean exist = false;
		
		// Check if book of that id exists
		while (checkid.hasNextLine()) {
			String booklist = checkid.nextLine();
		    String[] booklistarray = booklist.split(", ");
		    if (bookid.equals(booklistarray[0])) {
		    	exist = true;
		    }
		}
		checkid.close();
		
		//book of that id do not exist
		if (!exist) {
		    System.out.println("The book does not exist.");
		    System.out.println("Please key in a valid BookID.");
		    delete();
		}
		
		//calculate the size of library aka total row of text file
		int count = 0;
        Scanner checkrow = new Scanner(new File("booklist.txt"));
		while(checkrow.hasNextLine()) {
			checkrow.nextLine();
		    count++;
		}
		checkrow.close();
		
		// using 2d-array to store initial book list before deleting the the book that has been borrowed
		String initialBookList[][] = new String [count][4];
		Scanner store = new Scanner(new File("booklist.txt"));
		while (store.hasNextLine()) {
			for (int i=0; i<count; i++) {
				String bookData = store.nextLine();
				String[] singleBookData = bookData.split(", ");
				initialBookList[i][0] = singleBookData[0];
				initialBookList[i][1] = singleBookData[1];
				initialBookList[i][2] = singleBookData[2];
				initialBookList[i][3] = singleBookData[3];
			}
		}
		store.close();
		// to make the book list file empty first before we write in the new data
		new FileWriter ("booklist.txt", false).close();
		
		// write in the new data into book list
		FileWriter writer = new FileWriter("booklist.txt", true);
		for (int i=0; i<count; i++) {
			if (!initialBookList[i][0].equals(bookid)) {
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
		// Interface
		System.out.println("");
		System.out.println("-------------------------------------- ");
		System.out.println("The book has successfully been deleted.");
		System.out.println("---------------------------------------");
		System.out.println("");
		login_signup.admin_menu();
		input.close();
	}
}