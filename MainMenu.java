package Project;

public class MainMenu {
	
	//main menu method to display Super options
	public static void displayMainMenu() {
		System.out.println("************************\n******  LockedMe  ******\n************************"+"\n\n");
		System.out.println("Enter suitable number to perform operation: ");
		System.out.println("1. List all files\n"+
						   "2. Perform File Operations\n"+
						   "3. Exit\n");		
	}
	
	//file menu options to create, delete or search files
	public static void displayFileOperationMenu() {
		System.out.println("************************\n******   LockedMe  ******\n************************"+"\n\n");
		System.out.println("Enter suitable number to perform operation: ");
		System.out.println("1. Add files\n"+
						   "2. Delete file\n"+
						   "3. Search files\n"+
						   "4. Exit\n");

	}
	
}
