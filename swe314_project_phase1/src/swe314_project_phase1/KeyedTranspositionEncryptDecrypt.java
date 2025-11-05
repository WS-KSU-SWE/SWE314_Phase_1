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
		
		int blockSize = 1;
		int keyPart;
		
		System.out.print("Enter the block size: ");
		
		while (!isValid) {
			
			try {
				
				blockSize = input.nextInt();
				
				if (blockSize <= 0) {
					System.out.print("Please enter a positive integer: ");
				}
				else {
					isValid = true;
				}
				
				
			}
			catch (Exception e) {
				input.nextLine();
				System.out.print("Please enter an integer: ");
			}
		
		}
			
		isValid = false;
		
		key = new int[blockSize];
		orderedKey = new int[blockSize];
		
		for (int i = 0; i < blockSize; ++i) {
			orderedKey[i] = i + 1;
		}
		
		
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
						input.nextLine();
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
		
		boolean appendedZ = false;
		int currentBlock = 0;
		
		ciphertext = ""; // reset the ciphertext
		
		// input validation
		if (plaintext == null) {
			System.out.println("Error. Plaintext is null.");
			return;
		}
		
		
		if (plaintext.length() == 0) {
			System.out.println("Error. Empty plaintext.");
			return;
		}
		
		if (!hasLetter(plaintext)) {
			System.out.println("Error. Plaintext has no letters.");
			return;
		}
		
		for (int i = 0; i < key.length; ++i) {
			
			if (key[i] < 1 || key[i] > key.length) {
				System.out.println("Error. Invalid key.");
				return;
			}
			
		}
		
		
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
			
			if (ciphertext.length() % key.length == 0 && i != 0) {
				System.out.println(ciphertext);
				currentBlock += key.length;
				
			}
			
			int mixedIndex = key[i % key.length] - 1 + currentBlock;
			
			char chara = middleText.charAt(mixedIndex);
			
			ciphertext += chara;
			
		}
		
		
		System.out.println("The end result is: " + ciphertext.toUpperCase());
		
	}
	

	public void decryptWithSteps() {
		
		String middleText = "";
		boolean appendedZ = false;
		int currentBlock = 0;
		
		plaintext = ""; // reset the plaintext
		
		// input validation
		if (ciphertext == null) {
			System.out.println("Error. Ciphertext is null.");
			return;
		}
				
				
		if (ciphertext.length() == 0) {
			System.out.println("Error. Empty ciphertext.");
			return;
		}
			
		if (!hasLetter(ciphertext)) {
			System.out.println("Error. Ciphertext has no letters.");
			return;
		}
				
		for (int i = 0; i < key.length; ++i) {
			
			if (key[i] < 1 || key[i] > key.length) {
				System.out.println("Error. Invalid key.");
				return;
			}
			
		}
		
				
		for (int i = 0; i < ciphertext.length(); ++i) {
					// check if the character is a letter
			if ((ciphertext.charAt(i) >= 'a' && ciphertext.charAt(i) <= 'z') || (ciphertext.charAt(i) >= 'A' && ciphertext.charAt(i) <= 'Z')) {
				middleText += ciphertext.charAt(i);
			}
			
		}
		
		middleText = middleText.toUpperCase();
		
		System.out.println("We first prepare the ciphertext by removing all spaces and non letters:");
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
		
		printArrayLinearly(key);
		
		printArrayLinearly(orderedKey);
		
		System.out.println("Using the key we then place all elements in a block in the cyphertext in the bottom indecies to their new positions in the top indecies:");
		
		for (int i = 0; i < middleText.length(); ++i) {
			
			if (plaintext.length() % key.length == 0 && i != 0) {
				System.out.println(plaintext);
				currentBlock += key.length;
				
			}
			
			int mixedIndex = 0;
			
			
			// finding the smallest top index in the current block
			// we need this since we decrypt from bottom to top, doing this effectively requires the top to be sorted, for which it is not.
			for (int j = 0; j < key.length; ++j) {
				
				if (key[j] == i % key.length + 1) {
					mixedIndex = j;
				}
				
			}
			
			mixedIndex += currentBlock;
			
			char chara = middleText.charAt(mixedIndex);
			
			plaintext += chara;
			
		}
		
		
		System.out.println("The end result is: " + plaintext.toLowerCase());
		
	}
	
	
	public static void printArrayLinearly(int[] array) {
		
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	
	/*
	public static void main(String[] args) {
		
		KeyedTranspositionEncryptDecrypt k = new KeyedTranspositionEncryptDecrypt(4);
		
		k.key[0] = 2;
		k.key[1] = 3;
		k.key[2] = 4;
		k.key[3] = 0;
		
		k.plaintext = "Hello";
		
		k.encryptWithSteps();
		
	}
	*/
}
