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

public class Day12 {



	public static int part1() throws FileNotFoundException{
		int east = 0;
		int north = 0;
		int direction = 90;
		File inputFile = new File("testinput.txt");
		Scanner scanner = new Scanner(inputFile);

		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			char directionChar = nextLine.charAt(0);
			int move = Integer.parseInt(nextLine.substring(1));
			//System.out.println(directionChar);
			//System.out.println(move);

			if (directionChar == 'N') {
				north += move;
			} else if (directionChar == 'S') {
				north -= move;
			} else if (directionChar == 'W') {
				east -= move;
			} else if (directionChar == 'E') {
				east += move;
			} else if (directionChar == 'L') {
				direction -= move;
			} else if (directionChar == 'R') {
				direction += move;
			} else if (directionChar == 'F') {
				System.out.println("direction%360 = " + direction%360);
				if (direction%360 == 0) {
					north += move;
				} else if (direction%360 == 90) {
					east += move;
				} else if (direction%360 == 180 || direction%360 == -180) {
					north -= move;
				} else if (direction%360 == 270 || direction%360 == -90) {
					east -= move;
				}
			} else {
				System.out.println("PROBLEM");
			}
			System.out.println(direction + " " + east + " " + north);
		}

		if (north < 0) {
			north *= -1;
		}
		if (east < 0) {
			east *= -1;
		}
		System.out.println(north + east);
		return north+east;
	}

	public static int part2() throws FileNotFoundException {
		int eastWP = 10;
		int northWP = 1;
		int eastShip = 0;
		int northShip = 0;
		//int direction = 90;
		File inputFile = new File("testinput.txt");
		Scanner scanner = new Scanner(inputFile);

		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			char directionChar = nextLine.charAt(0);
			int move = Integer.parseInt(nextLine.substring(1));
			//System.out.println(directionChar);
			//System.out.println(move);

			if (directionChar == 'N') {
				northWP += move;
			} else if (directionChar == 'S') {
				northWP -= move;
			} else if (directionChar == 'W') {
				eastWP -= move;
			} else if (directionChar == 'E') {
				eastWP += move;
			} else if (directionChar == 'L') {
				if (move == 90) {
					int tempNorth = eastWP;
					eastWP = -1*northWP;
					northWP = tempNorth;
				} else if (move == 180) {
					northWP *= -1;
					eastWP *= -1;
				} else if (move == 270) {
					int tempNorth = eastWP*-1;
					eastWP = northWP;
					northWP = tempNorth;
				}
			} else if (directionChar == 'R') {
				if (move == 90) {
					int tempNorth = -1*eastWP;
					eastWP = northWP;
					northWP = tempNorth;
				} else if (move == 180) {
					northWP *= -1;
					eastWP *= -1;
				} else if (move == 270) {
					int tempNorth = eastWP;
					eastWP = -1*northWP;
					northWP = tempNorth;
				}
			} else if (directionChar == 'F') {
				// System.out.println("direction%360 = " + direction%360);
				eastShip += move * eastWP;
				northShip += move * northWP;
			} else {
				System.out.println("PROBLEM");
			}
			System.out.println(eastShip + " " + northShip);
		}

		if (northShip < 0) {
			northShip *= -1;
		}
		if (eastShip < 0) {
			eastShip *= -1;
		}
		System.out.println(northShip + eastShip);
		return northShip + eastShip;	
	}


	public static void main(String args[]) throws FileNotFoundException {
		//part1();
		part2();
		

	}






}