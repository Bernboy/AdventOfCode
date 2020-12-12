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


public class Day10 {
	

	public static void part2(int[] list) {
        final long[] sums = new long[list[list.length - 1] + 1];
        sums[0] = 1;
        for (int i = 0; i < list.length; i++) {
            final long x = list[i] >= 3 ? sums[list[i] - 3] : 0;
            final long y = list[i] >= 2 ? sums[list[i] - 2] : 0;
            final long z = list[i] >= 1 ? sums[list[i] - 1] : 0;
            sums[list[i]] = x + y + z;
            for (int j = 0; j < list[i] - 1; j++) {
            	System.out.print(sums[list[j]] + " ");	
            }
            System.out.println();
        }

        System.out.println("total = " + sums[sums.length - 1]);
    }


	public static void main(String args[]) throws FileNotFoundException {
		File inputFile = new File("testinput2.txt");
		Scanner scanner = new Scanner(inputFile);

		ArrayList<Integer> list = new ArrayList<Integer>();

		while (scanner.hasNextInt()) {
			list.add(scanner.nextInt());
		}

		Collections.sort(list);
		



		int[] intList = new int[list.size()];
		for (int i = 0; i < intList.length; i++) {
			intList[i] = list.get(i);
			System.out.print(intList[i] + " ");
		}
		System.out.println();
		part2(intList);
	}








	public static ArrayList<Integer> makeSubList(ArrayList<Integer> list, int index) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (int i = 0; i < index; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}




	public static int count(ArrayList<Integer> list) {
		if (list.size() <= 2) {
			return 1;
		}

		if (list.size() == 3) {
			if (list.get(2) - list.get(0) > 3) {
				return 1;
			} else {
				return 2;
			}
		}

		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 < list.size()) {
				if (list.get(i + 1) - list.get(i) == 1) { // IF FIRST DIFFERENCE IS ONE
					if (i + 2 < list.size()) {
						if (list.get(i + 2) - list.get(i) == 2) { // IF SECOND DIFFERENCE IS STILL ONE
							if (i + 3 < list.size()) {
								if (list.get(i + 3) - list.get(i) <= 3) { // IF THIRD DIFFERENCE IS STILL ONE
									System.out.println("IF THIRD DIFF IS STILL ONE: " + list.get(i) + " " + list.get(i + 1) + " " + list.get(i + 2) + " " + list.get(i + 3));
									total += count(makeSubList(list, i + 1));
									total += count(makeSubList(list, i + 2));
									total += count(makeSubList(list, i + 3));
								}
							}
						} else { // IF SECOND DIFFERENCE IS TWO
							System.out.println("IF SECOND DIFF IS TWO: " + list.get(i) + " " + list.get(i + 1) + " " + list.get(i + 2));
							total += count(makeSubList(list, i + 1));
							total += count(makeSubList(list, i + 2));
						}
					}
				} else { //IF FIRST DIFFERENCE IS TWO
					System.out.println("IF FIRST DIFF IS TWO: " + list.get(i) + " " + list.get(i + 1) + list.get(i + 2));
					if (list.get(i + 2) - list.get(i) == 2) {
						total += count(makeSubList(list, i + 1));
						total += count(makeSubList(list, i + 2));
					}
				}
			}
			System.out.println("interim total = " + total);
		}
		return total;



	}






}