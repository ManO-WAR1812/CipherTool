import java.util.*;
import java.io.*;

public class fileChoiceTest {
	public static void main(String[] args) {
		String c = ""; // User choice
		Scanner scan = new Scanner(System.in);
		while (true) { // Loop inspired by ChatGPT
			System.out.println("""
			 .---. .----. .----..---.    
			{_   _}| {_  { {__ {_   _}   
			  | |  | {__ .-._} } | |     
			  `-'  `----'`----'  `-'     
			""");
			System.out.println("1. Caesar Cipher\n2. Vigenere Cipher\n3. XoR Cipher\n4. Enigma Cipher\n");
			System.out.println("Choose Program or Enter \"q\" to exit: ");
			c = scan.nextLine();
			if (c.equals("q")) {
				System.out.println("\n\n[Exiting Program...]\n\n\n\n\n");
				break;
			}

			if (c.equals("1")) {
				Scanner fileC = new Scanner(System.in);
				while (true) {
					System.out.println("Caesar Cipher: Would you like to input a file? (y/n)");
					String inC = fileC.nextLine();
					if (inC.equals("n")) {
						System.out.println("\n\n[Exiting Program...]\n\n\n\n\n");
						System.out.print("\033[H\033[2J");
						System.out.flush();
						break;
					} else if (inC.equals("y")) {
						System.out.println("Enter Filename: ");
						String fileName = fileC.nextLine();
						caesarProcFile(fileName);
						if (!promptFileAction(fileC)) {
							break; // Exit to main menu if the user chooses to
						}
					} else {
						System.out.println("Invalid choice. Please enter 'y' or 'n'.");
					}
				}
			}
		}
	}

	// Method to handle Caesar Cipher with file input
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
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	// Prompt user to select another file or return to the main menu
	public static boolean promptFileAction(Scanner scanner) {
		while (true) {
			System.out.println("Would you like to process another file? (y/n)");
			String nextAction = scanner.nextLine();
			if (nextAction.equalsIgnoreCase("n")) {
				System.out.println("\n\n[Exiting Program...]\n\n\n\n\n");
				System.out.print("\033[H\033[2J");
				System.out.flush();
				return false;
			} else if (nextAction.equalsIgnoreCase("n")) {
				return true; // Continue processing files
			} else {
				System.out.println("Invalid choice. Please enter 'y' or 'n'.");
			}
		}
	}

	// Method for user input Caesar Cipher
	public static void caesarProc() {
		String quitAns = "";
		Scanner caesarChoice = new Scanner(System.in);

		while (true) { // Loop inspired by ChatGPT
			System.out.println("Add Text or Enter \"q\" to go to Main Menu: ");
			quitAns = caesarChoice.nextLine();
			if (quitAns.equals("q")) {
				System.out.println("\n\n[Exiting Program...]\n\n\n\n\n");
				System.out.print("\033[H\033[2J");
				System.out.flush();
				break;
			}
			System.out.print("Add Shift Value: ");
			int shiftVal = caesarChoice.nextInt();
			caesarChoice.nextLine(); // Consume newline

			String cipherText = caesarEnc(quitAns, shiftVal);
			System.out.print("Cipher text: " + cipherText + "\n");
		}
	}

	// Caesar Cipher Encryption
	public static String caesarEnc(String input, int n) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String cipherText = "";

		for (int i = 0; i < input.length(); i++) { // Reads all chars in message
			char currentChar = input.charAt(i);
			// Check if the character is a letter
			if (Character.isLetter(currentChar)) {
				// Determine if it's uppercase or lowercase
				boolean isUpperCase = Character.isUpperCase(currentChar);

				// Normalize to lowercase for lookup
				char normalizedChar = Character.toLowerCase(currentChar);
				int charPosition = alphabet.indexOf(normalizedChar);
				int keyVal = (n + charPosition) % 26;
				char replaceVal = alphabet.charAt(keyVal);

				// Convert back to uppercase if needed
				if (isUpperCase) {
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
}
