package Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class displayAllFiles {
	public static void displayAllFilesMethod(String path) {
		FileHandling.createDirectoryIfNotPresent("SimpliLearn");
		// displaying all files in every directories
		System.out.println("***Displaying all files with directory structure in ascending order***");

		// listFilesInDirectory displays files along with folder structure
		List<String> filesListNames = displayAllFiles.listFilesDirectoryWise(path, 0, new ArrayList<String>());

		System.out.println("***Displaying all files in ascending order***");
		if(filesListNames.isEmpty())
			System.out.println("No file found!!!\n");
		else {
			Collections.sort(filesListNames);
		}
		filesListNames.stream().forEach(System.out::println);
	}
	public static List<String> listFilesDirectoryWise(String path, int indentationCount, List<String> fileListNames) {
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<File> filesList = Arrays.asList(files);

		Collections.sort(filesList);

		if (files != null && files.length > 0) {
			for (File file : filesList) {

				System.out.print(" ".repeat(indentationCount * 2));

				if (file.isDirectory()) {
					
					System.out.println("`--- " + file.getName());
					// Recursively indent and display the files
					fileListNames.add(file.getName());
					listFilesDirectoryWise(file.getAbsolutePath(), indentationCount + 1, fileListNames);
				} else {
					System.out.println("|--- " + file.getName());
					fileListNames.add(file.getName());
				}
			}
		} else {
			System.out.print(" ".repeat(indentationCount * 2));
			System.out.println("The directory is empty");
		}
		System.out.println();
		return fileListNames;
	}
}
