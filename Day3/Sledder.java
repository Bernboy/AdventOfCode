import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;


public class Sledder {


	public static int slopeFinder(ArrayList<String> myList, int increment) {
		int length = myList.get(0).length();
		int currIndex = 0;
		int trees = 0;

		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).charAt(currIndex%length) == '#') {
				trees++;
			}
			currIndex += increment;
		}

		return trees;
	}

	public static int slopeFinder(ArrayList<String> myList, int rowIncrement, int colIncrement) {
		int length = myList.get(0).length();
		int currIndex = 0;
		int trees = 0;

		for (int i = 0; i < myList.size(); i += colIncrement) {
			if (myList.get(i).charAt(currIndex%length) == '#') {
				trees++;
			}
			currIndex += rowIncrement;
		}

		return trees;
	}

	public static void main(String args[]) throws FileNotFoundException{
		ArrayList<String> slope = new ArrayList<String>();
		File inputFile = new File("Day3Slopes.txt");
		Scanner scanner = new Scanner(inputFile);
		while (scanner.hasNextLine()) {
			slope.add(scanner.nextLine());
		}
		
		int slope11 = slopeFinder(slope, 1);
		int slope31 = slopeFinder(slope, 3);
		int slope51 = slopeFinder(slope, 5);
		int slope71 = slopeFinder(slope, 7);
		int slope12 = slopeFinder(slope, 1, 2);

		System.out.println("Slope11 = " + slope11);
		System.out.println("Slope31 = " + slope31);
		System.out.println("Slope51 = " + slope51);
		System.out.println("Slope71 = " + slope71);
		System.out.println("Slope12 = " + slope12);

		System.out.println(slope11*slope31*slope51*slope71*slope12);
	}
		
}