import java.util.*;

public class VigenereTest {
	public static void main(String[] args) {
		vigenereProc();
	}
	public static void vigenereProc() {
		Scanner scanner = new Scanner(System.in);
		String quitAns = "";
		
		while (true) {
			System.out.println("Enter text to encrypt (or \"q\" to quit):");
			quitAns = scanner.nextLine();
			if (quitAns.equals("q")) {
				System.out.println("Exiting program...");
				break;
			}
			
			System.out.print("Enter keyword for encryption: ");
			String keyword = scanner.nextLine();
			
			String cipherText = vigenereEnc(quitAns, keyword);
			System.out.println("Cipher text: " + cipherText + "\n");
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
}
