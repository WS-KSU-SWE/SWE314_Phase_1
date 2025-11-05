Author: Rayan Aloraydi
Reviewer: Wael Alanazi
SWE314 Project - Code Structure Overview
============================================

Package Structure
-----------------
swe314_project_phase1/

├─ EncrypterDecrypter.java           (abstract base class for encryption/decryption)
├─ ShiftEncryptDecrypt.java          (Shift / Caesar cipher implementation)
├─ PlayfairEncrypter.java            (Playfair cipher implementation)
├─ VigenereEncrypter.java            (Vigenère cipher implementation)
├─ KeyedTranspositionEncryptDecrypt.java (Keyed Transposition cipher implementation)
├─ DESEncrypter.java                 (DES encryption/decryption implementation)
├─ CryptoUtils.java                  (helper utilities for cleaning text, conversions, padding)
├─ Validation.java                   (input validation and error handling)
└─ Main.java                         (entry point, algorithm selection menu)

Number of Classes
-----------------
Total: 8 classes
- 6 classes for algorithms (Shift, Playfair, Vigenere, Keyed Transposition, DES, optional Cryptanalysis)
- 2 helper classes (CryptoUtils, Validation)
- 1 main driver (Main.java)
- 1 abstract base (EncrypterDecrypter)

Class Descriptions
------------------
1. EncrypterDecrypter.java
   - Abstract class that defines shared attributes and methods.
   - Fields: plaintext, ciphertext
   - Methods: encrypt(), decrypt(), getters

2. VigenereEncrypter.java
   - Extends EncrypterDecrypter
   - Methods:
       promptKey()            → reads and validates key
       encrypt()              → encrypts normally
       encryptWithSteps()     → shows detailed step-by-step process
       decrypt()              → optional inverse

3. ShiftEncryptDecrypt.java
   - Methods:
       encrypt(int key)       → (p + k) mod 26
       decrypt(int key)       → (c - k) mod 26
       explainSteps(int key)  → optional educational output

4. PlayfairEncrypter.java
   - Builds 5x5 key square, processes digraphs
   - Methods: buildMatrix(), encrypt(), decrypt()

5. KeyedTranspositionEncryptDecrypt.java
   - Methods:
       encrypt()              → block-based permutation
       decrypt()              → inverse column reordering
       padToBlock()

6. DESEncrypter.java
   - Methods:
       encryptBlock(byte[] msg, byte[] key)
       decryptBlock(byte[] msg, byte[] key)
       KeySchedule(), InitialPermutation(), FinalPermutation()
   - Handles binary/hex inputs

7. CryptoUtils.java
   - Static helper methods:
       cleanLettersLower(), padToMultiple(), lettersToNums(), numsToLetters(), binToHex()

8. Validation.java
   - Static validation functions:
       ensureLettersOnly(key)
       ensureNonEmptyClean(plaintext)
       ensureBinary64(input)

9. Main.java
   - Provides console menu for selecting algorithm.
   - Calls each encryption/decryption method accordingly.

Encryption & Decryption Overview
--------------------------------
Vigenère Cipher:
    encryptWithSteps() → cleans input → repeats key → numeric mod 26 → ciphertext (uppercase)
    decrypt()          → subtract mod 26 instead of add

Shift Cipher:
    encrypt()          → (p + k) mod 26
    decrypt()          → (c - k) mod 26

Playfair Cipher:
    encrypt()          → apply digraph rules (same row, column, rectangle)
    decrypt()          → reverse the same rules

Keyed Transposition:
    encrypt()          → fill columns by key order, output column-wise
    decrypt()          → reverse order and rebuild plaintext

DES:
    encryptBlock()     → apply IP → 16 Feistel rounds → FP
    decryptBlock()     → same but reverse subkey order
----------------------------------------
How to Run:
----------------------------------------
Simply download the code to Eclipse and press the run button. 



