import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExplorationProject {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		Differentiator differentiator = new Differentiator();
		boolean shouldGoAgain = true;
		while (shouldGoAgain) {
			System.out.println("Please enter the equation with the proper format:");
//			System.out.println("Please enter two terms to be simplified");
			differentiator.analyze(getReadLine());
//			differentiator.simplifyTerms(getReadLine(), getReadLine(), 3);
//			differentiator.canAddOrSubtract(getReadLine(), getReadLine());
			differentiator.clearArrays();
			System.out.println("Success!");
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
