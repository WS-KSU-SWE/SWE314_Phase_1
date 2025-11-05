package swe314_project_phase1;

import java.util.*;

/* This code is has been taken from https://github.com/deadlytea/DES and is authored by deadlytea.
 * The code has been modified in order to fit the needs of the project and any unused code and libraries from the original has been removed.
 * The remainder of this comment is dedicated to linking the attribute names in the class diagram to 
 * the attribute names in the code base
 * 
 * KEY_LENGTH	: MASTER_KEY_LENGTH
 * PC1			: firstKeyPermutationMatirx. PC1 is a 1 dimensional int array whereas firstKeyPermutationMatirx is a 2 dimensional int array
 * KEY_SHIFTS	: itterativeShiftValues
 * PC2			: secondKeyPermutationMatrix: PC2 is a 1 dimensional int array whereas secondKeyPermutationMatrix is a 2 dimensional int array
 * s1			: substitutionMatrix1
 * s2			: substitutionMatrix2
 * s3			: substitutionMatrix3
 * s4			: substitutionMatrix4
 * s5			: substitutionMatrix5
 * s6			: substitutionMatrix6
 * s7			: substitutionMatrix7
 * s8			: substitutionMatrix8
 * s			: substitutionMatrixArray. This is used in order to assist in traversing the substitution matrices in a loop
 * g			: expansionPermutationMatixFuncF. g is a 1 dimensional int array whereas expansionPermutationMatixFuncF is a 2 dimensional int array
 * p			: straightPermutationMatrixFuncF. p is a 1 dimensional int array whereas straightPermutationMatrixFuncF is a 2 dimensional int array
 * IP			: inititalPermutationMatrix. IP is a 1 dimensional int array whereas inititalPermutationMatrix is a 2 dimensional int array
 * IPi			: finalPermutationMatrix. IPi is a 1 dimensional int array whereas finalPermutationMatrix is a 2 dimensional int array
 * K			: subKeys
 * 
*/
public class DESEncrypter extends EncrypterDecrypter {

		
		public static int KEY_LENGTH = 64; 
		
		String masterKey;
		
		private static int[] PC1 = 
		{  
			57, 49, 41, 33, 25, 17,  9,
	         1, 58, 50, 42, 34, 26, 18,
	        10,  2, 59, 51, 43, 35, 27,
	        19, 11,  3, 60, 52, 44, 36,
	        63, 55, 47, 39, 31, 23, 15,
	         7, 62, 54, 46, 38, 30, 22,
	        14,  6, 61, 53, 45, 37, 29,
	        21, 13,  5, 28, 20, 12,  4
		};
		
		// First index is garbage value, loops operating on this should start with index = 1
		private static int[] KEY_SHIFTS = 
		{
			0,  1,  1,  2,  2,  2,  2,  2,  2,  1,  2,  2,  2,  2,  2,  2,  1
		};
		
		private static int[] PC2 = 
		{
			14, 17, 11, 24,  1,  5,
	         3, 28, 15,  6, 21, 10,
	        23, 19, 12,  4, 26,  8,
	        16,  7, 27, 20, 13,  2,
	        41, 52, 31, 37, 47, 55,
	        30, 40, 51, 45, 33, 48,
	        44, 49, 39, 56, 34, 53,
	        46, 42, 50, 36, 29, 32
		};
		
		
		private static int[][] s1 = {
			{14, 4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
			{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11,  9,  5,  3,  8},
			{4, 1, 14,  8, 13,  6, 2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
			{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
		 };

		private static int[][] s2 = {
				{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
				{3, 13,  4, 7, 15,  2,  8, 14, 12,  0, 1, 10,  6,  9, 11,  5},
				{0, 14, 7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
				{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14,  9}
			 };
		
		private static int[][] s3 = {
				{10, 0, 9, 14, 6, 3, 15, 5,  1, 13, 12, 7, 11, 4, 2,  8},
				{13, 7, 0, 9, 3,  4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
				{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14,  7},
				{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
			 };
		
		private static int[][] s4 = {
				{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
				{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,  9},
				{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
				{3, 15, 0, 6, 10, 1, 13, 8, 9,  4, 5, 11, 12, 7, 2, 14}
			 };
		
		private static int[][] s5 = {
				{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
				{14, 11, 2, 12,  4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
				{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
				{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
			  };
		
		private static int[][] s6 = {
				{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
				{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
				{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
				{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
			  };
		
		private static int[][] s7 = {
				{4, 11, 2, 14, 15,  0, 8, 13 , 3, 12, 9 , 7,  5, 10, 6, 1},
				{13 , 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
				{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
				{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
			  };
		
		private static int[][] s8 = {
				{13, 2, 8,  4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
				{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6 ,11, 0, 14, 9, 2},
				{7, 11, 4, 1, 9, 12, 14, 2,  0, 6, 10 ,13, 15, 3, 5, 8},
				{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6 ,11}
			};
		
		private static int[][][] s = {s1, s2, s3, s4, s5, s6, s7, s8};
		
		private static int[] g = 
		{
			32,  1,  2,  3,  4,  5,
			 4,  5,  6,  7,  8,  9, 
			 8,  9, 10, 11, 12, 13, 
			12, 13, 14, 15, 16, 17,
			16, 17, 18, 19, 20, 21, 
			20, 21, 22, 23, 24, 25, 
			24, 25, 26, 27, 28, 29, 
			28, 29, 30, 31, 32,  1
		};
			
		
		static int[] p = 
		{
			16,  7, 20, 21, 
			29, 12, 28, 17, 
			 1, 15, 23, 26, 
			 5, 18, 31, 10, 
			 2,  8, 24, 14, 
			32, 27,  3,  9, 
			19, 13, 30,  6, 
			22, 11,  4, 25
		};
		
		static int[] IP = 
		{
			 58, 50, 42, 34, 26, 18, 10 , 2,
	         60, 52, 44, 36, 28, 20, 12, 4,
	         62, 54, 46, 38, 30, 22, 14, 6,
	         64, 56, 48, 40, 32, 24, 16, 8,
	         57, 49, 41, 33, 25, 17, 9, 1,
	         59, 51, 43, 35, 27, 19, 11, 3,
	         61, 53, 45, 37, 29, 21, 13, 5,
	         63, 55, 47, 39, 31, 23, 15, 7
		};
		
		static int[] IPi = 
		{
				40, 8, 48, 16, 56, 24, 64, 32,
		        39, 7, 47, 15, 55, 23, 63, 31,
		        38, 6, 46, 14, 54, 22, 62, 30,
		        37, 5, 45, 13, 53, 21, 61, 29,
		        36, 4, 44, 12, 52, 20, 60, 28,
		        35, 3, 43 ,11, 51, 19, 59, 27,
		        34, 2, 42, 10, 50, 18, 58, 26,
		        33, 1, 41, 9, 49, 17, 57, 25
		};
		
		private long[] K;
		
		
		
		public DESEncrypter() {
			
			// First index is garbage value, loops operating on this should start with index = 1
			K = new long[17];
			
		}
		
		
		/* The encrypt method is used to generate a key list an break down the message into 64 bit blocks.
		 * Since we are developing an educational system, we have limited the max input length to 64 bits and therefore the breaking down process is useless
		 * However, this process will still be kept in the final code in order to not deviate too much from the original implementation.
		 * The original code used to accept any piece of text as a key and used a hash function to change this key into a long. For the same reason as before, 
		 * we have limited the key to only be a binary value with a max length of 64 bits. The hash function has been replaced by Long.parseLong(key, 2), where 2 
		 * refers to the base.
		*/
		/**
		 * Encrypt a string message with the DES block cipher 
		 * @param key
		 * @param plaintext
		 * @return
		 */
		// The encrypt method has been modified in order to conform to the rest of the encryption by removing its parameters.
		public String encrypt() {
			
			final int blockSize = 64;
			
			// input validation
			if (masterKey == null) {
				System.out.println("Error: key is null");
				return "";
			}
			
			if (plaintext == null) {
				System.out.println("Error: plaintext is null");
				return "";
			}
			
			
			if (masterKey.length() > KEY_LENGTH) {
				
				System.out.println("Error: Invalid key length");
				return "";
				
				//throw new RuntimeException("Invalid key length");
			}
			
			if (masterKey.length() == 0) {
				
				System.out.println("Error: Empty key entered");
				return "";
			}
			
			if (plaintext.length() > blockSize) {
				
				System.out.println("Error: Invalid plaintext length");
				return "";
				
				//throw new RuntimeException("Invalid key length");
			}
			
			if (plaintext.length() == 0) {
				
				System.out.println("Error: Empty plaintext entered");
				return "";
			}
			
			// check if key is binary 
			try {
				Long.parseLong(masterKey, 2); // parses masterKey into a long in base 2 (directly puts the bits into the space of a long) if it fails, then it is not binary
			}
			catch (Exception e) {
				System.out.println("Error: Nonbinary key entered");
				return "";
			}
			
			// check if plaintext is binary
			try {
				Long.parseLong(plaintext, 2); // parses plaintext into a long in base 2 (directly puts the bits into the space of a long) if it fails, then it is not binary
			}
			catch (Exception e) {
				System.out.println("Error: Nonbinary plaintext entered");
				return "";
			}
			
			
			while(masterKey.length() < KEY_LENGTH) {
				masterKey = "0" + masterKey;
			}
			
			while(plaintext.length() < blockSize) {
				plaintext = "0" + plaintext;
			}
			
			// creates a list of keys
			buildKeySchedule(Long.parseLong(masterKey, 2));
			///;;;;;;;;;;;;;;/"
			String binPlaintext = plaintext;
			
			// Add padding if necessary
			int remainder = binPlaintext.length() % 64;
			if (remainder != 0) {
				for (int i = 0; i < (64 - remainder); i++)
					binPlaintext = "0" + binPlaintext;
			}
			
			// Separate binary plaintext into blocks
			String[] binPlaintextBlocks = new String[binPlaintext.length()/64];
			int offset = 0;
			for (int i = 0; i < binPlaintextBlocks.length; i++) {
				binPlaintextBlocks[i] = binPlaintext.substring(offset, offset+64);
				offset += 64;
			}
			
			String[] binCiphertextBlocks = new String[binPlaintext.length()/64];
			
			// Encrypt the blocks
			for (int i = 0; i < binCiphertextBlocks.length; i++)
				try {
					binCiphertextBlocks[i] = encryptBlock(binPlaintextBlocks[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// Build the ciphertext binary string from the blocks
			String binCiphertext = "";
			for (int i = 0; i < binCiphertextBlocks.length; i++) 
				binCiphertext += binCiphertextBlocks[i];
				
			// Destroy key schedule
			for (int i=0;i<K.length;i++)
				K[i] = 0;
			
			
			return binCiphertext;
		}
		
		
		/* This method mirrors 2 separate methods in the class diagram: encryptWithSteps, and round
		 * This code base merges all the steps for the rounds and the initial and final permutation for the message into a single method,
		 * instead of having the steps for a round in another method and calling the round method in the encrypt method.
		 */
		public String encryptBlock(String plaintextBlock) throws Exception {
			
			int length = plaintextBlock.length();
			
			if (length != 64) {
				
				System.out.println("Input block length is not 64 bits!");
				return "";
				//throw new RuntimeException("Input block length is not 64 bits!");
			}
				
			
			System.out.println("Step 2: Encrypt the plaintext\n");
			System.out.println("Given our plaintext: " + plaintextBlock);
			
			//Initial permutation
			String out = "";
			for (int i = 0; i < IP.length; i++) {
				out = out + plaintextBlock.charAt(IP[i] - 1);	
			}
			
			System.out.println("We first permute the bits in the plaintext: " + out);
			System.out.println("We then enter the first of 16 encryption rounds. We will show the process for the first round.");
			System.out.println("The rest of the rounds are the same, except we just use the output of one round as the input of another");
			System.out.println("and apply that round's 48-bit key to create the next output.");
			String mL = out.substring(0, 32);
			String mR = out.substring(32);
		
			System.out.println("We first split our permuted plaintext into 2 halfs, left (L) and right (R): ");
			System.out.println("L: " + mL);
			System.out.println("R: " + mR);
			
			for (int i = 0; i < 16; i++) {
				
				// 48-bit current key
				String curKey = Long.toBinaryString(K[i+1]);
				
				while(curKey.length() < 48)
					curKey = "0" + curKey;
				
				// done only for round 1
				if (i == 0) {
					System.out.println("We then go into the heart of DES. Function f takes in R and the current round key");
					System.out.println("We will now go into the details of function f.");
				}
				
				
				// Get 32-bit result from f with m1 and ki
				String fResult = f(mR, curKey, i);
				
				// XOR m0 and f
				long f = Long.parseLong(fResult, 2);
				long cmL = Long.parseLong(mL, 2);
				
				long m2 = cmL ^ f;
				String m2String = Long.toBinaryString(m2);
				
				while(m2String.length() < 32)
					m2String = "0" + m2String;
				
				if (i == 0) {
					System.out.println("We now take resultF and XOR it with L: ");
					System.out.println("resultF : " + fResult);
					System.out.println("L       : " + mL + " XOR");
					System.out.println("         -------------------------------------");
					System.out.println("result: " + m2String);
					System.out.println("We will call this result xor_LF");
					
					System.out.println("The last step of this round is to swap the bits in R and xor_LF, the result of which will be called swaped.");
					System.out.println("swaped: " + mR + m2String);
					System.out.println("This will be the input for the next round.");
					System.out.println("We repeat all these steps 16 times, using the output of one round as the input of the next.");
				}
				
				
				
				mL = mR;
				mR = m2String;	
			}
			
			
			
			
			String in = mR + mL;
			String output = "";
			for (int i = 0; i < IPi.length; i++) {
				output = output + in.charAt(IPi[i] - 1);
			}
			
			System.out.println("At the end of all 16 rounds, we end up with this: ");
			System.out.println(in);
			System.out.println("We finally permute these bits to get our final result: ");
			System.out.println(output);
			
			
			return output;
		}
		
		
		// this method mirrors generateSubkeys in the class diagram
		public void buildKeySchedule(long key) {
			
			// Convert long value to 64bit binary string
			String binKey = Long.toBinaryString(key);
			
			// Add leading zeros if not at key length for ease of computations
			while (binKey.length() < 64) 
				binKey = "0" + binKey;
			
			System.out.println("Step 1: Generate keys\n");
			System.out.println("given the master key: " + binKey + "\nWe first have to compress the key from 64 bit to 56 bits and permute its bits.");
			System.out.println("We do this using a permutaiton matrix: ");
			
			for (int i = 0; i < 8; ++i) {
				for (int j = 0; j < 7; ++j) {
					System.out.print(PC1[j + i*7] + " ");
				}
				System.out.println();
			}
			
			System.out.println("This matrix tells us that bit " + PC1[0] + " in our master key will be the first bit in out permuted key and that ");
			System.out.println("bit " + PC1[1] + " will be the second bit, and so on.");
			
			// For the 56-bit permuted key 
			String binKey_PC1 = "";
			
			// Apply Permuted Choice 1 (64 -> 56 bit)
			for (int i = 0; i < PC1.length; i++)
				binKey_PC1 = binKey_PC1 + binKey.charAt(PC1[i]-1);
			
			System.out.println("Our new 56 bit key is: " + binKey_PC1);
			
			
			String sL, sR;
			int iL, iR;
			
			// Split permuted string in half | 56/2 = 28
			sL = binKey_PC1.substring(0, 28);
			sR = binKey_PC1.substring(28);
			
			System.out.println("We then split this key in half, each half having 28 bits");
			System.out.println("C0: " + sL);
			System.out.println("D0: " + sR);
			
			
			// from this point onward the code has been modified in order to properly show each step in the key generation process
			String[] leftHalf = new String[K.length];
			String[] rightHalf = new String[K.length];
			
			leftHalf[0] = sL;
			rightHalf[0] = sR;
			
			// Build the keys (Start at index 1)
			for (int i = 1; i < K.length; i++) {
				
				// Perform left shifts according to key shift array
				// the original implementation had a bug since it was using Integer.rotateLeft, which rotates a 32 bit int, but we had 28 bits, so all we are doing is just adding 0s to the start, and violating our 28 bit limit. This fixes that bug
				sL = circularShift(sL, KEY_SHIFTS[i]);
				sR = circularShift(sR, KEY_SHIFTS[i]);
				
				// Parse binary strings into integers for merging
				iL = Integer.parseInt(sL, 2);
				iR = Integer.parseInt(sR, 2);
				
				leftHalf[i] = sL;
				rightHalf[i] = sR;
				
				
				// Merge the two halves
				long merged = ((long)iL << 28) + iR;
				
				// 56-bit merged
				String sMerged = Long.toBinaryString(merged);
				
				// Fix length if leading zeros absent
				while (sMerged.length() < 56)
					sMerged = "0" + sMerged;
				
				// For the 56-bit permuted key 
				String binKey_PC2 = "";
				
				// Apply Permuted Choice 2 (56 -> 48 bit)
				for (int j = 0; j < PC2.length; j++)
					binKey_PC2 = binKey_PC2 + sMerged.charAt(PC2[j]-1);
				
				// Set the 48-bit key
				K[i] = Long.parseLong(binKey_PC2, 2);
			}
			
			System.out.println("We then itteritively shift the bits to the left by either 1 or 2: ");
			System.out.println("C0: " + leftHalf[0]);
			System.out.println("D0: " + rightHalf[0]);
			System.out.println("C1: " + leftHalf[1]);
			System.out.println("D1: " + rightHalf[1]);
			System.out.println("....");
			System.out.println("C16: " + leftHalf[16]);
			System.out.println("D16: " + rightHalf[16]);
			System.out.println();
			System.out.println("After that, merge them together:");
			System.out.println("C0_D0: " + leftHalf[0] + rightHalf[0]);
			System.out.println("C1_D1: " + leftHalf[1] + rightHalf[1]);
			System.out.println("...");
			System.out.println("C16_D16: " + leftHalf[16] + rightHalf[16]);
			System.out.println();
			System.out.println("We finally compress these vaules from 56 bits to 48 bits and permute them with another permutation matrix.");
			System.out.println("The final key starts from the resulting C1_D1 to the resulting C16_D16");
			System.out.println();
			System.out.println("The final key list is: ");
			
			for (int i = 1; i < 17; ++i) {
				String currentKey = Long.toBinaryString(K[i]);
				
				while (currentKey.length() < 48)
					currentKey = "0" + currentKey;
				
				System.out.println("key " + i + ": " + currentKey);
				
			}
			
			System.out.println();
			
		}
		
		
		private static String circularShift(String bin, int shift) {
			
			String result = "";
			
			for (int i = shift; i < bin.length(); ++i) {
				result += bin.charAt(i);
			}
			
			for (int i = 0; i < shift; ++i) {
				result += bin.charAt(i);
			}
			
			return result;
		}
		
		
		// the parameter currentRound was added to ensure that only the first round of function f is displayed to the user
		/**
		 * Feistel function in DES algorithm specified in FIPS Pub 46
		 * @param mi : String - 32-bit message binary string
		 * @param key : String - 48-bit key binary string
		 * @return 32-bit output string
		 */
		public static String f(String mi, String key, int currentRound) {
			
			// Expansion function g (named E in fips pub 46)
			String gMi = "";
			for (int i = 0; i < g.length; i++) {
				gMi = gMi + mi.charAt(g[i] - 1);
			}
			
			// step 1:
			if (currentRound == 0) {
				System.out.println("We first permute and expand R from 32 bits to 48 bits:");
				System.out.println("R: " + mi);
				System.out.println("Expanded and permuted R (Exp_R): " + gMi);
				System.out.println("We do this so we can XOR the key and Exp_R since XOR only works if the bit sizes are the same.");
			}
			
			
			
			long m =  Long.parseLong(gMi, 2);	
			long k = Long.parseLong(key, 2);
			
			// XOR expanded message block and key block (48 bits)
			Long result = m ^ k;
			
			String bin = Long.toBinaryString(result);
			// Making sure the string is 48 bits
			while (bin.length() < 48) {
				bin = "0" + bin;
			}
			
			// step 2:
			if (currentRound == 0) {
				System.out.println("We then XOR the key and Exp_R:");
				System.out.println("key   : " + key);
				System.out.println("Exp_R : " + gMi + " XOR");
				System.out.println("        -----------------------------------------------------");
				System.out.println("result: " + bin);
				System.out.println("We will call this result xor_RK");
			}
			
			String oldBin = bin;
			
			// Split into eight 6-bit strings
			String[] sin = new String[8]; // sin = substitution input
			for (int i = 0; i < 8; i++) {
				sin[i] = bin.substring(0, 6);
				bin = bin.substring(6);
			}
			
			// step 3: 
			if (currentRound == 0) {
				System.out.println("We then split xor_RK into 8, 6-bit values:");
				System.out.println("xor_RK: " + oldBin);
				
				for (int i = 0; i < 8; ++i) {
					System.out.println("B" + (i+1) + ": " + sin[i]);
				}
			
			}
			
			// Do S-Box calculations
			String[] sout = new String[8]; // sout = substitution output
			for (int i = 0 ; i < 8; i++) {
				int[][] curS = s[i];
				String cur = sin[i];
			
				// Get binary values
				int row = Integer.parseInt(cur.charAt(0) + "" + cur.charAt(5), 2); 
				int col = Integer.parseInt(cur.substring(1, 5), 2);
				
				// Do S-Box table lookup
				sout[i] = Integer.toBinaryString(curS[row][col]);
				
				// Make sure the string is 4 bits
				while(sout[i].length() < 4)
					sout[i] = "0" + sout[i];
				
			}
			
			// step 4: 
			if (currentRound == 0) {
	
				String b1 = sin[0];
				String row = "" + b1.charAt(0) + b1.charAt(5);
				String col = "" + b1.substring(1, 5);
				int rowInt = Integer.parseInt(row, 2);
				int colInt = Integer.parseInt(col, 2);
	
				System.out.println("After spliting, we then substitude the 6 bits in B1 to B8 with 4 bits.");
				System.out.println("We will show you how its done with B1 using its corresponding substiution matrix. The rest follow the same procedure.");
				System.out.println("The following is the first substitution matrix: ");
				printMatrix(s1);
				System.out.println("Given B1 we take the first and the last bit of B1 and combine them to get the row value.");
				System.out.println("We then take the value of the middle (remaining) bits as the column value.");
				System.out.println("B1: " + b1);
				System.out.println("Row value: " + row + " binary ---> decimal " + rowInt);
				System.out.println("Column value: " + col + " binary ---> decimal " + colInt);
				System.out.println("We then replace B1 with the value found at row " + rowInt + " and column " + colInt + " in the matrix:");
				System.out.println("B1 replacement = " + s1[rowInt][colInt] + " decimal ---> binary " + sout[0]);
				System.out.println("We will call this binary value S1.");
				System.out.println("We repeat these steps for B2, B3, B4, B5, B6, B7, and B8 with their respective substitution matrices.");
				System.out.println("The result is: ");
				
				for (int i = 0; i < 8; ++i) {
					System.out.println("S" + (i+1) + ": " + sout[i]);
				}
				
			}
			
			// Merge S-Box outputs into one 32-bit string
			String merged = "";
			for (int i = 0; i < 8; i++) {
				merged = merged + sout[i];
			}
			
			// Apply Permutation P
			String mergedP = "";
			for (int i = 0; i < p.length; i++) {
				mergedP = mergedP + merged.charAt(p[i] - 1);
			}
			
			// step 5:
			if (currentRound == 0) {
				System.out.println("We then merge S1 through S8 to get a 32 bit value, which we will call merged: ");
				System.out.println("merged: " + merged);
				System.out.println("We finally perform a stright permutation upon merged: ");
				System.out.println("permutated merged: " + mergedP);
				System.out.println("This is the final result of function f, which we will call resultF.");
				System.out.println("We will now return to the rest of the round:\n");
			}
			
			
			return mergedP;
		}
	
		
		// added method to print a substitution matrix
		public static void printMatrix(int[][] matrix) {
			
			System.out.print("    ");
			
			for (int i = 0; i < matrix[0].length; ++i) {
				System.out.print(i + " ");
			}
			
			System.out.println();
			
			for (int i = 0; i < matrix[0].length; ++i) {
				System.out.print("---");
			}
			System.out.println();
			for (int i = 0; i < matrix.length; ++i) {
				for (int j = 0; j < matrix[i].length; ++j) {
					if (j == 0) {
						System.out.print(i + "|  ");
					}
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
			
		}
		
		
		public void promptKey() {
			
			Scanner input = new Scanner(System.in);
			boolean isValid = false;
			
			System.out.print("Please enter a binary key that is at most 64 bits (1 and 0 ONLY!!): ");
			
			do {				
				
				try {
					
					masterKey = input.nextLine(); // if the user enters a space, we must inform them instead of continuing to run with the values before the space. That is why we use nextLine instead of next
					
					boolean isBinary = true;

					if (masterKey.length() > KEY_LENGTH) {
						System.out.print("Error. Please enter a binary key that is AT MOST 64 bits: ");
						continue;
					}
					if (masterKey.length() == 0) {
						System.out.print("Error, you entered an empty key. Please enter a binary key that is at most 64 bits: ");
						continue;
					}
					
					
					for (int i = 0; i < masterKey.length(); ++i) {
						
						char bin = masterKey.charAt(i);
						
						if (bin != '1' && bin != '0') {
							
							isBinary = false;
							System.out.print("Error. Please enter a BINARY key that is at most 64 bits: ");
							break;
						}
						
					}
					
					isValid = isBinary;
					
				}
				
				catch (NumberFormatException e) {
					System.out.print("Error. Please enter a binary key that is at most 64 bits (1 and 0 ONLY!!): ");
				}
					
			} while (!isValid);
			
			while(masterKey.length() < KEY_LENGTH) {
				masterKey = "0" + masterKey;
			}
				
			
			
		}
		
		
		
		public void promptPlaintext() {
			
			Scanner input = new Scanner(System.in);
			boolean isValid = false;
			final int blockLength = 64;
			
			System.out.print("Please enter a binary plaintext that is at most 64 bits (1 and 0 ONLY!!): ");
			
			do {				
				
				try {
					
					plaintext = input.nextLine(); // if the user enters a space, we must inform them instead of continuing to run with the values before the space. That is why we use nextLine instead of next
					
					boolean isBinary = true;

					if (plaintext.length() > blockLength) {
						System.out.print("Error. Please enter a binary plaintext that is AT MOST 64 bits: ");
						continue;
					}
					if (plaintext.length() == 0) {
						System.out.print("Error, you entered an empty plaintext. Please enter a binary plaintext that is at most 64 bits: ");
						continue;
					}
					
					for (int i = 0; i < plaintext.length(); ++i) {
						
						char bin = plaintext.charAt(i);
						
						if (bin != '1' && bin != '0') {
							
							isBinary = false;
							System.out.print("Error. Please enter a BINARY plaintext that is at most 64 bits: ");
							break;
						}
						
					}
					
					isValid = isBinary;
					
				}
				
				catch (NumberFormatException e) {
					System.out.print("Error. Please enter a binary plaintext that is at most 64 bits (1 and 0 ONLY!!): ");
				}
					
			} while (!isValid);
			
			while(plaintext.length() < blockLength) {
				plaintext = "0" + plaintext;
			}
			
		}
		
		/*
		public static void main(String[] args) {
			
			DESEncrypter des = new DESEncrypter();
			
			//des.promptPlaintext();
			 
			//des.setPlaintext("0000000100100011010001010110011110001001101010111100110111101111");
			//des.setKey("0001001100110100010101110111100110011011101111001101111111110001");
			
			//des.encrypt();
			
			//des.promptKey();			
			//System.out.println(des.masterKey);
			
			des.plaintext = null;
			des.masterKey = "0001001100110100010101110111100110011011101111001101111111110001";
			
			des.encrypt();
			
			
		}
		*/
}
