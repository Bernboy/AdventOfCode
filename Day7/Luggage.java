import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Luggage {

	public static class Bag {
		public int num;
		public String name;

		public Bag(int num, String name) {
			this.num = num;
			this.name = name;
		}
	}
	public static File inputFile = new File("Day7Luggage.txt");
	
	public static Map<String, ArrayList<Bag>> map = new HashMap<String, ArrayList<Bag>>();

	public static void main(String args[]) throws FileNotFoundException{
		Scanner scanner = new Scanner(inputFile);
		String line = "";
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			Scanner lineScanner = new Scanner(line);

			//GET FIRST NAME IN LINE
			String nameOfBag; 
			String adj = "";
			String noun = "";
			if (lineScanner.hasNext()) {
				adj = lineScanner.next();
			}
			if (lineScanner.hasNext()) {
				noun = lineScanner.next();
			}
			nameOfBag = adj + noun;

			//clear next two trash words
			if (lineScanner.hasNext()) {
				String temp = lineScanner.next();
			}
			if (lineScanner.hasNext()) {
				String temp = lineScanner.next();
			}


			//GET REST OF LINE TO PUT IN MAP
			ArrayList<Bag> bagList = new ArrayList<Bag>();
			while (lineScanner.hasNext()) {
				String number = lineScanner.next();
				if (!Character.isDigit(number.charAt(0))) { // Check to see if this is a line we gotta toss
					break;
				}
				String name = "";
				if (lineScanner.hasNext()) {
					adj = lineScanner.next();
				}
				if (lineScanner.hasNext()) {
					noun = lineScanner.next();
				}
				name = adj + noun;

				if (lineScanner.hasNext()) {
					String temp = lineScanner.next();
				}

				Bag bagToAdd = new Bag(Integer.parseInt(number), name);
				bagList.add(bagToAdd);
			}
			map.put(nameOfBag, bagList);
		}



		//adding to queue and doing the work
		PriorityQueue<String> myQueue = new PriorityQueue<String>();
		HashSet<String> visited = new HashSet<String>();
		int total = 0;

		myQueue.add("shinygold");
		while (!myQueue.isEmpty()) {
			String idToMatch = myQueue.poll();
			for (String id : map.keySet()) {
				for (Bag currBag : map.get(id)) {
					if (currBag.name.equals(idToMatch)) {
						myQueue.add(id);
						visited.add(id);
					}
				}
			}
		}
		System.out.println("total unique that contain gold = " + visited.size());
		System.out.println("sum inside shiny bag = " + sum(map.get("shinygold")));
	}


	//returns total number of bags accounted for by list.
	public static int sum(ArrayList<Bag> bagList) {
		if (bagList.size() == 0) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < bagList.size(); i++){
			int interim = bagList.get(i).num + bagList.get(i).num*sum(map.get(bagList.get(i).name));
			sum += interim;
		}
		for (int i = 0; i < bagList.size(); i++) {
		}
		return sum;
	}
}