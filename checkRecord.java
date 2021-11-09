
import java.util.Scanner;
import java.io.*;

public class checkRecord {
    
	private static File bookFile = new File("borrowRecord.txt");
    
	public static void borrowRecordMainMenu() throws IOException{
        if (login_signup.current_user.accType.equals("Admin")) {
        	Admin(bookFile);
        }
        else {
        	user(bookFile);
        }
    }

    private static void user(File bookFile) throws IOException {
        BufferedReader authorReader = new BufferedReader(new FileReader(bookFile));
        String username, line;
        String printFile[] = new String [5];
        int numInput;
        boolean isInRecord = false;
        
        Scanner in = new Scanner(System.in);
        username = login_signup.current_user.username;
        
        System.out.println("=========== Borrowing History ===========");
        while((line = authorReader.readLine())!= null){
        	printFile = line.split(",");
            if(line.contains(username)){
                System.out.println("Book ID: " + printFile[1]);
                System.out.println("Name: " + printFile[2]);
                System.out.println("Author: " + printFile[3]);
                System.out.println("Category: " + printFile[4]);
                System.out.println("=========================================");
                isInRecord = true;
            }
        }
        if (!isInRecord) {
        	System.out.println("You have not borrow any books from the library.");
        }
        do {
            System.out.println("Press 1 to return main menu.");
            numInput = in.next().charAt(0);
            switch(numInput) {
               case '1': login_signup.user_menu();
               break;
               default: System.out.println("The command entered is invalid, please enter a new command. \n");
            }
        } while(numInput != '1');
        authorReader.close();
        in.close();
    }

    private static void Admin(File bookFile) throws IOException  {
        BufferedReader reader = new BufferedReader(new FileReader(bookFile));
        Scanner sc = new Scanner(System.in);
        String printFile[] = new String [5];
        String line;
        int numInput;
        boolean isInRecord = false;
        
        System.out.println("=========Borrowing history of all users==========");
     
        while ((line = reader.readLine())!=null) {
        	printFile = line.split(",");
        	System.out.println("Username: " + printFile[0]);
        	System.out.println("Book ID: " + printFile[1]);
            System.out.println("Name: " + printFile[2]);
            System.out.println("Author: " + printFile[3]);
            System.out.println("Category: " + printFile[4]);
            System.out.println("=============================================");
            isInRecord = true;
        }
        if (!isInRecord) {
        	System.out.println("None of the books are borrowed from the library.");
        }
        do {
            System.out.println("Press 1 to return main menu.");
            numInput = sc.next().charAt(0);
            switch(numInput) {
               case '1': login_signup.admin_menu();
               break;
               default: System.out.println("The command entered is invalid, please enter a new command. \n");
            }
        } while(numInput != '1');
        reader.close();
        sc.close();
    }
    
}