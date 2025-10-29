package swe314_project_phase1;

public class VigenereEncrypter extends EncrypterDecrypter {

	private String key;
	
	public void encrpytWithSteps() {
	
        int keyIndex = 0;
        int keyLength = key.length();
        String cleanPlaintext = "";
        String cleanKey = "";
        
        int[] numericPlaintext;
        int[] numericKey;
        int[] numericCiphertext;
        
        ciphertext = "";
        
        System.out.println("First, we have to remove all non-letter characters from the plaintext and set all letters to lowercase: ");
        
        plaintext = plaintext.toLowerCase();
        
        for (int i = 0; i < plaintext.length(); ++i) {
        	
        	char ch = plaintext.charAt(i);
        	
        	if (Character.isLetter(ch)) {
        		cleanPlaintext += ch;
        	}
        	
        }
        
        plaintext = cleanPlaintext;
        
        System.out.println("Cleaned plaintext: " + plaintext);
        
        System.out.println("Then, we have to remove all non-letter characters from the key and set all letters to uppercase: ");
        
        key = key.toUpperCase();
        
        for (int i = 0; i < key.length(); ++i) {
        	
        	char ch = key.charAt(i);
        	
        	if (Character.isLetter(ch)) {
        		cleanKey += ch;
        	}
        	
        }
        
        key = cleanKey;
        
        System.out.println("Cleaned key: " + key);
        
        System.out.println("We then line up the plaintext and the key, repating the key once we reach the end:");
        
        System.out.println("Plaintext: ");
        
        for (int i = 0; i < plaintext.length(); ++i) {
        	System.out.print(plaintext.charAt(i) + " "); // the space is for the looks
        }
        
        System.out.println();
        
        System.out.println("Key: ");
        
        for (int i = 0; i < plaintext.length(); ++i) {
        	System.out.print(key.charAt(i % key.length()) + " "); // the space is for the looks
        }
        
        System.out.println();
        
        System.out.println("After than, we set all of the letters to their numeric value: (a = 0, b = 1, ..., z = 25)");
        
        numericPlaintext = new int[plaintext.length()];
        numericKey = new int[plaintext.length()];
        numericCiphertext = new int[plaintext.length()];
        
        System.out.println("Plaintext: ");
        
        for (int i = 0; i < plaintext.length(); ++i) {
        	
        	numericPlaintext[i] = plaintext.charAt(i) - 'a';
        	System.out.print(numericPlaintext[i] + " "); 
        
        }
        
        System.out.println();
        
        System.out.println("Key: ");
        
        for (int i = 0; i < plaintext.length(); ++i) {
        
        	numericKey[i] = key.charAt(i % key.length()) - 'A';
        	System.out.print(numericKey[i] + " ");
        
        }
        
        System.out.println();
        
        System.out.println("We then add the column values together and apply mod 26 to the result.");
        System.out.println("For example the value of the first column is: " );
        
        System.out.println("(" + numericPlaintext[0] + " + " + numericKey[0] + ") mod 26 = " + (numericPlaintext[0] + numericKey[0]) % 26);
        
        for (int i = 0; i < plaintext.length(); i++) {
        	
        	numericCiphertext[i] = (numericPlaintext[i] + numericKey[i]) % 26;
        	System.out.print(numericCiphertext[i] + " "); 
        	
        }
		
        
        System.out.println();
        System.out.println("We finally return these numbers to letters:");
        System.out.println("Ciphertext: ");
        
        for (int i = 0; i < plaintext.length(); i++) {
        	
        	ciphertext += (char) (numericCiphertext[i] + 'A');
        	System.out.print(ciphertext.charAt(i) + " "); 
        	
        }
        
        
        System.out.println("Result: " + ciphertext);
        
	}
	
	
	public void setKey(String key) {
		this.key = key;
	}
	
}
