import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExplorationProject {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		Differentiator differentiator = new Differentiator();
		boolean shouldGoAgain = true;
		while (shouldGoAgain) {
			System.out.println("What would you like to do?");
			System.out.println("\'d\' for differentiation, \'m\' for multiplication"
								+ "\'div\' for division, \'add\' for division, \'sub\' for subtraction");
			switch(getReadLine()) {
				case "d":
					System.out.println("Please enter the equation with the proper format:");
					differentiator.analyze(getReadLine());
					break;
				case "m":
					System.out.println("Please enter two terms to be multiplied");
					System.out.println(differentiator.simplifyTerms(getReadLine(), getReadLine(), 0));
					break;
				case "div":
					System.out.println("Please enter two terms to be divided");
					System.out.println(differentiator.simplifyTerms(getReadLine(), getReadLine(), 1));
					break;
				case "add":
					System.out.println("Please enter two terms to be added");
					System.out.println(differentiator.simplifyTerms(getReadLine(), getReadLine(), 2));
					break;
				case "sub":
					System.out.println("Please enter two terms to be subtracted");
					System.out.println(differentiator.simplifyTerms(getReadLine(), getReadLine(), 3));
					break;
			}
//			differentiator.canAddOrSubtract(getReadLine(), getReadLine());
			differentiator.clearArrays();
			System.out.println("Again?");
			String response = getReadLine();
			if (!response.equals("yes")) {
				shouldGoAgain = false;
			}
		}
	}
	
	public static String getReadLine() {
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			System.out.println("There was an IOException");
			e.printStackTrace();
		}
		return input;
	}
}
