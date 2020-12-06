import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Customs {
	

	public static void main(String args[]) throws FileNotFoundException {
		ArrayList<String> groups = new ArrayList<String>();
		File inputFile = new File("Day6Customs.txt");
		Scanner scanner = new Scanner(inputFile);

		String line = "";
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if (!nextLine.isEmpty()) {
				line += nextLine;
			} else {
				Scanner lineScanner = new Scanner(line);
				groups.add(line);
		//		System.out.println(line);
				line = "";
			}
		}
		groups.add(line);
		//System.out.println(line);

		int total = 0;
		ArrayList<Character> encounteredQuestions = new ArrayList<Character>();

		for (int i = 0; i < groups.size(); i++) {
			//System.out.println(groups.get(i));
			for (int j = 0; j < groups.get(i).length(); j++) {
				if (!encounteredQuestions.contains(groups.get(i).charAt(j))) {
					encounteredQuestions.add(groups.get(i).charAt(j));
					total++;
				}
			}
			encounteredQuestions.clear();
		}
		System.out.println(total);
	}
}