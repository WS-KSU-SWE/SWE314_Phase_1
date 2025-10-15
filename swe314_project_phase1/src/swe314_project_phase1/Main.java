package swe314_project_phase1;

public class Main {

	public static void main(String[] args) {
		
		KeyedTranspositionEncryptDecrypt kt = new KeyedTranspositionEncryptDecrypt(4);
		CryptanalysisDecryptor cd = new CryptanalysisDecryptor();
		//kt.promptKey();
		//kt.encryptWithSteps("Hello there friend!!!");
		
		
		cd.decrypt("OGGVOGVJGTG");
		
	}
	
}
