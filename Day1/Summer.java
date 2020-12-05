import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;

public class Summer {




	public static void main(String args[]) {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		try {
			File inputFile = new File("Day1Data.txt");
			Scanner scanner = new Scanner(inputFile);
			while (scanner.hasNextInt()) {
				int data = scanner.nextInt();
				numList.add(data);
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found bozo");
		}

		for (int i = 0; i < numList.size(); i++) {
			int outerLoopNum = numList.get(i);
			for (int j = i; j < numList.size(); j++) {
				int innerLoopNum = numList.get(j);
				if (outerLoopNum + innerLoopNum == 2020) {
					System.out.println("Outloop Num = " + outerLoopNum);
					System.out.println("Innerloop Num = " + innerLoopNum);
					System.out.println("Their product is = " + outerLoopNum*innerLoopNum);
				}
				for (int k = j; k < numList.size(); k++) {
					int innerMostNum = numList.get(k);
					if (outerLoopNum + innerLoopNum + innerMostNum == 2020) {
						System.out.println("Outloop Num = " + outerLoopNum);
						System.out.println("Innerloop Num = " + innerLoopNum);
						System.out.println("Innermost Num = " + innerMostNum);
						System.out.println("Their product is = " + outerLoopNum*innerLoopNum*innerMostNum);
					}
				}
			}
		}
	}
}