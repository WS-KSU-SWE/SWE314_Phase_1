package swe314_project_phase1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayfairEncrypter extends EncrypterDecrypter {
	
	/*
	private String[][] key = {
			{"L", "G", "D", "B", "A"}, 
			{"Q", "M", "H", "E", "C"},
			{"U", "R", "N", "I/J", "F"},
			{"X", "V", "S", "O", "K"},
			{"Z", "Y", "W", "T", "P"}
			};
	*/
	private String[][] key;
	private int doubleLetterRow;
	private int doubleLetterCol;
	
	
	public PlayfairEncrypter() {
		key = new String[5][5];
	}
	
	
	public boolean isInKey(char val) {
		
		if (Character.isLowerCase(val)) {
			val = Character.toUpperCase(val);
		}
		
		for (int i = 0; i < key.length; ++i) {
			for (int j = 0; j < key[i].length; ++j) {
				
				if (key[i][j] == null) {
					continue;
				}
				
				if (key[i][j].length() == 1) {
					
					if (key[i][j].charAt(0) == val) {
						return true;
					}
					
					
				}
				else if (key[i][j].length() == 3) {
					
					if (key[i][j].charAt(0) == val || key[i][j].charAt(2) == val) {
						return true;
					}
				
				}
				
			}
		}
		
		
		return false;
	}
	
	
	public void promptKey() {
		
		Scanner input = new Scanner(System.in);
		
		int doubleCharRow = 0;
		int doubleCharCol = 0;
		char firstDoubleChar = '\0';
		char secondDoubleChar = '\0';
		
		boolean isValid = false;
		
		do {
			
			try {
				
				System.out.print("Enter the row that will have 2 characters (1 to 5): ");
				doubleCharRow = input.nextInt();
				
				if (doubleCharRow > 5 || doubleCharRow < 1) {
					System.out.println("Please enter a value between 1 and 5 (inclusive)");
					
				}
				else {
					isValid = true;
				}
				
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
			}
			
			
			
		} while (!isValid);
		
		
		isValid = false;
		
		do {
			
			try {
				
				System.out.print("Enter the column that will have 2 characters (1 to 5): ");
				doubleCharCol = input.nextInt();
				
				if (doubleCharCol > 5 || doubleCharCol < 1) {
					System.out.println("Please enter a value between 1 and 5 (inclusive)");
					
				}
				else {
					isValid = true;
				}
				
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
			}
			
			
			
		} while (!isValid);
		
		doubleLetterRow = doubleCharRow - 1;
		doubleLetterCol = doubleCharCol - 1;
		
		
		isValid = false;
		
		
		do {
			
			System.out.print("Enter the first character: ");
			firstDoubleChar = input.next().charAt(0);
			
			
			if (!Character.isLetter(firstDoubleChar)) {
				System.out.println("Please enter a letter");
			}
			else {
				isValid = true;
			}
			
			
		} while (!isValid);
		
		
		do {
			
			System.out.print("Enter the second character: ");
			secondDoubleChar = input.next().charAt(0);
			
			
			if (!Character.isLetter(secondDoubleChar)) {
				System.out.println("Please enter a letter");
			}
			else if (secondDoubleChar == firstDoubleChar){
				System.out.println("Please enter a different letter");
			}
			else {
				isValid = true;
			}
			
			
		} while (!isValid);
		
		firstDoubleChar = Character.toUpperCase(firstDoubleChar);
		secondDoubleChar = Character.toUpperCase(secondDoubleChar);
		
		key[doubleLetterRow][doubleLetterCol] = firstDoubleChar + "\\" + secondDoubleChar;
		
		
		for (int i = 0; i < key.length; ++i) {
			
			for (int j = 0; j < key[i].length; ++j) {
				
				char chara = '\0';
				
				if ((i == doubleLetterRow) && (j == doubleLetterCol)) {
					continue;
				}
				
				isValid = false;
				
				do {
					
					System.out.print("Enter the letter for row "+ (i + 1) + ", column " + (j + 1) + ": ");
					chara = input.next().charAt(0);
					
					if (!Character.isLetter(chara)) {
						System.out.println("Please enter a letter");
					}
					else if (isInKey(chara)){
						System.out.println("Please enter a different letter");
					}
					else {
						isValid = true;
					}
					
				} while (!isValid);
				
				chara = Character.toUpperCase(chara);
				
				key[i][j] = "" + chara;
				
			}
			
		}
		
		
	}
	
	
	public void printKeyMatrix() {
		
		for (int i = 0; i < key.length; ++i) {
			for (int j = 0; j < key[i].length; ++j) {
				
				System.out.print(key[i][j] + " ");
				
				if (i != doubleLetterRow && j == doubleLetterCol) {
					System.out.print("  ");
				}
				
			}
			System.out.println();
		}
		
	}
	
	
	public Cell findCell(char val) {
		
		for (int i = 0; i < key.length; ++i) {
			for (int j = 0; j < key[i].length; ++j) {
				
				if (key[i][j].length() == 1) {
					
					if (key[i][j].charAt(0) == val) {
						return new Cell(i, j);
					}
					
					
				}
				else if (key[i][j].length() == 3) {
					
					if (key[i][j].charAt(0) == val || key[i][j].charAt(2) == val) {
						return new Cell(i, j);
					}
				
				}
				
			}
		}
		
		return null;
	}
	
	
	public String prepairPlaintext() {
		
		String middleText1 = "";
		String middleText2 = "";
		
		for (int i = 0; i < plaintext.length(); ++i) {
			// check if the character is a letter
			if ((plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z') || (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z')) {
				middleText1 += plaintext.charAt(i);
			}
		
		}
		
		middleText1 = middleText1.toUpperCase();
		
		for (int i = 0; i < middleText1.length(); i += 2) {
			
			char letter1 = middleText1.charAt(i);
			
			if (i == middleText1.length() - 1) {
				
				middleText2 += letter1;
				
				if (middleText2.length() % 2 == 1) {
					middleText2 += "Z";
				}
				
			}
			else {
				
				char letter2 = middleText1.charAt(i + 1);
				
				Cell letter1Cell = findCell(letter1);
				Cell letter2Cell = findCell(letter2);
				
				if (letter1Cell.equals(letter2Cell)) {
					
					i--;
					letter2 = 'X';
				}
				
				middleText2 += letter1;
				middleText2 += letter2;
				
			}
			
		}
		
		
		return middleText2;
	}
	
	
	public void encryptWithSteps() {
		
		cyphertext = "";
		String middleText = prepairPlaintext();
		
		
		printKeyMatrix();
		System.out.println("First, we prepare the plaintext by inserting an 'X' between 2 letters that are paired together");
		System.out.println("and share the same cell in the key and then add a 'Z' at the end if the resulting lenght is odd:");
		
		System.out.println(middleText);
		
		for (int i = 0; i < middleText.length(); i += 2) {
			
			char letter1 = middleText.charAt(i);
			char letter2 = middleText.charAt(i + 1);
			
			Cell letter1Cell = findCell(letter1);
			Cell letter2Cell = findCell(letter2);
			
			char cypherChar1 = '\0';
			char cypherChar2 = '\0';
			
			if (letter1Cell.getRow() == letter2Cell.getRow()) {
				
				int row = letter1Cell.getRow();
				int col1 = letter1Cell.getCol();
				int col2 = letter2Cell.getCol();
				
				int cypherCol1 = (col1 + 1) % 5;
				int cypherCol2 = (col2 + 1) % 5;
				
				cypherChar1 = key[row][cypherCol1].charAt(0); 
				cypherChar2 = key[row][cypherCol2].charAt(0); 
				
				System.out.println("Since " + letter1 + " and " + letter2 + " are on the same row in the matrix, we shift one unit right:");
				
			}
			
			else if (letter1Cell.getCol() == letter2Cell.getCol()) {
				
				int col = letter1Cell.getCol();
				int row1 = letter1Cell.getRow();
				int row2 = letter2Cell.getRow();
				
				int cypherRow1 = (row1 + 1) % 5;
				int cypherRow2 = (row2 + 1) % 5;
				
				cypherChar1 = key[cypherRow1][col].charAt(0); 
				cypherChar2 = key[cypherRow2][col].charAt(0); 
				
				System.out.println("Since " + letter1 + " and " + letter2 + " are on the same column in the matrix, we shift one unit down:");
			}
			
			else {
				cypherChar1 = key[letter1Cell.getRow()][letter2Cell.getCol()].charAt(0);
				cypherChar2 = key[letter2Cell.getRow()][letter1Cell.getCol()].charAt(0);
				
				System.out.println("We swap the columns for " + letter1 + " and " + letter2 + ":");
				
			}
			
			cyphertext += cypherChar1;
			cyphertext += cypherChar2;
			
			System.out.println(cyphertext);
			
		}
		
		
	}
	
}
