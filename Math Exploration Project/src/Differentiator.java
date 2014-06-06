import java.util.ArrayList;


public class Differentiator {
	
	public ArrayList operators, openingParentheses, closingParentheses, exponents, multiplication, division, addition, subtraction, terms;
	
	public Differentiator() {
		operators = new ArrayList(1);
		openingParentheses = new ArrayList(1);
		closingParentheses = new ArrayList(1);
		exponents = new ArrayList(1);
		multiplication = new ArrayList(1);
		division = new ArrayList(1);
		addition = new ArrayList(1);
		subtraction = new ArrayList(1);
		terms = new ArrayList(1);
	}

	public void analyze(String input) {
		input = input.replaceAll(" ", "");//get rid of all the spaces
		doAnalyzingLoop(input);
		determineSeperateTerms(input);
	}
	
	/**
	 * 
	 * @param terms an arrayList of the terms seperated from each other and the operators, each having its own index
	 * @return does all simplification that it can of the terms in the array
	 */
	public ArrayList simplifyTerms(ArrayList terms) {
		ArrayList newTerms = terms;
		
		return newTerms;
	}
	
	/**
	 * Loops through the input and adds the characters and stuff to their respective arrays
	 * @param input the input...duh
	 */
	private void doAnalyzingLoop(String input) {
		int i = 0;//the current iteration
		
		while (i < input.length() - 1) {
			char currentLetter = input.charAt(i);
			switch (currentLetter) {
				case '(':
					openingParentheses.add(i);
//					operators.add(i);
					break;
				case ')':
					closingParentheses.add(i);
//					operators.add(i);
					break;
				case '^':
					exponents.add(i);
//					operators.add(i);
					break;
				case '*':
					multiplication.add(i);
					operators.add(i);
					break;
				case '/':
					division.add(i);
					operators.add(i);
					break;
				case '+':
					addition.add(i);
					operators.add(i);
					break;
				case '-':
					subtraction.add(i);
					operators.add(i);
					break;
			}
			i++;
		}
	}
	
	/**
	 * Adds the individual terms and the operators to the terms array
	 * @param input
	 */
	private void determineSeperateTerms(String input) {
		try {
			terms.add(input.substring(0, (int)operators.get(0)));
			System.out.println(input.substring(0, (int)operators.get(0)));//the term between the beginning and the first operator
			for (int k = 0; k < operators.size() - 1; k++) {
//				System.out.println("numParenthLevelsIn: " + (int)determineNumParenthLevelsIn((int)operators.get(k + 1)) + ", and k:" + (k + 1));
				int currIndex = (int)operators.get(k) + 2;
				int numParenthLevelsInside = determineNumParenthLevelsIn((int)operators.get(k) + 2);
				if (numParenthLevelsInside > 0) {
					if (determineNumParenthLevelsIn((int)operators.get(k)) == 0) {
						terms.add(input.charAt((int)operators.get(k)) + "");
						terms.add("");
						System.out.println(input.charAt((int)operators.get(k)));
					}else{
						terms.set(terms.size() - 1, (String)terms.get(terms.size() - 1) + input.charAt((int)operators.get(k)));
						System.out.print(input.charAt((int)operators.get(k)));
					}
					if (determineNumParenthLevelsIn((int)operators.get(k + 1)) == 0) {
						System.out.print(input.substring((int)operators.get(k) + 1, (int)operators.get(k + 1)));
						terms.set(terms.size() - 1, (String)terms.get(terms.size() - 1) + input.substring((int)operators.get(k) + 1, (int)operators.get(k + 1)));
						System.out.print("\n");
					}else{
						terms.set(terms.size() - 1, (String)terms.get(terms.size() - 1) + input.substring((int)operators.get(k) + 1, (int)operators.get(k + 1)));
						System.out.print(input.substring((int)operators.get(k) + 1, (int)operators.get(k + 1)));
					}
				}else{
					terms.add(input.charAt((int)operators.get(k)) + "");
					System.out.println(input.charAt((int)operators.get(k)));
					terms.add(input.substring((int)operators.get(k) + 1, (int)operators.get(k + 1)));
					System.out.println(input.substring((int)operators.get(k) + 1, (int)operators.get(k + 1)));//the middle terms
				}
			}
			terms.add(input.charAt((int)operators.get(operators.size() - 1)) + "");
			System.out.println(input.charAt((int)operators.get(operators.size() - 1)));
			terms.add(input.substring((int)operators.get(operators.size() - 1) + 1));
			System.out.println(input.substring((int)operators.get(operators.size() - 1) + 1));//the term between the last operator and the end
		}catch (Exception e) {
			System.out.println("Ya, sorry, I done goofed");
		}
		System.out.print("");
	}
	
	/**
	 * This just clears out all of the arrays that are used in analyze()
	 */
	public void clearArrays() {
		operators.clear();
		openingParentheses.clear();
		closingParentheses.clear();
		exponents.clear();
		multiplication.clear();
		division.clear();
		addition.clear();
		subtraction.clear();
		terms.clear();
	}
	
	/**
	 * I feel like this can be more efficient slightly by incrementing through the arrayList indexes,
	 * but I don't feel like thinking that much right now. For the moment, this should work.
	 * @param index of the letter that you want to know how many levels in
	 * @return how many parentheses levels in the letter at the index specified is in
	 */
	public int determineNumParenthLevelsIn(int index) {
		int i = (int)(openingParentheses.get(0));
		int numParenthLevelsIn = 0;
		while (i < index) {
			if (openingParentheses.contains((i))) {
				numParenthLevelsIn++;
			}
			if (closingParentheses.contains(i)) {
				numParenthLevelsIn--;
			}
			i++;
		}
		return numParenthLevelsIn;
	}
	
	/**
	 * This is assuming that the terms are in the form of kx^n, with k being some double
	 * There must be both a k and an n, even if they are just 1.0D
	 * If type is 2 or 3, it is also assuming that canAddOrSubtractTerms has already been called and returned true
	 * @param term1 the first term
	 * @param term2 the second term
	 * @param type 0 means term1*term2, 1 mean term1/term2, 2 means term1 + term2, 3 means term1 - term2
	 * @return a simplified output of the two terms
	 */
	public String simplifyTerms(String term1, String term2, int type) {
		double answerCoefficient = determineCoefficient(term1, term2, type);
		double answerExponent = determineExponent(term1, term2, type);
		
//		System.out.println("" + answerCoefficient + "x^" + answerExponent);
		return "" + answerCoefficient + "x^" + answerExponent;
	}
	
	/**
	 * Really this just finds out if they have the same exponent, thats it
	 * @return true if exponents are the same, false if they're not
	 */
	public boolean canAddOrSubtract(String term1, String term2) {
		int term1CaratIndex = term1.indexOf('^');
		int term2CaratIndex = term2.indexOf('^');
		if (isDouble(term1.substring(term1CaratIndex + 1)) && isDouble(term2.substring(term2CaratIndex + 1))) {
			if (Double.parseDouble(term1.substring(term1CaratIndex + 1)) == Double.parseDouble(term2.substring(term2CaratIndex + 1))) {
				System.out.println(true);
				return true;
			}
		}
		System.out.println(false);
		return false;
	}
	
	/**
	 * This also assumes the form of kx^n
	 * It will not do anything with the variable being something besides ^ to show exponentiation
	 * @param type 0 mean add exponents, type 1 means subtract exponents, type 2 or 3 means keep term1's exponent, assuming term1 and term1 and term2's
	 * exponents are the same
	 * @return The added exponents of the terms
	 */
	public double determineExponent(String term1, String term2, int type) {
		int term1CaratIndex = term1.indexOf('^');		
		double term1Exponent = 0D;
		if (term1CaratIndex != -1 && isDouble(term1.substring(term1CaratIndex + 1))) {
			term1Exponent = Double.parseDouble(term1.substring(term1CaratIndex + 1));
		}
		
		if (type == 2 || type == 3) {
			return term1Exponent;
		}
		
		int term2CaratIndex = term1.indexOf('^');
		double term2Exponent = 0D;
		if (term2CaratIndex != -1 && isDouble(term2.substring(term2CaratIndex + 1))) {
			term2Exponent = Double.parseDouble(term2.substring(term2CaratIndex + 1));
		}
		
		double answerExponent = 1;
		if (type == 0) {
			answerExponent = term1Exponent + term2Exponent;
		}else if (type == 1) {
			answerExponent = term1Exponent - term2Exponent;
		}
		return answerExponent;
	}
	
	/**
	 * This also assumes the form of kx^n
	 * It will not do anything with the variable being something besides x (atm)
	 * @param type 0 means term1 coef * term2 coef, 1 means term1 / term2, 2 term1 + term2, 3 term1 - term2
	 * @return The multiplied coefficients of the two terms
	 */
	public double determineCoefficient(String term1, String term2, int type) {
		String term1CoefficientString = term1.substring(0, term1.indexOf("x"));//anything between the beginning and x
		double term1Coefficient = 0D;
		if (isDouble(term1CoefficientString)) {//if it's ok to parse it to a double
			term1Coefficient = Double.parseDouble(term1CoefficientString);
		}

		String term2CoefficientString = term2.substring(0, term2.indexOf("x"));
		double term2Coefficient = 0D;
		if (isDouble(term2CoefficientString)) {
			term2Coefficient = Double.parseDouble(term2CoefficientString);
		}
		
		double answerCoefficient = 1;
		if (type == 0) {
			answerCoefficient = term1Coefficient * term2Coefficient;
		}else if (type == 1) {
			answerCoefficient = term1Coefficient / term2Coefficient;
		}else if (type == 2) {
			answerCoefficient = term1Coefficient + term2Coefficient;
		}else if (type == 3) {
			answerCoefficient = term1Coefficient - term2Coefficient;
		}
		return answerCoefficient;
	}

	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}