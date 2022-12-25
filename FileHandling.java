package Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

public class FileHandling {
	public static void createDirectoryIfNotPresent(String dirName) {
		File file = new File(dirName);

		// If file doesn't exist, create the SimpliLearn folder
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	public static void createFile(String fileToAdd, Scanner sc) {
		FileHandling.createDirectoryIfNotPresent("SimpliLearn");
		Path pathOfFile = Paths.get("./SimpliLearn/" + fileToAdd);
		try {
			Files.createDirectories(pathOfFile.getParent());
			Files.createFile(pathOfFile);
			System.out.println(fileToAdd + " created successfully");

			System.out.println("Would you like to add any content to the file? (y/n)");
			String choice = sc.next().toLowerCase();

			sc.nextLine();
			if (choice.equals("y")) {
				System.out.println("\n\nInput content and press enter\n");
				String content = sc.nextLine();
				Files.write(pathOfFile, content.getBytes());
				System.out.println("\nContent was written to file " + fileToAdd+" and the file was saved.");
			}

		} catch (IOException e) {
			System.out.println("Failed to create file " + fileToAdd);
			System.out.println(e.getClass().getName());
		}
	}

	public static List<String> displayFileLocation(String fileName, String path) {
		List<String> fileListNames = new ArrayList<>();
		FileHandling.searchFileRecursively(path, fileName, fileListNames);

		if (fileListNames.isEmpty()) {
			System.out.println("\n\n*** Couldn't find any file with given file name \"" + fileName + "\" ***\n\n");
		} else {
			System.out.println("\n\nFile found at the below location");

			List<String> files = IntStream.range(0, fileListNames.size())
					.mapToObj(index -> (index + 1) + ": " + fileListNames.get(index)).collect(Collectors.toList());

			files.forEach(System.out::println);
		}

		return fileListNames;
	}

	public static void searchFileRecursively(String path, String fileName, List<String> fileListNames) {
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<File> filesList = Arrays.asList(files);

		if (files != null && files.length > 0) {
			for (File file : filesList) {

				if (file.getName().startsWith(fileName)) {
					fileListNames.add(file.getAbsolutePath());
				}

				// Need to search in directories separately to ensure all files required are searched
				if (file.isDirectory()) {
					searchFileRecursively(file.getAbsolutePath(), fileName, fileListNames);
				}
			}
		}
	}

	public static void deleteFileRecursively(String path) {

		File currFile = new File(path);
		File[] files = currFile.listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {

				String fileName = file.getName() + " at " + file.getParent();
				if (file.isDirectory()) {
					deleteFileRecursively(file.getAbsolutePath());
				}

				if (file.delete()) {
					System.out.println(fileName + " deleted successfully\n");
				} else {
					System.out.println("Failed to delete " + fileName+"\n");
				}
			}
		}

		String currFileName = currFile.getName() + " at " + currFile.getParent();
		if (currFile.delete()) {
			System.out.println(currFileName + " deleted successfully");
		} else {
			System.out.println("Failed to delete " + currFileName);
		}
	}
}
