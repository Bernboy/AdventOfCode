import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;
import java.lang.Math;
import java.util.List;

public class Day11Part2 {

	public static final int FLOOR = 2;
	public static final int OCCUPIED_SEAT = 1;
	public static final int OPEN_SEAT = 0;

	public static int[][] seatingArray;// = new int[][];
	public static int[][] modArray;

	public static int turnCount = 0;

	public static int[][] readInArray(Scanner scanner, int length) {
		String firstLine = scanner.nextLine();
		String[] firstArray = new String[length];
		Scanner lineScanner = new Scanner(firstLine);
		firstArray[0] = firstLine;
		int counter = 1;
		while (scanner.hasNextLine()) { 
			firstArray[counter] = scanner.nextLine();
			counter++;
		}
		int[][] intArray = new int[firstArray.length][firstArray[0].length()];
		for (int i = 0; i < firstArray.length; i++) {
			// System.out.println(firstArray[i]);
			for (int j = 0; j < firstArray[i].length(); j++) {
				char[] charArray = firstArray[i].toCharArray();
				intArray[i][j] = charArray[j] == ('L') ? OPEN_SEAT : FLOOR;
				// System.out.print(intArray[i][j]);
			}
			// System.out.println();
		}
		return intArray;
	}

	public static void printSeatingArray() {
		for (int i = 0; i < seatingArray.length; i++) {
			for (int j = 0; j < seatingArray[0].length; j++) {
				System.out.print(seatingArray[i][j]);
			}
			System.out.println();
		}
	}

	public static int checkLeftForOpenSeats(int i, int j) {
		int counter = 0;
		if (j == 0) {
			return 0;
		}
		if (i == 0) {
			if (seatingArray[i][j-1] == 0) {
				counter++;
			}
			if (seatingArray[i+1][j-1] == 0) {
				counter++;
			}
		} else if (i == seatingArray.length - 1) {
			if (seatingArray[i][j-1] == 0) {
				counter++;
			}
			if (seatingArray[i-1][j-1] == 0) {
				counter++;
			}
		} else {
			if (seatingArray[i][j-1] == 0) {
				counter++;
			}
			if (seatingArray[i-1][j-1] == 0) {
				counter++;
			}
			if (seatingArray[i+1][j-1] == 0) {
				counter++;
			}
		}
		return counter;
	}


	public static int checkRightForOpenSeats(int i, int j) {
		int counter = 0;
		if (j == seatingArray[i].length - 1) {
			return 0;
		}
		if (i == 0) {
			if (seatingArray[i][j+1] == 0) {
				counter++;
			}
			if (seatingArray[i+1][j+1] == 0) {
				counter++;
			}
		} else if (i == seatingArray.length - 1) {
			if (seatingArray[i][j+1] == 0) {
				counter++;
			}
			if (seatingArray[i-1][j+1] == 0) {
				counter++;
			}
		} else {
			if (seatingArray[i][j+1] == 0) {
				counter++;
			}
			if (seatingArray[i-1][j+1] == 0) {
				counter++;
			}
			if (seatingArray[i+1][j+1] == 0) {
				counter++;
			}
		}
		return counter;
	}

	public static int checkAboveForOpenSeats(int i, int j) {
		if (i == 0) {
			return 0;
		} else {
			if (seatingArray[i-1][j] == 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static int checkBelowForOpenSeats(int i, int j) {
		if (i == seatingArray.length - 1) {
			return 0;
		} else {
			if (seatingArray[i+1][j] == 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static int checkAboveForTakenSeats(int i, int j) {
		if (i == 0) {
			return 0;
		} else {
			if (seatingArray[i-1][j] == OCCUPIED_SEAT) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static int checkBelowForTakenSeats(int i, int j) {
		if (i == seatingArray.length - 1) {
			return 0;
		} else {
			if (seatingArray[i+1][j] == OCCUPIED_SEAT) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public static int checkLeftForTakenSeats(int i, int j) {
		int counter = 0;
		if (j == 0) {
			return 0;
		}
		if (i == 0) {
			if (seatingArray[i][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i+1][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
		} else if (i == seatingArray.length - 1) {
			if (seatingArray[i][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i-1][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
		} else {
			if (seatingArray[i][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i-1][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i+1][j-1] == OCCUPIED_SEAT) {
				counter++;
			}
		}
		return counter;
	}


	public static int checkRightForTakenSeats(int i, int j) {
		int counter = 0;
		if (j == seatingArray[i].length - 1) {
			return 0;
		}
		if (i == 0) {
			if (seatingArray[i][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i+1][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
		} else if (i == seatingArray.length - 1) {
			if (seatingArray[i][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i-1][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
		} else {
			if (seatingArray[i][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i-1][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
			if (seatingArray[i+1][j+1] == OCCUPIED_SEAT) {
				counter++;
			}
		}
		return counter;
	}

	public static int checkNeighborsTakenSeats(int i, int j) {

		
		// if (i == 0 && j == 3) {
		// 	System.out.println("kjabdlkgba");
		// 	for (int m = 0; m < seatingArray[i].length; m++) {
		// 		System.out.print(seatingArray[i][m]);	
		// 	}
		// 	System.out.println();
		// 	System.out.println(checkAbove(i, j));
		// 	System.out.println(checkBelow(i, j));
		// 	System.out.println(checkUpperLeftDiag(i, j));
		// 	System.out.println(checkLowerLeftDiag(i, j));
		// 	System.out.println(checkLowerRightDiag(i, j));
		// 	System.out.println(checkUpperRightDiag(i, j));
		// 	System.out.println(checkRight(i, j));
		// 	System.out.println(checkLeft(i, j));
		// 	System.out.println("@ ij " + seatingArray[0][0]);
		// }
		int total = 0;
		total += checkAbove(i, j);
		total += checkBelow(i, j);
		total += checkUpperLeftDiag(i, j);
		total += checkLowerLeftDiag(i, j);
		total += checkLowerRightDiag(i, j);
		total += checkUpperRightDiag(i, j);
		total += checkRight(i, j);
		total += checkLeft(i, j);
		// System.out.println("total = " + total);
		return total;
	}


	// public static int checkNeighborsOpenSeats(int i, int j) {
	// 	int total = 0;
	// 	total += checkRightForOpenSeats(i, j);
	// 	total += checkLeftForOpenSeats(i, j);
	// 	total += checkBelowForOpenSeats(i, j);
	// 	total += checkAboveForOpenSeats(i, j);
	// 	return total;
	// }






	public static int checkAbove(int i, int j) {
		if (i == 0) {
			return 0;
		}

		for (int m = i - 1; m >= 0; m--) {
			// System.out.println("in check above and the value is " + seatingArray[m][j] + " " + m + " " + j);
			if (seatingArray[m][j] == OCCUPIED_SEAT) {
				// System.out.println("checkabove");
				return 1;
			} else if (seatingArray[m][j] == OPEN_SEAT) {
				return 0;
			}
		}
		return 0;
	}

	public static int checkBelow(int i, int j) {
		if (i == seatingArray.length - 1) {
			return 0;
		}

		for (int m = i + 1; m < seatingArray.length; m++) {
			// System.out.println("in check below and the value is " + seatingArray[m][j]);
			if (seatingArray[m][j] == OCCUPIED_SEAT) {
				// System.out.println("checkbelow");
				return 1;
			} else if (seatingArray[m][j] == OPEN_SEAT) {
				return 0;
			}
		}
		return 0;
	}

	public static int checkRight(int i, int j) {
		if (j == seatingArray[i].length - 1) {
			return 0;
		}

		for (int m = j + 1; m < seatingArray[i].length; m++) {
			// System.out.println("in check right and the value is " + seatingArray[m][j]);
			if (seatingArray[i][m] == OCCUPIED_SEAT) {
				// System.out.println("checkright");
				return 1;
			} else if (seatingArray[i][m] == OPEN_SEAT) {
				return 0;
			}
		}
		return 0;
	}

	public static int checkLeft(int i, int j) {
		if (j == 0) {
			return 0;
		}

		for (int m = j - 1; m >= 0; m--) {
			// System.out.println("in check left and the value is " + seatingArray[m][j]);
			if (seatingArray[i][m] == OCCUPIED_SEAT) {
				// System.out.println("checkleft");
				return 1;
			} else if (seatingArray[i][m] == OPEN_SEAT) {
				return 0;
			}
		}
		return 0;
	}

	public static int checkUpperRightDiag(int i, int j) {
		if (j == seatingArray[i].length - 1 || i == 0) {
			return 0;
		}
		j++;
		i--;
		while (j <= seatingArray[0].length - 1 && i >= 0) {
			// System.out.println("in check upper right and the value is " + seatingArray[i][j]);
			
			if (seatingArray[i][j] == OCCUPIED_SEAT) {
				// System.out.println("checkUR");
				return 1;
			} else if (seatingArray[i][j] == OPEN_SEAT) {
				return 0;
			}
			j++;
			i--;
		}
		return 0;
	}

	public static int checkUpperLeftDiag(int i, int j) {
		if (j == 0 || i == 0) {
			return 0;
		}
		j--;
		i--;
		while (j >= 0 && i >= 0) {
			// System.out.println("in check upper left and the value is " + seatingArray[i][j]);
			if (seatingArray[i][j] == OCCUPIED_SEAT) {
				// System.out.println("checkUL");
				return 1;
			} else if (seatingArray[i][j] == OPEN_SEAT) {
				return 0;
			}
			j--;
			i--;
		}
		return 0;
	}

	public static int checkLowerLeftDiag(int i, int j) {
		if (j == 0 || i == seatingArray.length - 1) {
			return 0;
		}
		j--;
		i++;
		while (j >= 0 && i < seatingArray.length) {
			// System.out.println("in check lower left and the value is " + seatingArray[i][j]);
			if (seatingArray[i][j] == OCCUPIED_SEAT) {
				// System.out.println("checkLL");
				return 1;
			} else if (seatingArray[i][j] == OPEN_SEAT) {
				return 0;
			}
			j--;
			i++;
		}
		return 0;
	}

	public static int checkLowerRightDiag(int i, int j) {
		if (j == seatingArray[i].length - 1 || i == seatingArray.length - 1) {
			return 0;
		}
		j++;
		i++;
		while (j < seatingArray[0].length && i < seatingArray.length) {
			// System.out.println("in check lower right and the value is " + seatingArray[i][j]);
			if (seatingArray[i][j] == OCCUPIED_SEAT) {
				// System.out.println("checkLR");
				return 1;
			} else if (seatingArray[i][j] == OPEN_SEAT) {
				return 0;
			}
			j++;
			i++;
		}
		return 0;
	}








	public static int[][] makeModArray() {
		int[][] changeArray = new int[seatingArray.length][seatingArray[0].length];
		for (int i = 0; i < seatingArray.length; i++) {
			for (int j = 0; j < seatingArray[i].length; j++) {
				if (seatingArray[i][j] == OPEN_SEAT) {
					//System.out.println(checkNeighborsTakenSeats(i,j));
					if (checkNeighborsTakenSeats(i, j) == 0) {
						changeArray[i][j] = 1;
					}
				} else if (seatingArray[i][j] == OCCUPIED_SEAT) {
					if (checkNeighborsTakenSeats(i, j) > 4) {
						changeArray[i][j] = 1;
					}
				}
				//System.out.print(changeArray[i][j]);
			}
			//System.out.println();
		}
		return changeArray;
	}

	public static void turn() {
		for (int i = 0; i < seatingArray.length; i++) {
			for (int j = 0; j < seatingArray[i].length; j++) {
				if (modArray[i][j] == 1) {
					changeArray(i, j);
				}
			}
		}
		turnCount++;
	}

	public static void changeArray(int i, int j) {
		if (seatingArray[i][j] == OPEN_SEAT) {
			seatingArray[i][j] = OCCUPIED_SEAT;
		} else if (seatingArray[i][j] == OCCUPIED_SEAT) {
			seatingArray[i][j] = OPEN_SEAT;
		}
	}

	public static int sumArray(int[][] array) {
		int total = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				total += array[i][j];
			}
		}
		return total;
	}

	public static boolean arraysEqual(int[][] array1, int[][] array2) {
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array1[i].length; j++) {
				if (array1[i][j] != array2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[][] cloneArray(int[][] arrayToClone) {
		int[][] newArray = new int[arrayToClone.length][arrayToClone[0].length];
		for (int i = 0; i < arrayToClone.length; i++) {
			for (int j = 0; j < arrayToClone[i].length; j++) {
				newArray[i][j] = arrayToClone[i][j];
			}
		}
		return newArray;
	}

	public static int findOccupied() {
		int total = 0;
		for (int i = 0; i < seatingArray.length; i++) {
			for (int j = 0; j < seatingArray[i].length; j++) {
				if (seatingArray[i][j] == OCCUPIED_SEAT) {
					total ++;
				}
			}
		}
		return total;
	}
	public static void main(String args[]) throws FileNotFoundException{
		File inputFile = new File("input.txt");
		Scanner scanner = new Scanner(inputFile);
		Scanner counterScanner = new Scanner(inputFile);
		int counter = 0;
		while (counterScanner.hasNextLine()) {
			counterScanner.nextLine();
			counter++;
		}	
		seatingArray = readInArray(scanner, counter);

		//printSeatingArray();
		//System.out.println("NEW LINE");

		int[][] oldArray = new int[seatingArray.length][seatingArray[0].length];

		while (!arraysEqual(oldArray, seatingArray)) {
			oldArray = cloneArray(seatingArray);
			modArray = makeModArray();
			turn();
			//printSeatingArray();
			// for (int i = 0; i < oldArray.length; i++) {
				// for (int j = 0; j < oldArray[i].length; j++) {
					 // System.out.print(oldArray[i][j]);
				// }
				// System.out.println("NEW LINE");
			// }
			// System.out.println(arraysEqual(oldArray, seatingArray));
		}
		// printSeatingArray();
		// oldArray = cloneArray(seatingArray);
		// modArray = makeModArray();
		// turn();
		// printSeatingArray();

		// System.out.println("NEW LINE");
		// modArray = makeModArray();
		// turn();
		// printSeatingArray();

		// System.out.println("NEW LINE");
		// modArray = makeModArray();
		// turn();
		// printSeatingArray();



		System.out.println(findOccupied());

	}
	
}