import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


class BookQuery { 
	
	private static File bookFile = new File("booklist.txt");
	
  //Method for Main Menu of Book Search
  public static void bookSearchMainMenu(){
	  
	try {
	  Scanner scanMenuInput = new Scanner(System.in);
      char menuInput;
      
      //Menu for users to select options
	  do { 
		  System.out.println("========== BOOK QUERY HOME PAGE ==========");
		  System.out.println("Welcome to the home page of the Book Query feature! \n");
          System.out.println("Please select an option below:");
		  System.out.println("1. Proceed to search for books \n2. Go Back to Main Menu");
		  System.out.println("Please enter your choice:");
		  menuInput = scanMenuInput.next().charAt(0);
		  System.out.println();
		  
	      switch(menuInput) {
	       case '1': bookSearchMenu();
	       break;
	       case '2': System.out.println("Thank you for using the Book Query Feature! :D\n");
	       if (login_signup.current_user.accType.equals("Admin")) {
	    	   login_signup.admin_menu();
	       }
	       else {
	    	   login_signup.user_menu();
	       }
	       break;
	       default: System.out.println("The command entered is invalid, please enter a new command. \n");
	      }
	  } while(menuInput != '1' && menuInput != '2');
	  
	  scanMenuInput.close();
  } 
	//If there is any exceptions, print this message and restart the method
	catch (Exception e) {
		System.out.println("\n An error has occured, please try again. \n");
		e.printStackTrace();
		System.out.println();
		bookSearchMainMenu();
	}
  } //method ends here
  
  //Menu to select the method to search books by
  private static void bookSearchMenu(){
	 try {
	  Scanner scanSearchInput = new Scanner(System.in);
	  char searchInput;
	  
	  //Menu for users to select options
	  do {
		  System.out.println("========== BOOK QUERY MAIN MENU ==========");
		  System.out.println("Welcome to the Book Query Menu! \n");
		  System.out.println("Please select the method to search for books by:");
		  System.out.println("1. Search for books by name \n2. Search for books by author \n3. Search for books by category \n4. Go back to the home page");
		  System.out.println("Please enter your choice: ");
		  searchInput = scanSearchInput.next().charAt(0);
		  System.out.println();
		  
		  switch(searchInput) {
		  case '1': searchBooksByName();
		  break;
		  case '2': searchBooksByAuthor();
		  break;
		  case '3': searchBooksByCategory();
		  break;
		  case'4': bookSearchMainMenu();
		  break;
		  default: System.out.println("The command entered is invalid, please enter a new command. \n");
		  } 
	  } while(searchInput != '1' && searchInput != '2' && searchInput != '3' && searchInput != '4');
	  
	  scanSearchInput.close();
  } 
	//If there is any exceptions, print this message and restart the method
	 catch (Exception e) {
		 System.out.println("\n An error has occured, please try again. \n");
		 e.printStackTrace();
	     System.out.println();
		 bookSearchMenu();
	 }
  } //Method ends here
  
  //Search for Books By Name
  private static void searchBooksByName(){
	 Scanner scanUserSearch = new Scanner(System.in);
	 
	 try { 
     //Menu that prompts user to enter their search term
	 System.out.println("========== SEARCH FOR BOOKS BY NAME ==========");
	 System.out.println("Welcome to Search Books by Name ! \n");
	 System.out.println("Please enter your search term:");
	 String searchTerm = scanUserSearch.nextLine();
	 
	 //Buffered reader that can read the text file
	 BufferedReader nameReader = new BufferedReader(new FileReader(bookFile));
	 String[] printBook = new String[4];
	 String line;
	 String searchedID="";
	 String searchedName="";
	 String searchedAuthor="";
	 String searchedCat="";
	 boolean exist = false;

	     System.out.println("The books with names containing " + "\"" + searchTerm + "\"" + " are:");
	     
		 //Reader reads the text file line by line
		 while((line = nameReader.readLine())!= null) {
			 
			 //Changing the contents of the line to an array, splitting the array elements at every comma
			 printBook = line.split(", ");
			 searchedID = printBook[0];
			 searchedName = printBook[1];
			 searchedAuthor = printBook[2];
			 searchedCat = printBook[3];
			 
			//If line contains user's search term, print that line
			 if(searchedName.toLowerCase().contains(searchTerm.toLowerCase())) {
				 exist = true;
				 System.out.println("====================");
				 System.out.println("Book ID: " + searchedID);
				 System.out.println("Name: " + searchedName);
				 System.out.println("Author: " + searchedAuthor);
				 System.out.println("Category: " + searchedCat);
				 System.out.println("====================");
				 }
			 }
			
		     if(exist == true) {
		    	//Menu for users to select next step
				 System.out.println("Now, would you like to: \n1. Search for another book by name? \n2. Search by other methods?");
				 System.out.println("Please enter you choice:");
				 char userInput = scanUserSearch.next().charAt(0);
				 
				 switch(userInput) {
			    	case '1': searchBooksByName();
			    	break;
			    	case '2': bookSearchMenu();
			    	break;
			    	default: System.out.println("\nInvalid command. Redirecting you to the book search menu... \n");
	   	                     bookSearchMenu();              
			    	}       
		     }

			 //If no line contains user's search term, print this
			 if(exist == false){
			    
				//Menu for users to select next step   
			    System.out.println("\nThis book is either not available or does not exist in this library. \n");
			    System.out.println("Now, would you like to: \n1. Search with another name? \n2. Search by other methods?");
			    System.out.println("Please enter you choice:");
			    char input2 = scanUserSearch.next().charAt(0);
			    	
			    	switch(input2) {
			    	case '1': searchBooksByName();
			    	break;
			    	case '2': bookSearchMenu();
			    	break;
			    	default: System.out.println("\nInvalid command. Redirecting you to the book search menu... \n");
			    	         bookSearchMenu();   
			    	}
			 }
		       scanUserSearch.close();
		       nameReader.close();
		 }
		 //If there is any exceptions, print this message and restart the method
		 catch(Exception e) {
		  System.out.println("\n An error has occured, please try again. \n");
		  e.printStackTrace();
		  System.out.println();
		  searchBooksByName();
	     }
  } //Method ends here
  
  //Search for Books by Author Name
  private static void searchBooksByAuthor(){
	 Scanner scanAuthor = new Scanner(System.in);
	 
	 try {
		 
     //Menu that prompts user to enter their search term
	 System.out.println("========== SEARCH FOR BOOKS BY AUTHOR ==========");
	 System.out.println("Welcome to Search for Books by Author! \n");
	 System.out.println("Please enter your search term:");
	 String searchTerm = scanAuthor.next();
	 
	 //Buffered reader that can read the text file
	 BufferedReader authorReader = new BufferedReader(new FileReader(bookFile));
	 String[] printBook = new String[4];
	 String line;
	 String searchedID="";
	 String searchedName="";
	 String searchedAuthor="";
	 String searchedCat="";
	 boolean exist = false;

	     System.out.println("\nThe books written by authors that contain " + "\"" + searchTerm + "\"" + " in their name are: \n");
	     
		 //Reader reads the text file line by line
		 while((line = authorReader.readLine())!= null) {
			 
			 //Changing the contents of the line to an array, splitting the array elements at every comma
			 printBook = line.split(", ");
			 searchedID = printBook[0];
			 searchedName = printBook[1];
			 searchedAuthor = printBook[2];
			 searchedCat = printBook[3];
			 
			//If line contains user's search term, print that line
			 if(searchedAuthor.toLowerCase().contains(searchTerm.toLowerCase())) {
				 exist = true;
				 System.out.println("====================");
				 System.out.println("Book ID: " + searchedID);
				 System.out.println("Name: " + searchedName);
				 System.out.println("Author: " + searchedAuthor);
				 System.out.println("Category: " + searchedCat);
				 System.out.println("====================");
			 }
		  }
			
		    if(exist == true) { 
		    	//Menu for users to select next step
				 System.out.println("Now, would you like to: \n1. Search for another book by name? \n2. Search by other methods?");
				 System.out.println("Please enter you choice:");
				 char userInput = scanAuthor.next().charAt(0);
				 
				 switch(userInput) {
			    	case '1': searchBooksByName();
			    	break;
			    	case '2': bookSearchMenu();
			    	break;
			    	default: System.out.println("\nInvalid command. Redirecting you to the book search menu... \n");
	   	                     bookSearchMenu();              
			    	}       
		     }

			   //If no line contains user's search term, print this
			if(exist == false) {
			    
				//Menu for users to select next step   
			    System.out.println("\nThis book is either not available or does not exist in this library. \n");
			    System.out.println("Now, would you like to: \n1. Search with another name? \n2. Search by other methods?");
			    System.out.println("Please enter you choice:");
			    char input2 = scanAuthor.next().charAt(0);
			    	
			    	switch(input2) {
			    	case '1': searchBooksByName();
			    	break;
			    	case '2': bookSearchMenu();
			    	break;
			    	default: System.out.println("\nInvalid command. Redirecting you to the book search menu... \n");
			    	         bookSearchMenu();   
			    	}
			 }
		     scanAuthor.close();
		     authorReader.close();
		  }
	  //If there is any exceptions, print this message and restart the method
	  catch(Exception e) {
	  System.out.println("\n An error has occured, please try again. \n");
	  e.printStackTrace();
	  System.out.println();
	  searchBooksByName();
   }
  } //Method ends here
  
  //Menu for search books by category
  private static void searchBooksByCategory(){
  
  try {
	  Scanner scanCategory = new Scanner(System.in);
	  char category;
	  char userInput;
	  
	  //Menu for users to choose which category do they want to search
	  do {
		  System.out.println("========== SEARCH FOR BOOKS BY CATEGORY ==========");
		  System.out.println("Welcome to Search for Books by Category! \n");
		  System.out.println("Please choose a category from the list below:");
		  System.out.println("1. Novels \n2. Magazines \n3. Literature \n4. Biographies \n5. Comic Books \n6. Educational Books \n");
		  System.out.println("If you wish to search by other methods, choose '7'.");
		  System.out.println("Please enter you choice:");
		  category = scanCategory.next().charAt(0);
		  
		  //Calls the method to print out books available in the user's searched category
		  switch(category){
	        case '1': bookPrinter(bookFile , "Novel");
	        break;
	        case '2': bookPrinter(bookFile , "Magazine"); 
	        break;
	        case '3': bookPrinter(bookFile , "Literature");
	        break;
	        case '4': bookPrinter(bookFile , "Biography");
	        break;
	        case '5': bookPrinter(bookFile , "Comics");
	        break;
	        case '6': bookPrinter(bookFile , "Educational");
	        break;
	        case '7': bookSearchMenu();
	        break;
	        default: System.out.println("The command entered is invalid, please enter a new command. \n");
	      }  
	  } while(!(category == '1' || category == '2' || category == '3' || category == '4' || category == '5' || category == '6' || category == '7'));
	 
	 //Menu that allows users to select their next step after the available books in their searched category has been printed
	 do{
	 System.out.println("\nNow, would you like to:");
	 System.out.println("1. Search in another category? \n2. Search by other methods?");
	 System.out.println("Please enter you choice:");
	 userInput = scanCategory.next().charAt(0);
	 System.out.println();
	 
	   switch(userInput) {
	   case '1': searchBooksByCategory();
	   break;
	   case '2': bookSearchMenu();
	   break;
	   default: System.out.println("The command entered is invalid, please enter a new command. \n");
	   }
	 } while(userInput != '1' && userInput != '2');
	 scanCategory.close();
  }
  
  //If there is any exceptions, print this message and restart the method
  catch (Exception e) {
	  System.out.println();
	  e.printStackTrace();
	  System.out.println("\n An error has occured, please try again. \n");
	  searchBooksByCategory();
  }
  }
  
  
 //Method to print all the books that are available in the searched category
  private static void bookPrinter(File bookFile, String category){
	 
	 try {
		 
	 //Buffered reader that reads text files
	 BufferedReader readNovel = new BufferedReader(new FileReader(bookFile));
	 String line;
	 boolean exist = false;
	 
	 //Header of the list of available books in the category
	 System.out.println("\nThe available books in this category: \n");
	 
	 //Reader that reads the text file line by line
	 while((line = readNovel.readLine()) != null) {
		 
		//Changing the contents of the line to an array, splitting the array elements at every comma
		 String printNovel[] = line.split(", ");
		 String searchedID = printNovel[0];
		 String searchedName = printNovel[1];
		 String searchedAuthor = printNovel[2];
		 String searchedCat = printNovel[3];
		 
		 //If line contains the searched category, print that line
		 if(searchedCat.contains(category)) {
			 exist = true;
			 System.out.println("====================");
		     System.out.println("Name: " + searchedName);
			 System.out.println("Book ID: " + searchedID);
			 System.out.println("Author: " + searchedAuthor);
			 System.out.println("====================");
		 }
	   }
	 
		 
	     //If line does not contain the chosen category, print this
		 if(exist == false) {
			 Scanner scanFalseInput = new Scanner(System.in);
			 System.out.println("\nThere are currently no books available for this category.");
			 
			 //Menu for users to select next step
			    System.out.println("Now, would you like to: \n1. Search another category? \n2. Search by other methods?");
			    System.out.println("Please enter you choice:");
			    char input3 = scanFalseInput.next().charAt(0);
			    	
			    	switch(input3) {
			    	case '1': searchBooksByName();
			    	break;
			    	case '2': bookSearchMenu();
			    	break;
			    	default: System.out.println("\nInvalid command. Redirecting you to the book search menu... \n");
			    	         bookSearchMenu();   
			    	}
			    scanFalseInput.close();
		 }
	 readNovel.close();
    }
	 //If there is any exceptions, print this message and restart the method
	 catch (Exception e) {
		 System.out.println();
		 e.printStackTrace();
	     System.out.println("\n An error has occured, please try again. \n");
		 searchBooksByCategory();
	}
 } //Method ends here
} //Class ends here
  
    
  





	


