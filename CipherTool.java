//Author: John Vincent Postrano

/* References:
 * https://www.topcoder.com/thrive/articles/caesar-cipher-in-java-encryption-and-decryption --> Caesar Cipher Equation
 * https://chatgpt.com/c/67339582-4e74-8012-bae8-4639f705a681 --> Enigma cipher
 * https://www.geeksforgeeks.org/xor-cipher/ --> XoR Cipher
 * https://stackoverflow.com/questions/917163/convert-a-string-like-testing123-to-binary-in-java --> XoR Cipher
 *
 */
 
/*Todays Notes (To-Do)
 * Remember to add a file choice 
 * 
 */
 
import java.util.*;
import java.io.*; 


public class CipherTool{
	
	public static void main(String[] args){
		mainScreen();
	}
	//Main Screen Method
	public static void mainScreen(){
		
		String c = ""; //c = Choice of user
		Scanner scan = new Scanner(System.in);
		while(true){ //Loop inspired by ChatGPT
			displayTitle();
			System.out.println("@Author: John Vincent Postrano");
			System.out.println("1. Caesar Cipher\n2. Vigenere Cipher\n3. XoR Cipher\n4. Enigma Cipher\n");
			System.out.println("Choose Program or Enter \"q\" to exit: ");
			c = scan.nextLine();
			if(c.equals("q")){
				System.out.println("\n\n[Exiting Program...]\n\n\n\n\n");
				break;
			}
			if(c.equals("1") || c.equals("Caesar Cipher")){
				Scanner fileC = new Scanner(System.in);
				while (true) {
					System.out.println("Caesar Cipher: Would you like to input a file? (y/n/menu)");
					String inC = fileC.nextLine();
					if (inC.equals("n")) {
						caesarProc();
					} else if (inC.equals("y")) {
						System.out.println("Enter Filename: ");
						String fileName = fileC.nextLine();
						caesarProcFile(fileName);
					} else if(inC.equals("menu")){
						System.out.print("\033[H\033[2J");
						System.out.flush();
						break;
					} else {
						System.out.println("Invalid choice. Please enter 'y' or 'n'.");
					}
				}
			} else if(c.equals("2") || c.equals("Vigenere Cipher")){
				Scanner fileC = new Scanner(System.in);
				while (true) {
					System.out.println("Vigenere Cipher: Would you like to input a file? (y/n/menu)");
					String inC = fileC.nextLine();
					if (inC.equals("n")) {
						vigenereProc();
					} else if (inC.equals("y")) {
						System.out.println("Enter Filename: ");
						String fileName = fileC.nextLine();
						vigenereProcFile(fileName);
					} else if(inC.equals("menu")){
						System.out.print("\033[H\033[2J");
						System.out.flush();
						break;
					} else {
						System.out.println("Invalid choice. Please enter 'y' or 'n'.");
					}
				}
			} else if(c.equals("3") || c.equals("XoR Cipher")){
				Scanner fileC = new Scanner(System.in);
				while (true) {
					System.out.println("XoR Cipher: Would you like to input a file? (y/n/menu)");
					String inC = fileC.nextLine();
					if (inC.equals("n")) {
						xorProc();
					} else if (inC.equals("y")) {
						System.out.println("Enter Filename: ");
						String fileName = fileC.nextLine();
						xorProcFile(fileName);
					} else if(inC.equals("menu")){
						System.out.println("\n\n[Exiting Program...]\n\n\n\n\n");
						System.out.print("\033[H\033[2J");
						System.out.flush();
						break;
					} else {
						System.out.println("Invalid choice. Please enter 'y' or 'n'.");
						}
				}
			} else if(c.equals("4") || c.equals("Enigma Cipher")){
				while (true) {
					System.out.println("Enigma Cipher: Would you like to input a file? (y/n/menu)");
					Scanner fileC = new Scanner(System.in);
					String inC = fileC.nextLine();
					if (inC.equals("n")) {
						enigmaProc();
					} else if (inC.equals("y")) {
						System.out.println("Enter Filename: ");
						String fileName = fileC.nextLine();
						enigmaProcFile(fileName);
					} else if(inC.equals("menu")){
						System.out.print("\033[H\033[2J");
						System.out.flush();
						break;
					} else {
						System.out.println("Invalid choice. Please enter 'y' or 'n'.");
					}
				}
			}
		}
	}
	
	//Caesar Cipher --START--
	public static void caesarProc(){
		String quitAns = "";
		Scanner caesarChoice = new Scanner(System.in);
		
		try{
			System.out.println("\n\nAdd Text: ");
			quitAns = caesarChoice.nextLine();
			System.out.print("Add Shift Value: ");
			int shiftVal = caesarChoice.nextInt();
			caesarChoice.nextLine();
			
			String cipherText = caesarEnc(quitAns, shiftVal);
			System.out.print("Cipher text: " + cipherText + "\n\n");
		} catch(IllegalArgumentException e) {
			System.out.println("ERROR: Illegal Arguement\n\n");
		} catch(InputMismatchException e){
			System.out.println("ERROR: Shift Value Only Accepts Ints\n\n");
		} catch(Exception e){
			System.out.println("ERROR");
		}
		
	}
	
	public static String caesarEnc(String input, int n) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String cipherText = "";
		
		for (int i = 0; i < input.length(); i++) { //Reads all chars in message
			char currentChar = input.charAt(i);
			// Check if the character is a letter
			if (Character.isLetter(currentChar)) {
				// Determine if it's uppercase or lowercase
				boolean isUpperCase = Character.isUpperCase(currentChar);
				
				// Convert to lowercase
				char normalizedChar = Character.toLowerCase(currentChar);
				int charPosition = alphabet.indexOf(normalizedChar);
				int keyVal = (n + charPosition) % 26;
				char replaceVal = alphabet.charAt(keyVal);
				
				
				if (isUpperCase) {// Convert back to uppercase if needed
					replaceVal = Character.toUpperCase(replaceVal);
				}
				
				cipherText += replaceVal;
			} else {
				// Non-alphabetic characters remain unchanged
				cipherText += currentChar;
			}
		}
		System.out.print("Original text: " + input + "\n");
		return cipherText;
	}
	
	
	public static void caesarProcFile(String fileName) {
		try {
			// Reading the file
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			String fileContent = ""; // String to hold file content

			while (fileScanner.hasNextLine()) {
				fileContent += fileScanner.nextLine() + "\n"; // Concatenate lines with a newline
			}
			fileScanner.close();

			// File content read
			System.out.println("File Content:\n" + fileContent);

			// Ask the user for shift value
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Shift Value: ");
			int shiftValue = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			// Encrypt the file content
			String cipherText = caesarEnc(fileContent, shiftValue);

			// Output the encrypted text
			System.out.println("Encrypted Text:\n" + cipherText);

		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found. Please check the file name and try again.");
		} catch (IllegalArgumentException e){
			System.out.println("ERROR: Shift Value is Int only");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	
	//Caesar Cipher --END--
	
	//Vigenere Cipher --START--
	public static void vigenereProc() {
		Scanner scanner = new Scanner(System.in);
		String quitAns = "";
		try{
			System.out.println("Enter text to encrypt (or \"q\" to quit):");
			quitAns = scanner.nextLine();
			
			
			System.out.print("Enter keyword for encryption: ");
			String keyword = scanner.nextLine();
			
			String cipherText = vigenereEnc(quitAns, keyword);
			System.out.println("Cipher text: " + cipherText + "\n");
		} catch(Exception e) {
			System.out.println("ERROR: Please Report Bug to Author\nEmail: johvicpo1720@gmail.com");
		}
	}
	
	public static String vigenereEnc(String input, String keyword) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder cipherText = new StringBuilder();
		int keywordIndex = 0; // Tracks position in the keyword
		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			if (Character.isLetter(currentChar)) {
				// Determine if the character is uppercase or lowercase
				boolean isUpperCase = Character.isUpperCase(currentChar);
				// Normalize currentChar to lowercase for indexing
				char normalizedChar = Character.toLowerCase(currentChar);
				// Get shift from the keyword
				char keywordChar = Character.toLowerCase(keyword.charAt(keywordIndex % keyword.length()));
				int shift = alphabet.indexOf(keywordChar);
				// Encrypt the character
				int charPosition = alphabet.indexOf(normalizedChar);
				int keyVal = (charPosition + shift) % 26;
				char replaceVal = alphabet.charAt(keyVal);
				// Convert back to uppercase if needed
				if (isUpperCase) {
					replaceVal = Character.toUpperCase(replaceVal);
				}
				cipherText.append(replaceVal);
				// Move to the next character in the keyword
				keywordIndex++;
			} else {
				// Non-alphabetic characters remain unchanged
				cipherText.append(currentChar);
			}
		}
		
		return cipherText.toString();
	}
	
	public static void vigenereProcFile(String fileName) {
		try {
			// Reading the file
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			String fileContent = ""; // String to hold file content

			while (fileScanner.hasNextLine()) {
				fileContent += fileScanner.nextLine() + "\n"; // Concatenate lines with a newline
			}
			fileScanner.close();

			// File content read
			System.out.println("File Content:\n" + fileContent);

			// Ask the user for shift value
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter keyword: ");
			String key = scanner.next();
			scanner.nextLine(); // Consume newline

			// Encrypt the file content
			String cipherText = vigenereEnc(fileContent, key);

			// Output the encrypted text
			System.out.println("Encrypted Text:\n" + cipherText);

		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found. Please check the file name and try again.");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	
	
	
	//Vigenere Cipher --END--
	
	//XoR Cipher --START--
	public static void xorProc() {
		try{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter a string to encrypt: ");
			// Input the string and XOR key
			String inputString = scanner.nextLine();
			System.out.print("Enter a single-character XOR key: ");
			
			char xorKey = scanner.next().charAt(0); //Will ignore other characters
			
			// Encrypt the string
			String encryptedBinary = xorEnc(inputString, xorKey);
			System.out.println("Encrypted Binary: " + encryptedBinary);
			// Decrypt the binary back to the original string
			String decryptedBinary = xorEnc(inputString, xorKey);
			System.out.println("Decrypted Binary: " + decryptedBinary);
		} catch(IllegalArgumentException e) {
			System.out.println("ERROR: Add only one char for XOR key");
		} catch(Exception e){
			System.out.println("ERROR: Please report bug to Author\nEmail:johvicpo1720@gmail.com");
		}
	}
	public static String xorEnc(String inputString, char xorKey) {
		byte[] bytes = inputString.getBytes();
		StringBuilder binary = new StringBuilder();
		// Convert each character to binary and XOR with the key
		for (byte b : bytes) {
			int val = b ^ xorKey; // XOR operation
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binary.append(' ');
		}
		return binary.toString();
	}
	
	public static void xorProcFile(String fileName) {
		try {
			// Reading the file
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			String fileContent = ""; // String to hold file content

			while (fileScanner.hasNextLine()) {
				fileContent += fileScanner.nextLine() + "\n"; // Concatenate lines with a newline
			}
			fileScanner.close();

			// File content read
			System.out.println("File Content:\n" + fileContent);

			// Ask the user for shift value
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter key (One Character): ");
			String key = scanner.next();
			if(key.length() > 1){
				while(key.length() > 1){
				System.out.print("Wrong Input; Add one character only");
				System.out.print("Enter key (One Character): ");
				key = scanner.next();
				}
			}
			
			scanner.nextLine(); // Consume newline

			// Encrypt the file content
			String cipherText = vigenereEnc(fileContent, key);

			// Output the encrypted text
			System.out.println("Encrypted Text:\n" + cipherText);

		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found. Please check the file name and try again.");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	
	
	//XoR Cipher --END--
	
	//Enigma Cipher --START--
	
	public static void enigmaProc() {
		Scanner scanner = new Scanner(System.in);

		// Input plaintext and rotor settings
		System.out.print("Enter the text to encrypt: ");
		String plaintext = scanner.nextLine().toUpperCase();
		System.out.print("Enter the initial rotor positions (3 letters): ");
		String rotorPositions = scanner.nextLine().toUpperCase();
		// Validate rotor positions
		if (rotorPositions.length() != 3 || !rotorPositions.matches("[A-Z]{3}")) {
			System.out.println("Invalid rotor positions. Please enter exactly 3 uppercase letters.");
			return;
		}
		// Encrypt using the enigmaEnc method
		String encryptedText = enigmaEnc(plaintext, rotorPositions);
		System.out.println("Encrypted Text: " + encryptedText);
	}
	
	public static String enigmaEnc(String plaintext, String rotorPositions) {
	// Define rotors and reflector
	String rotor1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
	String rotor2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
	String rotor3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
	String reflector = "YRUHQSLDPXNGOKMIEBFZCWVJAT";

	// Get initial rotor positions
	int position1 = rotorPositions.charAt(0) - 'A';
	int position2 = rotorPositions.charAt(1) - 'A';
	int position3 = rotorPositions.charAt(2) - 'A';

	StringBuilder encryptedText = new StringBuilder();

	// Encrypt each character
	for (char c : plaintext.toCharArray()) {
		// Ignore non-alphabetic characters
		if (!Character.isLetter(c)) {
			encryptedText.append(c);
			continue;
		}

		// Normalize to uppercase
		c = Character.toUpperCase(c);

		// Pass through the first rotor
		int index = (c - 'A' + position1) % 26;
		c = rotor1.charAt(index);

		// Pass through the second rotor
		index = (c - 'A' + position2) % 26;
		c = rotor2.charAt(index);

		// Pass through the third rotor
		index = (c - 'A' + position3) % 26;
		c = rotor3.charAt(index);

		// Reflect the character
		index = reflector.indexOf(c);
		c = (char) ('A' + index);

		// Pass back through the third rotor
		index = (rotor3.indexOf(c) - position3 + 26) % 26;
		c = (char) ('A' + index);

		// Pass back through the second rotor
		index = (rotor2.indexOf(c) - position2 + 26) % 26;
		c = (char) ('A' + index);

		// Pass back through the first rotor
		index = (rotor1.indexOf(c) - position1 + 26) % 26;
		c = (char) ('A' + index);

		// Append the encrypted character
		encryptedText.append(c);

		// Rotate the first rotor
		position1 = (position1 + 1) % 26;

		// Rotate the second rotor after every full rotation of the first
		if (position1 == 0) {
			position2 = (position2 + 1) % 26;

			// Rotate the third rotor after every full rotation of the second
			if (position2 == 0) {
				position3 = (position3 + 1) % 26;
			}
		}
	}

	return encryptedText.toString();
}
	
	public static void enigmaProcFile(String fileName) {
			try {
				// Reading the file
				File file = new File(fileName);
				Scanner fileScanner = new Scanner(file);
				String fileContent = ""; // String to hold file content

				while (fileScanner.hasNextLine()) {
					fileContent += fileScanner.nextLine() + "\n"; // Concatenate lines with a newline
				}
				fileScanner.close();

				// File content read
				System.out.println("File Content:\n" + fileContent);

				// Ask the user for shift value
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter the initial rotor positions (3 Letters): ");
				String rotors = scanner.next();
				if(rotors.length() != 3){
					while(rotors.length() != 3){
					System.out.print("Wrong Input; Add 3 letters only");
					System.out.print("Enter the initial rotor positions (3 Letters): ");
					rotors = scanner.next();
					}
				}
				
				scanner.nextLine(); // Consume newline

				// Encrypt the file content
				String cipherText = enigmaEnc(fileContent, rotors);

				// Output the encrypted text
				System.out.println("Encrypted Text:\n" + cipherText);

			} catch (FileNotFoundException e) {
				System.out.println("Error: File not found. Please check the file name and try again.");
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}
		

	//Enigma Cipher --END--
	
	public static void displayTitle(){
		System.out.println("""
 ▗▄▄▖▗▄▄▄▖▗▄▄▖ ▗▖ ▗▖▗▄▄▄▖▗▄▄▖▗▄▄▄▖▗▄▖  ▗▄▖ ▗▖   
▐▌     █  ▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌ ▐▌ █ ▐▌ ▐▌▐▌ ▐▌▐▌   
▐▌     █  ▐▛▀▘ ▐▛▀▜▌▐▛▀▀▘▐▛▀▚▖ █ ▐▌ ▐▌▐▌ ▐▌▐▌   
▝▚▄▄▖▗▄█▄▖▐▌   ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌ █ ▝▚▄▞▘▝▚▄▞▘▐▙▄▄▖

			""");
	}
}
