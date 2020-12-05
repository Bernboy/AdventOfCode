import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class PP {

	static String[] ids = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

	public static HashMap<String, Integer> myMap = new HashMap<String, Integer>();
	
	


	public static void main(String args[]) throws FileNotFoundException{
		int total = 0;
		myMap.put("byr", 0);
		myMap.put("iyr", 0);
		myMap.put("eyr", 0);
		myMap.put("hgt", 0);
		myMap.put("hcl", 0);
		myMap.put("ecl", 0);
		myMap.put("pid", 0);
		myMap.put("cid", 0);
		ArrayList<ArrayList<String[]>> data = new ArrayList<ArrayList<String[]>>();
		
		File inputFile = new File("Day4Passport.txt");
		Scanner scanner = new Scanner(inputFile);

		String line = "";
		while(scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			//System.out.println(nextLine);
			if (!nextLine.isEmpty()) {
				line += nextLine;
				line += " ";
				//System.out.println(line);
			} else {
				Scanner lineScanner = new Scanner(line);
				ArrayList<String[]> stringList = new ArrayList<String[]>();
				while (lineScanner.hasNext()) {
					stringList.add(lineScanner.next().split(":"));
					//System.out.println(stringList.get(stringList.size()-1)[1]);
				}
				//System.out.println(line);
				data.add(stringList);
				//System.out.println(data);
				line = "";
			}
		}
		//System.out.println(line);
		Scanner lineScanner = new Scanner(line);
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		while (lineScanner.hasNext()) {
			stringList.add(lineScanner.next().split(":"));
					//System.out.println(stringList.get(stringList.size()-1)[1]);
		}
				//System.out.println(line);
		data.add(stringList);

		//DATA now filled out

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				myMap.put(data.get(i).get(j)[0], 1);
			}
			if (checkPP()) {
				total++;
			}
			//resetMap();
		}
		System.out.println(total);
	}


	public static void resetMap() {
		myMap.put("byr", 0);
		myMap.put("iyr", 0);
		myMap.put("eyr", 0);
		myMap.put("hgt", 0);
		myMap.put("hcl", 0);
		myMap.put("ecl", 0);
		myMap.put("pid", 0);
		myMap.put("cid", 0);
	}


	public static void printMap() {
		//System.out.println("NEW!!!");
		for (String id : myMap.keySet()) {
			//System.out.println("ID = " + id);
			//System.out.println("Result = " + myMap.get(id));
		}
	}

	public static boolean checkPP() {
		for (String id : ids) {
			if (myMap.get(id) == 0) {
				printMap();
				resetMap();
				return false;
			}
		}
		resetMap();
		return true;
	}

	
}