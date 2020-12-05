import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class PP2 {


	static ArrayList<String> ids = new ArrayList<String>();


	//{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

	public static HashMap<String, String> myMap = new HashMap<String, String>();
	
	


	public static void main(String args[]) throws FileNotFoundException{
		ids.add("byr");
		ids.add("iyr"); 
		ids.add("eyr"); 
		ids.add("hgt"); 
		ids.add("hcl"); 
		ids.add("ecl"); 
		ids.add("pid");   

		int total = 0;
		// myMap.put("byr", "temp");
		// myMap.put("iyr", "temp");
		// myMap.put("eyr", "temp");
		// myMap.put("hgt", "temp");
		// myMap.put("hcl", "temp");
		// myMap.put("ecl", "temp");
		// myMap.put("pid", "temp");
		// myMap.put("cid", "temp");

		ArrayList<ArrayList<String[]>> data = new ArrayList<ArrayList<String[]>>();
		
		File inputFile = new File("Day4Passport.txt");
		Scanner scanner = new Scanner(inputFile);

		String line = "";
		while(scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if (!nextLine.isEmpty()) {
				line += nextLine;
				line += " ";
			} else {
				Scanner lineScanner = new Scanner(line);
				ArrayList<String[]> stringList = new ArrayList<String[]>();
				while (lineScanner.hasNext()) {
					stringList.add(lineScanner.next().split(":"));
				}
				data.add(stringList);
				line = "";
			}
		}
		//DO LAST LINE, FIXING OFF BY ONE ERROR
		Scanner lineScanner = new Scanner(line);
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		while (lineScanner.hasNext()) {
			stringList.add(lineScanner.next().split(":"));
		}
		data.add(stringList);


		//DATA now filled out
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				//System.out.println(data.get(i).get(j)[0] + " " + data.get(i).get(j)[1]);
				myMap.put(data.get(i).get(j)[0], data.get(i).get(j)[1]);
			}
			if (checkPP()) {
				total++;
			}
		}
		System.out.println(total);
	}


	public static void resetMap() {
		myMap.put("byr", "temp");
		myMap.put("iyr", "temp");
		myMap.put("eyr", "temp");
		myMap.put("hgt", "temp");
		myMap.put("hcl", "temp");
		myMap.put("ecl", "temp");
		myMap.put("pid", "temp");
		myMap.put("cid", "temp");
		//System.out.println(myMap.size());
	}


	public static void printMap() {
		//System.out.println("NEW!!!");
		for (String id : myMap.keySet()) {
			System.out.println("ID = " + id);
			System.out.println("Result = " + myMap.get(id));
		}
	}

	public static boolean checkPP() {
		//System.out.println(ids);
		//System.out.println("SIZE: " + ids.size());
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			//System.out.println(i);
			//System.out.println(id);
			if (myMap.get(id) == "temp") {
				//printMap();
				resetMap();
				return false;
			} else if (!isValidID(id, myMap.get(id))) {
				//printMap();
				resetMap();
				return false;
			}
		}
		resetMap();
		return true;
	}

	public static boolean isValidID(String id, String value) {
		//System.out.println(id + " " + value);
		if (id == "byr") {
			return checkBYR(value);
		} else if (id == "iyr") {
			return checkIYR(value);
		} else if (id == "eyr") {
			return checkEYR(value);
		} else if (id == "hgt") {
			return checkHGT(value);
		} else if (id == "hcl") {
			return checkHCL(value);
		} else if (id == "ecl") {
			return checkECL(value);
		} else if (id == "pid") {
			return checkPID(value);
		} else if (id == "cid") {
			return checkCID(value);
		} else {
			return false;
		}
	}	

	public static boolean checkBYR(String value) {
		int idNum = Integer.parseInt(value);
		//System.out.println("Value is: " + value + " and this value is: " + (idNum >= 1920 && idNum <= 2002));
		return idNum >= 1920 && idNum <= 2002;
	}

	public static boolean checkIYR(String value) {
		int idNum = Integer.parseInt(value);
		//System.out.println("Value is: " + value + " and this value is: " + (idNum >= 2010 && idNum <= 2020));
		return idNum >= 2010 && idNum <= 2020;
	}

	public static boolean checkEYR(String value) {
		int idNum = Integer.parseInt(value);
		//System.out.println("Value is: " + value + " and this value is: " + (idNum >= 2020 && idNum <= 2030));
		return idNum >= 2020 && idNum <= 2030;
	}

	public static boolean checkHGT(String value) {

		String[] part = value.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		//System.out.println("Value is: " + value + " with length = " + part.length);
		//System.out.println("Part[0] = " + part[0]); //" and part[1] = " + part[1]);
		//System.out.println(part.length);
		if (part.length != 2) {
			return false;
		}
		System.out.println("Part[0] = " + part[0] + " and part[1] = " + part[1]);
		if (part[1].equals("cm")) {
			System.out.println("Part[0] = " + part[0] + " and part[1] = " + part[1]);
			return Integer.parseInt(part[0]) >= 150 && Integer.parseInt(part[0]) <= 193;
		} else if (part[1].equals("in")) {
			System.out.println("Part[0] = " + part[0] + " and part[1] = " + part[1]);
			return Integer.parseInt(part[0]) >= 59 && Integer.parseInt(part[0]) <= 76;
		} else {
			return false;
		}
	}

	public static boolean checkHCL(String value) {
		//System.out.println(value.length());
		if (value.length() != 7) {
			return false;
		}
		if (value.charAt(0) != '#') {
			return false;
		}
		for (int i = 1; i < 7; i++) {
			//System.out.println(value.charAt(i) + " " + Character.isDigit(value.charAt(i)) +  " " + Character.isLowerCase(value.charAt(i)));
			if (!Character.isDigit(value.charAt(i)) && !Character.isLowerCase(value.charAt(i))) {
				
				return false;
			}
		}
		return true;
	}

	public static boolean checkECL(String value) {
		//System.out.println("HERE");
		ArrayList<String> set = new ArrayList<String>();
		set.add("amb");
		set.add("blu");
		set.add("brn");
		set.add("gry");
		set.add("grn");
		set.add("hzl");
		set.add("oth");
		return set.contains(value);
	}

	public static boolean checkPID(String value) {
		if (value.length() != 9) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			if (!Character.isDigit(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkCID(String value) {
		return true;
	}





}