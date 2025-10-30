package swe314_project_phase1;

import java.util.*;

public class ShiftEncryptDecrypt extends EncrypterDecrypter {

	private int key;
	
	public void promptKey() {
		
		Scanner input = new Scanner(System.in);
		key = 0;
		
		System.out.print("Please enter a non-zero integer for the key: ");
		
		do {
			
			try {
				key = input.nextInt();
				
				if (key == 0) {
					System.out.print("We cannot encrypt or decrypt data using a key of 0, please enter a non-zero key: ");
				}
				
			}
			
			catch (InputMismatchException e) {
				input.nextLine();
				System.out.print("Please enter an integer: ");
			}
		
		} while (key == 0);
		
	}
	
	public void encryptWithSteps() {
		
		String cleanPlaintext = "";
		ciphertext = ""; // reset the ciphertext
		
		System.out.println("First, we have to remove all non-letter characters from the plaintext and set all letters to lowercase: ");
		
		plaintext = plaintext.toLowerCase();
		
		for (int i = 0; i < plaintext.length(); ++i) {
			
			char chara = plaintext.charAt(i);
			
			if (Character.isLetter(chara)) {
				cleanPlaintext += chara;
			}
			
		}
		
		plaintext = cleanPlaintext;
		
		System.out.println("Plaintext: " + plaintext);
		
		System.out.println("Next, we have to shift the letters in the plaintext by the key value, + means shift right, - means shift left. ");
		System.out.println("We loop around when we reach the end of the alphabet.");
		System.out.println("Key: " + key);
		
		
		// print the alphabet 
		char letter = 'A';
		for (int i = 0; i < 26; ++i) {
			
			System.out.print(letter + " ");
			letter = (char) (letter + 1); 
			
		}
		System.out.println();
		
		for (int i = 0; i < plaintext.length(); i++) {
			
			char pLetter = plaintext.charAt(i); // Compute numeric value 
			int pValue = pLetter - 'a';
			int shifted; 
			
			if (key > 0) {
				shifted = (pValue + key) % 26; 
			} 
			else { 
					shifted = (26 + (pValue + key)) % 26;
			} 
			
			// Convert back to letter in capital form 
			char cipherLetter = (char) (shifted + 'A'); // Append to ciphertext 
			
			System.out.println(pLetter + " -- (" + key + ") --> " + cipherLetter);
			
			ciphertext += cipherLetter; 
			} 
		
		
		System.out.println("Result: " + ciphertext);
	}
	
	

	
	
	public void decryptWithSteps() {
		
		String cleanCiphertext = "";
		
		plaintext = ""; // // reset the plaintext
		
		System.out.println("First, we have to remove all non-letter characters from the ciphertext and set all letters to uppercase: ");
		
		ciphertext = ciphertext.toUpperCase();
		
		for (int i = 0; i < ciphertext.length(); ++i) {
			
			char chara = ciphertext.charAt(i);
			
			if (Character.isLetter(chara)) {
				cleanCiphertext += chara;
			}
			
		}
		
		ciphertext = cleanCiphertext;
		
		System.out.println("Ciphertext: " + ciphertext);
		
		System.out.println("Next, we have to shift the letters in the ciphertext by the key value, + means shift right, - means shift left. ");
		System.out.println("We loop around when we reach the end of the alphabet.");
		System.out.println("Key: " + key);
		
		
		// print the alphabet 
		char letter = 'A';
		for (int i = 0; i < 26; ++i) {
			
			System.out.print(letter + " ");
			letter = (char) (letter + 1); 
			
		}
		System.out.println();
		
		for (int i = 0; i < ciphertext.length(); i++) {
			
			char cLetter = ciphertext.charAt(i); // Compute numeric value 
			int cValue = cLetter - 'A'; 
			int shifted; 
			
			if (key > 0) {
				shifted = (cValue + key) % 26; 
			} 
			else { 
					shifted = (26 + (cValue + key)) % 26;
			} 
			
			// Convert back to letter in capital form 
			char plainLetter = (char) (shifted + 'a'); // Append to plaintext 
			
			System.out.println(cLetter + " -- (" + key + ") --> " + plainLetter);
			
			plaintext += plainLetter; 
			} 
		
		
		System.out.println("Result: " + plaintext);
		
	}
	
	
	public void reverseKey() {
		key = -key;
	}
	
}
