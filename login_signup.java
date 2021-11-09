
import java.util.Scanner;
import java.io.*;

public class login_signup {
	
	public class current_user {
		public static String username = null;
		public static String accType = null;
	}
	
	public static void clear() {
		try {
			new FileWriter ("UserData.txt", false).close();
		}
		catch (Exception e) {
			System.out.println("ERROR");
			System.out.println("Please try again.");
			change_pass();
		}
	}
	
	public static void change_pass() {
		try {
			Scanner input = new Scanner(System.in);
			Scanner checkline = new Scanner(new File("UserData.txt"));
			Scanner check = new Scanner(new File("UserData.txt"));
			int lines = 0;
			while (checkline.hasNextLine()) {
				checkline.nextLine();
				lines++;
			}
			String[][] temp_array = new String[lines][3];
			while (check.hasNextLine()) {
				for (int i=0; i<lines; i++){
					String userdata = check.nextLine();
					String[] userdataArray = userdata.split(", ");
					temp_array[i][0] = userdataArray[0];
					temp_array[i][1] = userdataArray[1];
					temp_array[i][2] = userdataArray[2];
				}
			for (int i=0; i<temp_array.length; i++) {
				if (current_user.username.equals(temp_array[i][0])) {
					System.out.println("Current password: ");
					String current_pass = input.next();
					while (true) {
						if (current_pass.equals(temp_array[i][1])) {
							while (true) {
								System.out.println("New Password: ");
								String new_pass = input.next();
								System.out.println("Re-type new Password: ");
								String re_new_pass = input.next();
								if (new_pass.equals(re_new_pass)) {
									temp_array[i][1] = new_pass;
									clear();
									FileWriter writer = new FileWriter("UserData.txt", true);
									for (int p=0; p<temp_array.length; p++) {
										for (int j=0; j<3; j++) {
											writer.write(temp_array[p][j]);
											if (j < 2) {
												writer.write(", ");
											}
										}
										writer.write("\n");
									}
									writer.close();
									System.out.println("Password has been changed.");
									System.out.println("Please re-login with new password.");
									login();
									break;
								}
								else {
									System.out.println("Passwords do not match.");
									System.out.println("Please re-type your new passwords.");
									continue;
								}
							}
							break;
						}
						else {
							System.out.println("Password entered is incorrect.");
							System.out.println("Please re-type your current password.");
							change_pass();
						}
					}
				}
			}
		}
		input.close();
		}
		catch (Exception e) {
			System.out.println("ERROR");
			System.out.println("Please try again.");
			change_pass();
		}
	}
	
    public static void login() {
        try {
			Scanner input = new Scanner(System.in);
			Scanner check = new Scanner(new File("UserData.txt"));
			String username, password;
			System.out.println("============ LOG IN ============");
			System.out.println("Username: ");
			username = input.next();
			System.out.println("Password: ");
			password = input.next();
			boolean exist = false;
			while (check.hasNextLine()) {
				String userdata = check.nextLine();
				String[] userdataArray = userdata.split(", ");
				if (username.equals(userdataArray[0]) && password.equals(userdataArray[1])) {
					exist = true;
					current_user.username = userdataArray[0];
					current_user.accType = userdataArray[2];
					if (userdataArray[2].equals("Admin")) {
						admin_menu();
					}
					else {
						user_menu();
					}
					break;
				}
			}
			if (!exist) {
				System.out.println("Invalid Username or Password.");
				System.out.println("Please re-type your username and password.");
				login();
			}
			input.close();
		}
		catch (Exception e) {
			System.out.println("ERROR");
			System.out.println("Please try again");
			login();
		}
    }

    public static void signup() {
        try {
			Scanner input = new Scanner(System.in);
			Scanner check = new Scanner(new File("UserData.txt"));
			String username, password, re_password;
			String accType = null;
			String[] newuserdata = new String[3];
			System.out.println("============ SIGN UP ============");
			System.out.println("Username: ");
			username = input.next();
			while (check.hasNextLine()) {
				String userdata = check.nextLine();
				String[] userdataArray = userdata.split(", ");
				if (username.equals(userdataArray[0])) {
					System.out.println("The username already taken.");
					System.out.println("Please choose another username.");
					signup();
					break;
				}
			}
			while (true) {
				System.out.println("Password: ");
				password = input.next();
				System.out.println("Confirm Password: ");
				re_password = input.next();
				if (password.equals(re_password)) {
					System.out.println("1. Admin" + "\n" + "2. User");
					System.out.println("Pick your account type: ");
					boolean isValid = false;
					while (!isValid) {
						int type = input.nextInt();
						switch (type) {
							case 1:
								accType = "Admin";
								isValid = true;
								break;

							case 2:
								accType = "User";
								isValid = true;
								break;

							default:
								System.out.println("Invalid Command.");
								System.out.println("Please enter a correct command.");
								System.out.println("1. Admin" + "\n" + "2. User");
								System.out.println("Pick your account type: ");
								continue;
						}
					}
				newuserdata[0] = username;
				newuserdata[1] = password;
				newuserdata[2] = accType;
				FileWriter writer = new FileWriter("UserData.txt", true);
				for (int i = 0; i < newuserdata.length; i++) {
					writer.write(newuserdata[i]);
					if (i < 2) {
						writer.write(", ");
					}
				}
				writer.write("\n");
				writer.close();
				System.out.println("Registration successful. Press log in with your new account.");
				login();
				break;
				}
				if (!password.equals(re_password)) {
					System.out.println("Passwords do not match.");
					System.out.println("Please re-type your passwords.");
					continue;
				}
			}
		input.close();
		}
		catch (Exception e) {
			System.out.println("ERROR");
			System.out.println("Please try again");
			signup();
		}
	}

    public static void admin_menu() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("============ MAIN MENU ============");
			System.out.println("1. Manage books");
			System.out.println("2. Check borrow record of all users");
			System.out.println("3. Search books");
			System.out.println("4. Change account password");
			System.out.println("5. Logout");
			System.out.println("Pick your choice: ");
			int choice = input.nextInt();
			switch (choice) {
				case 1:
					bookproperties.menu();
					break;
					
				case 2:
					checkRecord.borrowRecordMainMenu();
					break;
					
				case 3:
					BookQuery.bookSearchMainMenu();
					break;
					
				case 4:
					change_pass();
					break;
				
				case 5:
					while (true) {
						System.out.println("Are you sure?");
						System.out.println("1. Yes" + "\n" + "2. No");
						System.out.println("Pick your choice: ");
						int yesno = input.nextInt(); 
						if (yesno == 1) {
							System.out.println("Logout success.");
							first_menu();
							break;
						}
						else if (yesno == 2) {
							admin_menu();
							break;
						}
						else {
							System.out.println("Invalid Command.");
							System.out.println("Please enter a valid command.");
							continue;
						}
					}

				default:
					System.out.println("ERROR.");
					System.out.println("Please try again.");
					admin_menu();
			}
			input.close();
		}
    	catch (Exception e) {
			System.out.println("ERROR.");
			System.out.println("Please try again.");
			admin_menu();
		}
    }
    
    public static void user_menu() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("============ MAIN MENU ============");
			System.out.println("1. Borrow books");
			System.out.println("2. Return books");
			System.out.println("3. Check borrow record");
			System.out.println("4. Search books");
			System.out.println("5. Change account password");
			System.out.println("6. Logout");
			System.out.println("Pick your choice: ");
			int choice = input.nextInt();
			switch (choice) {
				case 1:
					borrowBook.borrowBooks();
					break;
					
				case 2:
					ReturnBooks.ReturningBooks();
					break;
					
				case 3:
					checkRecord.borrowRecordMainMenu();
					break;
				
				case 4:	
					BookQuery.bookSearchMainMenu();
					break;
					
				case 5:
					change_pass();
					break;
				
				case 6:
					while (true) {
						System.out.println("Are you sure?");
						System.out.println("1. Yes" + "\n" + "2. No");
						System.out.println("Pick your choice: ");
						int yesno = input.nextInt(); 
						if (yesno == 1) {
							System.out.println("Logout success.");
							first_menu();
							break;
						}
						else if (yesno == 2) {
							user_menu();
							break;
						}
						else {
							System.out.println("Invalid Command.");
							System.out.println("Please enter a valid command.");
							continue;
						}
					}
				
				default:
					System.out.println("Invalid Command.");
					System.out.println("Please enter a valid command.");
					user_menu();
			}
			input.close();
		}
    	catch (Exception e) {
			System.out.println("ERROR.");
			System.out.println("Please try again.");
			user_menu();
		}
    }
    
	public static void first_menu() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("========== WELCOME TO LIBRARY SYSTEM ==========");
			System.out.println("1. Log in" + "\n" + "2. Sign Up");
			System.out.println("Pick your choice: ");
			int choice = input.nextInt();
			switch (choice) {
				case 1:
					login();
					break;

				case 2:
					signup();
					break;
				
				default:
					System.out.println("Invalid Command.");
					System.out.println("Please enter a valid command.");
					first_menu();
			}
			input.close();
		}
		catch (Exception e) {
			System.out.println("ERROR");
			System.out.println("Please try again.");
			first_menu();
		}
    }

    public static void main(String[] args) {
		first_menu();
	}
}