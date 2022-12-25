package Project;

import java.util.List;
import java.util.Scanner;

public class MainMenuOperations {
	//Outer or welcome screen input
	public static void handleWelcomeScreenInput() {
		boolean continueOperation = true;					//to repeat handleWelcomeScreenInput method if user wants
		Scanner sc = new Scanner(System.in);
		do {
			try {
				MainMenu.displayMainMenu();
				int option = sc.nextInt();

				switch (option) {
				case 1:
					//displaying all files when option 1 is chosen on main menu
					displayAllFiles.displayAllFilesMethod("SimpliLearn");
					break;
					
				case 2:
					//calling file handling options on choosing 2 
					MainMenuOperations.handleMainMenuOptions();
					break;
					
				case 3:
					//exit the application on pressing 3
					System.out.println("The application closed successfully");
					continueOperation = false;
					sc.close();
					System.exit(0);
					break;
					
				default:
					System.out.println("Please select available options only");
					
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleWelcomeScreenInput();
			} 
		} while (continueOperation == true);
	}
	
	public static void handleMainMenuOptions() {
		boolean continueOperation = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				MainMenu.displayFileOperationMenu();
				FileHandling.createDirectoryIfNotPresent("SimpliLearn");
				
				int option = sc.nextInt();
				switch (option) {
				case 1:
					// Create a new file
					System.out.println("Enter the name of a file:");
					String newFileName = sc.next();
					FileHandling.createFile(newFileName, sc);
					break;
					
				case 2:
					// delete a file
					System.out.println("Enter the name of a file to delete from \"SimpliLearn\" folder");
					String fileToDelete = sc.next();
					FileHandling.createDirectoryIfNotPresent("SimpliLearn");
					List<String> filesToDelete = FileHandling.displayFileLocation(fileToDelete, "SimpliLearn");
					
						// If idx == 0, delete all files displayed for the name
						for (String path : filesToDelete) {
							FileHandling.deleteFileRecursively(path);
						}
						break;
					
				case 3:
					// File/Folder Search
					System.out.println("Enter the name of a file to search from \"SimpliLearn\" folder");
					String fileName = sc.next();
					
					FileHandling.createDirectoryIfNotPresent("SimpliLearn");
					FileHandling.displayFileLocation(fileName, "SimpliLearn");
					break;
					
				case 4:
					// Go to Previous menu
					return;
					
				case 5:
					// on Exit
					System.out.println("The application closed successfully.");
					continueOperation = false;
					sc.close();
					System.exit(0);
				default:
					System.out.println("Please select available options only");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleMainMenuOptions();
			}
		} while (continueOperation == true);
	}
}
