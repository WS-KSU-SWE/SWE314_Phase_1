Author: Rayan Aloraydi

Reviewer: Wael Alanazi

SWE314 Project - Code Structure Overview
============================================

Number of Classes
-----------------
Total: 12 classes + 1 interface

Package Structure
-----------------
swe314_project_phase1/

├─ EncrypterDecrypter.java                  (abstract base class for encryption/decryption)

├─ ShiftEncryptDecrypt.java                 (Shift / Caesar cipher implementation)

├─ PlayfairEncrypter.java                   (Playfair cipher implementation)

├─ VigenereEncrypter.java                   (Vigenère cipher implementation)

├─ KeyedTranspositionEncryptDecrypt.java    (Keyed Transposition cipher implementation)

├─ DESEncrypter.java                        (DES encryption/decryption implementation)

├─ Cell.java                                (Wrapper class for Playfair key to store the rows and columns)

├─ CryptanalysisDecrypter.java              (Decrypt using cryptanalysis)

├─ LetterFrequencyPair.java                 (Wrapper class for Crpytanalysis to store the letters and their frequency)

├─ LinkedStack.java                         (Custom stack implementation)

├─ Node.java                                (Node for LinkedStack)

├─ Stack.java                               (stack interface)

└─ Main.java                                (entry point, algorithm selection menu)


How to Run:
----------------------------------------
Simply download the code to Eclipse and press the run button. 



