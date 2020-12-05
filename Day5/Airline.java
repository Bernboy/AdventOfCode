import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Airline {
	public static ArrayList<String> ticketList = new ArrayList<String>();
	public static File inputFile = new File("Day5Airlines.txt");
	



	public static void main(String args[]) throws FileNotFoundException {
		int biggestYet = 0;

		//ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		// for (int i = 0; i < 128; i++) {
		// 	ArrayList<Integer> newList = new ArrayList<Integer>();
		// 	list.add(newList);
		// 	for (int j = 0; j < 8; j++) {
		// 		list.get(i).add(-1);
		// 	}
		// }

		int[][] intList = new int[128][8];
		for (int i = 0; i < 128; i++) {
			for (int j = 0; j < 8; j++) {
				intList[i][j] = -100;
			}
		}

		Scanner scanner = new Scanner(inputFile);
		while (scanner.hasNextLine()) {
			ticketList.add(scanner.nextLine());
		}

		for (int i = 0; i < ticketList.size(); i++) {
			int row = calculateRow(ticketList.get(i));
			int col = calculateCol(ticketList.get(i));
			intList[row][col] = 1;
			//list.get(row).set(col, 1);
			int temp  = row*8 + col;

			// System.out.println("ROW: " + row);
			// System.out.println("COL: " + col);
			// System.out.println("TEMP: " + temp);
			// System.out.println("BIGGESTYET: " + biggestYet);
			if (temp > biggestYet) {
				biggestYet = temp;
			}
		}
		//System.out.println(biggestYet);


		for (int i = 1; i < 127; i++) {
			for (int j = 0; j < 8; j++) {
				if (intList[i][j] == -100) {
					System.out.println("i = " + i + " j = " + j);
				}
			}
		}




	}

	public static int calculateCol(String data) {
		int min = 0;
		int max = 7;
		int lastBoi = 0;
		for (int i = 7; i < 10; i++) {
			if (data.charAt(i) == 'L') {
				max = (min + max)/2;
				//System.out.println("max = " + max);
			} else {
				min = (min + max)/2 + 1;
				//System.out.println("min = " + min);
			}
		}

		if (data.charAt(9) == 'L') {
			lastBoi = min;
		} else {
			lastBoi = max;
		}
		return lastBoi;
	}

	public static int calculateRow(String data) {
		//System.out.println("NEW");
		int min = 0;
		int max = 127;

		int lastBoi = 0;

		for (int i = 0; i < 7; i++) {
			if (data.charAt(i) == 'F') {
				max = (min + max)/2;
				//System.out.println("max = " + max);
			} else {
				min = (min + max)/2 + 1;
				//System.out.println("min = " + min);

			}
		}

		if (data.charAt(6) == 'F') {
			lastBoi = min;
		} else {
			lastBoi = max;
		}
		return lastBoi;
	}
}