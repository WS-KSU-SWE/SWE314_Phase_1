package swe314_project_phase1;

public class CryptanalysisDecryptor {

	private char[] charaFrequency = {'E', 'T', 'A', 'O', 'I', 'N', 'S', 'H', 'R', 'C', 'U',
									 'M', 'W', 'F', 'G', 'Y', 'P', 'B', 'V', 'K', 'J', 'Q', 'X', 'Z'};
	private LetterFrequencyPair[] cypherCharaFrequency;
	
	
	public CryptanalysisDecryptor() {
		
		cypherCharaFrequency = new LetterFrequencyPair[26];
		
		for (int i = 0; i < 26; ++i) {
			cypherCharaFrequency[i] = new LetterFrequencyPair((char)('A' + i));
		}
		
	}
	
	// simple bubble sort implementation (descending order)
	public void sortCypherCharaFrequency() {
		
		for (int i = 0; i < cypherCharaFrequency.length; ++i) {
			
			for (int j = 0; j < cypherCharaFrequency.length - 1 - i; ++j) {
				
				if (cypherCharaFrequency[j].frequency < cypherCharaFrequency[j + 1].frequency) {
					
					LetterFrequencyPair temp = cypherCharaFrequency[j];
					
					cypherCharaFrequency[j] = cypherCharaFrequency[j + 1];
					
					cypherCharaFrequency[j + 1] = temp;
					
				}
				
			}
			
		}
		
	}
	
	public void decrypt(String cyphertext) {
		
		cyphertext = cyphertext.toUpperCase();
		Stack<String> stack = new LinkedStack<String>();
		
		for (int i = 0; i < cyphertext.length(); ++i) {
			
			if ((cyphertext.charAt(i) >= 'A' && cyphertext.charAt(i) <= 'Z')) {
				cypherCharaFrequency[cyphertext.charAt(i) - 'A'].frequency++;
			}
			
		}
		
		sortCypherCharaFrequency();
		
		
		
		
	}
	
	
	
	public static void printArrayLinearly(Object [] array) {
		
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	
	
}
