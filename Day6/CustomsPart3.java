import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class CustomsPart3 {
	
	public static void main(String args[]) throws FileNotFoundException {
		File inputFile = new File("Day6Customs.txt");
		Scanner scanner = new Scanner(inputFile);
		Set<Character> token = new HashSet<Character>();
		Set<Character> temp = new HashSet<Character>();

		String line = "";
		int total = 0;
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if (!nextLine.isEmpty()) {
				line += nextLine;
				line += " ";
			} else {
				Scanner lineScanner = new Scanner(line);
				if (lineScanner.hasNext()) {
					String next = lineScanner.next();
					for (int i = 0; i < next.length(); i++) {
						token.add(next.charAt(i));
					}
				}
				while (lineScanner.hasNext()) {
					String next = lineScanner.next();
					for (int i = 0; i < next.length(); i++) {
						temp.add(next.charAt(i));
					}
					token.retainAll(temp);
					temp.clear();
				}
				line = "";
				total += token.size();
				token.clear();
			}
		}
		Scanner lineScanner = new Scanner(line);
		if (lineScanner.hasNext()) {
			String next = lineScanner.next();
			for (int i = 0; i < next.length(); i++) {
				token.add(next.charAt(i));
			}	
		}
		while (lineScanner.hasNext()) {
			String next = lineScanner.next();
			for (int i = 0; i < next.length(); i++) {
				temp.add(next.charAt(i));
			}
			token.retainAll(temp);
		}
		total += token.size();
		System.out.println(total);
	}
}