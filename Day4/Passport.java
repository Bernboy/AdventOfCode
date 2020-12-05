import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


//213
public class Passport{

	static String[] ids = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};


	public static void reset(HashMap<String, Integer> myMap) {
		myMap.put("byr", 0);
		myMap.put("iyr", 0);
		myMap.put("eyr", 0);
		myMap.put("hgt", 0);
		myMap.put("hcl", 0);
		myMap.put("ecl", 0);
		myMap.put("pid", 0);
		myMap.put("cid", 0);
	}

	public static boolean checkPP(HashMap<String, Integer> myMap) {
		for (String id : ids) {
			if (myMap.get(id) == 0 && id != "cid") {
				return false;
			}
		}
		return true;
	}


	public static void main(String args[]) throws FileNotFoundException {
		int total = 0;
		HashMap<String, Integer> myMap = new HashMap<String, Integer>();
		reset(myMap);

		ArrayList<String> passports = new ArrayList<String>();
		File inputFile = new File("Day4Passport.txt");
		Scanner scanner = new Scanner(inputFile);
		
		String[] line = null;
		int counter = 0;
		while (scanner.hasNextLine()) {
			String newLine = scanner.nextLine();
			if (newLine.isEmpty()) {
				if (checkPP(myMap)) {
					reset(myMap);
					total++;
				}
			} else {
				Scanner lineScanner = new Scanner(newLine);
				while (lineScanner.hasNext()) {
					line = lineScanner.next().split(":");
					if (myMap.get(line[0]) == 0) {
						myMap.put(line[0], 1);
					} else {
						if (checkPP(myMap)) {
							total++;
						}
						reset(myMap);
						myMap.put(line[0], 1);
					}
				}
			}
			

			// line = scanner.next().split(":");
			
			// if (myMap.get(line[0]) == 0) {
			// 	myMap.put(line[0], 1);
			// } else {
			// 	if (checkPP(myMap)) {
			// 		total++;
			// 	}
			// 	reset(myMap);
			// 	myMap.put(line[0], 1);
			// }
				
		}
		System.out.println(total);
	}		
}