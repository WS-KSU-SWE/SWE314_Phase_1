package swe314_project_phase1;

public class EncrypterDecrypter {

	protected String plaintext;
	protected String ciphertext;
	
	
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}
	
	
	public void setCyphertext(String cyphertext) {
		this.ciphertext = cyphertext;
	}
	
	public boolean hasLetter(String str) {
		
		if (str == null) {
			return false;
		}
		
		
		for (int i = 0; i < str.length(); ++i) {
			
			char chara = str.charAt(i);
			
			if (Character.isLetter(chara)) {
				return true;
			}
			
		}
		
		return false;
	}
	
}
