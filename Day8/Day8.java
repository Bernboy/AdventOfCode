import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Day8 {

	public static  ArrayList<Integer> visitedInts = new ArrayList<Integer>();
	public static  ArrayList<Instructions> list = new ArrayList<Instructions>();

	public static class Instructions {
		String op;
		int number;

		public Instructions(String op, int number) {
			this.op = op;
			this.number = number;
		}
	}

	public static void main(String args[]) throws FileNotFoundException{
		File inputFile = new File("input.txt");
		Scanner scanner = new Scanner(inputFile);
		String line = "";
		int current = 0;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			String[] newLine = line.split(" ");
			int sign;
			if (newLine[1].charAt(0) == '+') {
				sign = 1;
			} else {
				sign = -1;
			}

			String operation = newLine[0];
			int number = Integer.parseInt(newLine[1].substring(1))*sign;

			Instructions instructionsToAdd = new Instructions(operation, number);
			list.add(instructionsToAdd);
		}

		ArrayList<ArrayList<Instructions>> listOfLists = new ArrayList<ArrayList<Instructions>>();
		for (int i = 0; i < list.size(); i++) {
			ArrayList<Instructions> newList = new ArrayList<Instructions>();
			if (list.get(i).op.equals("jmp")) {
				for (int j = 0; j < list.size(); j++) {
					newList.add(new Instructions(list.get(j).op, list.get(j).number));
				}
				newList.get(i).op = "nop";
				listOfLists.add(newList);
			}
			if (list.get(i).op.equals("nop")) {
				for (int j = 0; j < list.size(); j++) {
					newList.add(new Instructions(list.get(j).op, list.get(j).number));
				}
				newList.get(i).op = "jmp";
				listOfLists.add(newList);
			}
		}

		System.out.println("accumulator before infinite loop is started = " + getTotalBefore(list));
		for (int k = 0; k < listOfLists.size(); k++) {
			int num = getTotalAfter(listOfLists.get(k));
			if (num > 0) {
				System.out.println("accumulator at end of fixed list = " + getTotalAfter(listOfLists.get(k)));
			}
		}
	}


	public static int getTotalAfter(ArrayList<Instructions> list) {
		int total = 0;
		int spot = 0;
		boolean keepGoing = true;
		boolean madeIt = false;
		while (keepGoing) {
			// mark where we are visited
			visitedInts.add(spot);
			// if acc op///
			if (list.get(spot).op.equals("acc")) {
				//increment total
				total += list.get(spot).number;
				// if next spot is no good
				if (visitedInts.contains(spot + 1)) {
					keepGoing = false;
				}
				spot++;
			} else if (list.get(spot).op.equals("jmp")) {
				if (visitedInts.contains(spot + list.get(spot).number)) {
					keepGoing = false;
				}
				spot += list.get(spot).number;
			} else { //nop
				if (visitedInts.contains(spot + 1)) {
					keepGoing = false;
				}
				spot++;
			}
			if (spot >= list.size()) {
				madeIt = true;
				keepGoing = false;
			}
		}
		visitedInts.clear();

		// if we got to end of list, then return accumulator
		if (madeIt) {
			return total;
		} else {
			return -1;
		}
	}

	public static int getTotalBefore(ArrayList<Instructions> list) {
		int total = 0;
		int spot = 0;
		boolean keepGoing = true;
		while (keepGoing) {
			visitedInts.add(spot);
			if (list.get(spot).op.equals("acc")) {
				total += list.get(spot).number;
				if (visitedInts.contains(spot + 1)) {
					keepGoing = false;
				}
				spot++;
			} else if (list.get(spot).op.equals("jmp")) {
				if (visitedInts.contains(spot + list.get(spot).number)) {
					keepGoing = false;
				}
				spot += list.get(spot).number;
			} else { //nop
				if (visitedInts.contains(spot + 1)) {
					keepGoing = false;
				}
				spot++;
			}
		}
		visitedInts.clear();
		return total;
	}
}