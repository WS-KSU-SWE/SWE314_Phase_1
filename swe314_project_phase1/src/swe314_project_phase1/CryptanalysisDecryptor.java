package swe314_project_phase1;

import java.util.Scanner;

public class CryptanalysisDecryptor {

	private char[] charaFrequency = {'E', 'T', 'A', 'O', 'I', 'N', 'S', 'H', 'R', 'D', 'L', 'C', 'U',
									 'M', 'W', 'F', 'G', 'Y', 'P', 'B', 'V', 'K', 'J', 'Q', 'X', 'Z'};
	private LetterFrequencyPair[] cyphertextLetterFrequency;
	
	
	public CryptanalysisDecryptor() {
		
		cyphertextLetterFrequency = new LetterFrequencyPair[26];
		
		for (int i = 0; i < 26; ++i) {
			cyphertextLetterFrequency[i] = new LetterFrequencyPair((char)('A' + i));
		}
		
	}
	
	// simple bubble sort implementation (descending order)
	public void sortCyphertextLetterFrequency() {
		
		for (int i = 0; i < cyphertextLetterFrequency.length; ++i) {
			
			for (int j = 0; j < cyphertextLetterFrequency.length - 1 - i; ++j) {
				
				if (cyphertextLetterFrequency[j].frequency < cyphertextLetterFrequency[j + 1].frequency) {
					
					LetterFrequencyPair temp = cyphertextLetterFrequency[j];
					
					cyphertextLetterFrequency[j] = cyphertextLetterFrequency[j + 1];
					
					cyphertextLetterFrequency[j + 1] = temp;
					
				}
				
			}
			
		}
		
	}
	
	
	public int getChoice(int min, int max) {
		
		Scanner input = new Scanner(System.in);
		boolean isValid = false;
		int choice = 0;
		
		
		while (!isValid) {
			
			try {
			
				choice = input.nextInt();
				
				if (choice < min || choice > max) {
					System.out.print("Please enter an integer between " + min  + " and " + max + ": ");
				}
				
				else {
					isValid = true;
				}
				
			}
			catch (Exception e) {
				System.out.print("Please enter an integer: ");
			}
			
		}
		
		input.close();
		
		return choice;
	}
	
	
	public void decrypt(String cyphertext) {
		
		Stack<String> stack = new LinkedStack<String>();
		int frequencyLetterIndex = 0;
		int currentLetter = 0;
		String middleText;
		
		cyphertext = cyphertext.toUpperCase();
		
		middleText = cyphertext;
		
		int userChoice = 0;
		
		for (int i = 0; i < cyphertext.length(); ++i) {
			
			if ((cyphertext.charAt(i) >= 'A' && cyphertext.charAt(i) <= 'Z')) {
				cyphertextLetterFrequency[cyphertext.charAt(i) - 'A'].frequency++;
			}
			
		}
		
		sortCyphertextLetterFrequency();
		
		
		while(userChoice != 4 || currentLetter == 26) {
			
			String newMiddleText = "";
			
			for (int i = 0; i < middleText.length(); ++i) {
				
				char chara = cyphertext.charAt(i);
				
				if (chara == cyphertextLetterFrequency[currentLetter].letter) {
					
					newMiddleText += charaFrequency[frequencyLetterIndex];
					
				}
				else {
					newMiddleText += middleText.charAt(i);
				}
				
			}
			
			
			System.out.println("Current guess: " + newMiddleText);
			
			System.out.println("1) Continue");
			System.out.println("2) Choose another letter");
			System.out.println("3) Step back");
			System.out.println("4) Exit");
			System.out.print("Please choose an option: ");
			
			userChoice = getChoice(1, 4);
			
			switch (userChoice) {
			
			case 1:
				middleText = newMiddleText;
				stack.push(newMiddleText);
				currentLetter++;
				
			case 2:	
				frequencyLetterIndex = (frequencyLetterIndex + 1) % 26;
				break;
				
			case 3:
				
				if (stack.empty()) {
					System.out.println("You are an the first step");
				}
				else {
					middleText = stack.pop();
					currentLetter--;
				}
				break;	
				
				
			default:
				userChoice = 4;
				
			}
			
		}
		
	}
	
}
