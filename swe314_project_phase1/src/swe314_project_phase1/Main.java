package swe314_project_phase1;

public class Main {

	public static void main(String[] args) {
		
		KeyedTranspositionEncryptDecrypt kt = new KeyedTranspositionEncryptDecrypt(4);
		CryptanalysisDecryptor cd = new CryptanalysisDecryptor();
		PlayfairEncrypter pl = new PlayfairEncrypter();
		
		pl.promptKey();
		pl.setPlaintext("Hello There");
		
		pl.encryptWithSteps();
		
		/*
		kt.promptKey();
		kt.setPlaintext("Hello there friend!!!");
		kt.encryptWithSteps();
		*/
		
		/*
		cd.setCyphertext("OGGVOGVJGTG");
		cd.decrypt();
		*/
	}
	
}
