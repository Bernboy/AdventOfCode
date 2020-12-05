import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;

public class PasswordDebugger {






	public static void debugOldPasswords() {
		int total = 0;

		try {
			File inputFile = new File("Day2Passwords.txt");
			Scanner scanner = new Scanner(inputFile);
			while (scanner.hasNextLine()) {


				String line = scanner.nextLine();
				String[] string1 = line.split(":");

				String[] nums = string1[0].split(" ");
				String[] limitsAsStrings = nums[0].split("-");
				int min = Integer.parseInt(limitsAsStrings[0]);
				int max = Integer.parseInt(limitsAsStrings[1]);
				char delim = nums[1].charAt(0);
				


				int counter = 0;

				for (int i = 0; i < string1[1].length(); i++) {
					if (string1[1].charAt(i) == delim) {
						counter++;
					}
				}
				if (counter >= min && counter <= max) {
					total++;
				}
			}
			System.out.println(total);
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found bozo");
		}
	}

	public static void debugNewPasswords() {
		int total = 0;
		try {
			File inputFile = new File("Day2Passwords.txt");
			Scanner scanner = new Scanner(inputFile);
			while (scanner.hasNextLine()) {


				String line = scanner.nextLine();
				String[] string1 = line.split(":");
				String key = string1[1].trim();
				String[] nums = string1[0].split(" ");
				String[] limitsAsStrings = nums[0].split("-");
				int pos1 = Integer.parseInt(limitsAsStrings[0]) - 1;
				int pos2 = Integer.parseInt(limitsAsStrings[1]) - 1;
				char delim = nums[1].charAt(0);

				int counter = 0;

				if (key.charAt(pos1) == delim) {
					if (key.charAt(pos2) != delim) {
						total++;
					}
				} else if (key.charAt(pos2) == delim) {
					total++;
				}
			}
			System.out.println(total);
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found bozo");
		}

	}


	public static void main(String args[]) {
		//debugOldPasswords();
		//debugNewPasswords();
	}











		
}