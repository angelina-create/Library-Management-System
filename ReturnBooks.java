
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ReturnBooks {

			//declare and initialize array and variable that will be used
			public static String printLFile[] = new String [5]; 
			public static Scanner ReturnInput = new Scanner(System.in);
			public static String returnTerm;
			
			//creating a method to group all other methods together so that just by calling ReturningBooks() everything can be executed.
			public static void ReturningBooks() throws Exception {
				// TODO Group all other methods together
				  ReturnHomePage();
				  AccessingBookRecord();
				  UpdateLibraryFile();
				  delete(); 
				  NextAction();	
			}
		
			//creating a method to show the console interface of return book feature
			private static void ReturnHomePage() throws Exception {
				// print the following lines
				System.out.println("==================== RETURN HOME PAGE ====================");
				System.out.println(" Welcome to the home page of the Returning Book feature!"  );
				System.out.println("==========================================================");
			}
			
			//creating a method to check if the book exist in the borrow record and print out details of the books.
			private static void AccessingBookRecord() throws Exception {
				
			//Checking if the user has any record under the borrow record
			//If yes, means the user did borrow a book before, the details of the book borrowed will be shown
			try {
				BufferedReader printAllReader = new BufferedReader(new FileReader("borrowRecord.txt"));
				String lines;
				String AllFile[] = new String [5];
				boolean notInRecord = true;
				while((lines = printAllReader.readLine())!= null){
		        	AllFile = lines.split(",");
		            if(lines.contains(login_signup.current_user.username)){
		                System.out.println("Book ID: " + AllFile[1]);
		                System.out.println("Name: " + AllFile[2]);
		                System.out.println("Author: " + AllFile[3]);
		                System.out.println("Category: " + AllFile[4]);
		                System.out.println("==========================================================");
		                notInRecord = false;
		            }
				}
				//If the user does not have any borrow record, he/she will be redirected back to the main menu.
		        if (notInRecord) {
		        	System.out.println("You have not borrowed any books.");
		        	System.out.println("Redirecting you back to main menu...");
		        	login_signup.user_menu();
		        }
		        //end of checking borrow record for valid user ID
		        printAllReader.close();
		       
		        //Prompt the user to type in the book ID of the book to be returned
				System.out.println("Please enter the ID of book you want to return: ");
				returnTerm  = ReturnInput.next();
				
				//splitting each row into 5 elements and giving each elements a name for ease of checking later on	
			    BufferedReader returnReader = new BufferedReader(new FileReader("borrowRecord.txt"));
				String line;
				String Username;
				String bookID;
				String bookName;
				String bookAuthor;
				String bookCategory;
				boolean isInRecord = false;
				while((line = returnReader.readLine())!= null) {
					printLFile = line.split(", ");
					Username = printLFile[0];
					bookID = printLFile[1];
					bookName = printLFile[2];
					bookAuthor = printLFile[3];
					bookCategory = printLFile[4];
					 
					//Check whether if the person who borrows the book is the one who is returning the book now 
					if (Username.contains(login_signup.current_user.username)) {
						if(bookID.equals(returnTerm)) {
							isInRecord = true;
							break;
						 }
					 }
				 }
				//If the logged in user's borrow record does not have the book he/she wish to return, the user will be redirecting back to the main menu
				 if(!isInRecord){
					 System.out.println("The book is not in record.\nRedirecting you back to main menu...");
					 login_signup.user_menu();
					 }
				 //end of checking for identity of borrower and returner
				 returnReader.close();
				}
			//If there is any input/output interruptions, system will print out "could not access the book" along with the stack trace for the ease of debugging.
			catch(IOException e){ 
				System.out.println("Could not access the book");
				e.printStackTrace();
				}
			}
			
			//creating a method to add the returned book back to the library inventory
			private static void UpdateLibraryFile() throws IOException {
					
					//to write to the library text file with the book details of the book to be returned
					FileWriter myWriter = new FileWriter("booklist.txt", true);
			        for (int i = 1; i < printLFile.length; i++) {
			            myWriter.write(printLFile[i]);
			            if (i < (printLFile.length-1)) {
			                myWriter.write(", ");    
			            }
			           
			        }
			        myWriter.write("\n");
			        myWriter.close();
			        System.out.println("--------------------------------------------------------");
					System.out.println("        Successfully updated to the Library File.       ");
					System.out.println("--------------------------------------------------------");
					//end of writing to the library file
			}
			
			//creating a method to read and count number of the records in the borrow file 
			private static int txtrow() throws IOException {
				int count = 0;
		        Scanner checkrow = new Scanner(new File("borrowRecord.txt"));
				while(checkrow.hasNextLine()) {
					checkrow.nextLine();
				    count++;
				}
				checkrow.close();
				return count;
				//end of counting how many rows there are in the text file
			}
			
			//creating a method to delete a specific record in the borrow file
			private static void delete() throws Exception {
				
				//Create a double dimension Array List containing all of the current borrow record because the array is dynamic. 
				ArrayList<ArrayList<String> > BorrowedbookArrayList = new ArrayList<ArrayList<String> >();
				Scanner delete = new Scanner(new File("borrowRecord.txt"));
				//Initializing two dimensional array as we know the columns but not the row
				String [][] Borrowedbookarray = new String [txtrow()][5];
				int row = 0;
				
				//Read lines of the borrow list file and adding them into an array with index 
				while (delete.hasNextLine()) {
					String booklist = delete.nextLine();
				    String[] Borrowedbooklistarray = booklist.split(", ");
				    for (int i = 0; i < Borrowedbooklistarray.length; i++) {
				    	Borrowedbookarray[row][i] = Borrowedbooklistarray[i];
				    }
				    row++;
				}
				// this two for loops is to add the array into a two dimensional array list
				for (int i = 0; i < Borrowedbookarray.length; i++) {
					BorrowedbookArrayList.add(new ArrayList<String>());
					for (int j = 0; j < Borrowedbookarray[0].length; j++) {
						BorrowedbookArrayList.get(i).add(j,Borrowedbookarray[i][j]);
					}
				}
				// search for the array containing the same id and remove the said array
				delete.close();
				for (int i = 0; i < BorrowedbookArrayList.size(); i++) {
					if (BorrowedbookArrayList.get(i).get(1).equals(returnTerm)) {
						BorrowedbookArrayList.remove(i);
					}
				}
				
				//rewrite the updated book list back into the text file
				new FileWriter ("borrowRecord.txt", false).close();
				FileWriter writer = new FileWriter("borrowRecord.txt", true);
				for (int i = 0; i < BorrowedbookArrayList.size(); i++) {
					for (int j = 0; j < Borrowedbookarray[0].length; j++) {
						writer.write(BorrowedbookArrayList.get(i).get(j));
				        if (j < (Borrowedbookarray[0].length-1)) {
				        	writer.write(", ");
				        }
					}
					writer.write("\n");
				}
				writer.close();
				System.out.println("--------------------------------------------------------");
				System.out.println("        The book has successfully been returned.        ");
				System.out.println("--------------------------------------------------------");
				//end of deleting the book record from the borrow record
			}
			
			// End of the feature, redirecting back to main menu
			private static void NextAction() {
						try {
							System.out.println("Redirecting you back to main menu...");	
							login_signup.user_menu();
						}
						catch (Exception e){
							System.out.println("Something went wrong");
							e.printStackTrace();
						}
					
			}
}