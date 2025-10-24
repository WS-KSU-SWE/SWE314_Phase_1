package swe314_project_phase1;
import java.util.Scanner;


public class KeyedTranspositionEncryptDecrypt extends EncrypterDecrypter {

	private int[] key;
	private int[] orderedKey; // in acceding order
	
	
	public KeyedTranspositionEncryptDecrypt(int blockSize) {
		
		if (blockSize < 1) {
			throw new RuntimeException("Block size cannot be less than 1");
		}
		
		key = new int[blockSize];
		orderedKey = new int[blockSize];
		
		for (int i = 0; i < blockSize; ++i) {
			orderedKey[i] = i + 1;
		}
		
	}
	
	
	public void promptKey() {
		
		Scanner input = new Scanner(System.in);
		boolean isValid = false;
		
		
		int keyPart;
		
		for (int i = 0; i < key.length; ++i) {
			
			System.out.print("Enter key part " + (i+1) +": ");
				
				while (!isValid) {
					
					try {
						
						keyPart = input.nextInt();
						
						if (isInKey(keyPart)) {
							System.out.print("Please enter an integer that is new: ");
						}
						else if (keyPart < 1 || keyPart > key.length) {
							System.out.print("Please enter an integer that is between 1 and " + key.length + ": ");
						}
						else {
							
							isValid = true;
							key[i] = keyPart;
							
						}
						
					}
					catch (Exception e) {
						System.out.print("Please enter an integer: ");
					}
					
				}
			
				isValid = false;
			
		}
		
		
	}
	
	
	public boolean isInKey(int val) {
		
		for (int i = 0; i < key.length; ++i) {
			
			if (key[i] == val) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public void encryptWithSteps() {
		
		String middleText = "";
		String cypherText = "";
		boolean appendedZ = false;
		int currentBlock = 0;
		
		
		
		for (int i = 0; i < plaintext.length(); ++i) {
					// check if the character is a letter
			if ((plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z') || (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z')) {
				middleText += plaintext.charAt(i);
			}
			
		}
		
		middleText = middleText.toLowerCase();
		
		System.out.println("We first prepare the plaintext by removing all spaces and non letters:");
		System.out.println(middleText);
		
		while (middleText.length() % key.length != 0) {
			appendedZ = true;
			middleText += 'z';
		}
		
		if (appendedZ) {
			System.out.println("We append z to the end of the plaintext until we can divide the text into blocks each containing " + key.length + " letters");
			System.out.println(middleText);
		}
		
		
		
		System.out.println();
		
		printArrayLinearly(key);
		
		printArrayLinearly(orderedKey);
		
		System.out.println("Using the key we then place all elements in a block in the plaintext in the top indecies to their new positions in the bottom indecies:");
		
		for (int i = 0; i < middleText.length(); ++i) {
			
			if (cypherText.length() % key.length == 0 && i != 0) {
				System.out.println(cypherText);
				currentBlock += key.length;
				
			}
			
			int mixedIndex = key[i % key.length] - 1 + currentBlock;
			
			char chara = middleText.charAt(mixedIndex);
			
			cypherText += chara;
			
		}
		
		
		System.out.println("The end result is: " + cypherText.toUpperCase());
		
	}
	
	
	public void decryptWithSteps() {
		
		String middleText = "";
		String plainText = "";
		boolean appendedZ = false;
		int currentBlock = 0;
		
		
		
		for (int i = 0; i < cyphertext.length(); ++i) {
					// check if the character is a letter
			if ((cyphertext.charAt(i) >= 'a' && cyphertext.charAt(i) <= 'z') || (cyphertext.charAt(i) >= 'A' && cyphertext.charAt(i) <= 'Z')) {
				middleText += cyphertext.charAt(i);
			}
			
		}
		
		middleText = middleText.toUpperCase();
		
		System.out.println("We first prepare the cyphertext by removing all spaces and non letters:");
		System.out.println(middleText);
		
		while (middleText.length() % key.length != 0) {
			appendedZ = true;
			middleText += 'Z';
		}
		
		if (appendedZ) {
			System.out.println("We append z to the end of the cyphertext until we can divide the text into blocks each containing " + key.length + " letters");
			System.out.println(middleText);
		}
		
		
		
		System.out.println();
		
		printArrayLinearly(orderedKey);
		
		printArrayLinearly(key);
		
		System.out.println("Using the key we then place all elements in a block in the cyphertext in the bottom indecies to their new positions in the top indecies:");
		
		for (int i = 0; i < middleText.length(); ++i) {
			
			if (plainText.length() % key.length == 0 && i != 0) {
				System.out.println(plainText);
				currentBlock += key.length;
				
			}
			
			int mixedIndex = key[i % key.length] - 1 + currentBlock;
			
			char chara = middleText.charAt(mixedIndex);
			
			plainText += chara;
			
		}
		
		
		System.out.println("The end result is: " + plainText.toLowerCase());
		
	}
	
	
	public static void printArrayLinearly(int[] array) {
		
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	
}
