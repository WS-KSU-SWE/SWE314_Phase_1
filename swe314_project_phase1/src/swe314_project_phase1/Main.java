package swe314_project_phase1;

import java.util.*;

public class Main {

	private static Scanner input = new Scanner(System.in);
	
	private static KeyedTranspositionEncryptDecrypt keyedTransposition = new KeyedTranspositionEncryptDecrypt(4);
	private static PlayfairEncrypter playfair = new PlayfairEncrypter();
	private static VigenereEncrypter vigenere = new VigenereEncrypter();
	private static ShiftEncryptDecrypt shift = new ShiftEncryptDecrypt();
	private static DESEncrypter desEnc = new DESEncrypter();
	
	public static void encrypt() {
		
		int choice;
		String plaintext = "";
		
		System.out.println("1) Shift");
		System.out.println("2) Playfair");
		System.out.println("3) Vigenere");
		System.out.println("4) Keyed Transposition");
		System.out.println("5) DES (Data Encryption Standard)");
		System.out.println("6) Shift and Keyed Transposition");
		System.out.println("7) Exit");
		
		System.out.print("Please enter the choice related to desired encryption algorithm: ");
		
		choice = CryptanalysisDecrypter.getChoice(1, 7);
		
		
		if (choice != 5 && choice != 7) {
			
			boolean valid = false;
			
			System.out.print("Please enter the plaintext: ");
			
			while (!valid) {
				
				plaintext = input.nextLine();
				
				if (EncrypterDecrypter.hasLetter(plaintext)) {
					valid = true;
				}
				else {
					System.out.print("Please enter letters for the plaintext: ");
				}
				
			}
			
			
		}
		
		
		switch (choice) {
		
		case 1:
			
			shift.setPlaintext(plaintext);
			shift.promptKey();
			shift.encryptWithSteps();
			
			break;
			
		case 2:
			
			playfair.promptKey();
			playfair.setPlaintext(plaintext);
			playfair.encryptWithSteps();
			
			break;
		
		case 3:
			
			vigenere.promptKey();
			vigenere.setPlaintext(plaintext);
			vigenere.encrpytWithSteps();
			
			break;
		
		case 4:
			
			keyedTransposition.promptKey();
			keyedTransposition.setPlaintext(plaintext);
			keyedTransposition.encryptWithSteps();
			
			break;
		
		case 5:
			
			desEnc.promptPlaintext();
			desEnc.promptKey();
			desEnc.encrypt();
			
			break;
		
		case 6:
			
			shift.setPlaintext(plaintext);
			System.out.println("Shift key:");
			shift.promptKey();
			System.out.println("Keyed transposition key:");
			keyedTransposition.promptKey();
			
			System.out.println("\nStep 1: shift cipher:\n");
			shift.encryptWithSteps();
			
			keyedTransposition.setPlaintext(shift.getCiphertext());
			
			System.out.println("\nStep 2: keyed transposition:\n");
			
			keyedTransposition.encryptWithSteps();
			
			break;
		
		default:
			return;
			
		}
		
		System.out.println();
		
	}
	
	
	public static void decrypt() {
		
		int choice;
		String ciphertext = "";
		
		CryptanalysisDecrypter cryptanalysis = new CryptanalysisDecrypter();
		
		System.out.println("1) Shift");
		System.out.println("2) Keyed Transposition");
		System.out.println("3) Cryptanalysis");
		System.out.println("4) Exit");
		
		System.out.print("Please enter the choice related to desired encryption algorithm: ");
		
		choice = CryptanalysisDecrypter.getChoice(1, 4);
		
		boolean valid = false;
		
		switch (choice) {
		
		case 1:
			
			if (shift.getCiphertext() != "") {
				
				System.out.println("The system has stored the previous encryption using this method with its key.");
				System.out.print("Would you like to decrypt this ciphertext? (y/n): "); // we will treat any invalid input as a no (n)
				
				char decreptChoice = input.nextLine().charAt(0);
				
				if (decreptChoice == 'y') {
					shift.reverseKey();
					shift.decryptWithSteps();
					break;
				}
				
			}
			
			valid = false;
			
			System.out.print("Please enter the ciphertext: ");
			
			while (!valid) {
				
				ciphertext = input.nextLine();
				
				if (EncrypterDecrypter.hasLetter(ciphertext)) {
					valid = true;
				}
				else {
					System.out.print("Please enter letters for the ciphertext: ");
				}
				
			}
			
			
			shift.setCiphertext(ciphertext);
			shift.promptKey();
			shift.decryptWithSteps();
			
			break;
		
		case 2:
			
			if (keyedTransposition.getCiphertext() != "") {
				
				System.out.println("The system has stored the previous encryption using this method with its key.");
				System.out.print("Would you like to decrypt this ciphertext? (y/n): "); // we will treat any invalid input as a no (n)
				
				char decreptChoice = input.nextLine().charAt(0);
				
				if (decreptChoice == 'y') {
					
					keyedTransposition.decryptWithSteps();
					break;
				}
				
			}
			

			valid = false;
			
			System.out.print("Please enter the ciphertext: ");
			
			while (!valid) {
				
				ciphertext = input.nextLine();
				
				if (EncrypterDecrypter.hasLetter(ciphertext)) {
					valid = true;
				}
				else {
					System.out.print("Please enter letters for the ciphertext: ");
				}
				
			}
			
			keyedTransposition.setCiphertext(ciphertext);
			keyedTransposition.promptKey();
			keyedTransposition.decryptWithSteps();
			
			break;
			
		case 3: 
			
			valid = false;
			
			System.out.print("Please enter the ciphertext: ");
			
			while (!valid) {
				
				ciphertext = input.nextLine();
				
				if (EncrypterDecrypter.hasLetter(ciphertext)) {
					valid = true;
				}
				else {
					System.out.print("Please enter letters for the ciphertext: ");
				}
				
			}
			
			
			cryptanalysis.setCiphertext(ciphertext);
			cryptanalysis.decrypt();
			
			break;
			
		default: 
			return;
		
		}
		
		System.out.println();
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		
		int choice = 0;
		
		while (choice != 3) {
			
			System.out.println("1) Encrypt data");
			System.out.println("2) Decrypt data");
			System.out.println("3) Exit");
			
			System.out.print("Please select an option (1, 2, 3): ");
			choice = CryptanalysisDecrypter.getChoice(1, 3);
			
			switch (choice) {
			
			case 1:
				encrypt();
			break;
			
			case 2: 
				decrypt();
			break;
			
			default: 
				System.out.println("Goodbye");
				break;
			
			}
			
			
		}
		
		
	}
	
}
