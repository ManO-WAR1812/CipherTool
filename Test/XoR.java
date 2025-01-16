/* References: 
 * https://www.geeksforgeeks.org/xor-cipher/
 * https://stackoverflow.com/questions/917163/convert-a-string-like-testing123-to-binary-in-java
 * 
 */


import java.util.*;

public class XoR {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Input the string and XOR key
		System.out.print("Enter a string to encrypt: ");
		String inputString = scanner.nextLine();
		System.out.print("Enter a single-character XOR key: ");
		char xorKey = scanner.next().charAt(0);
		// Encrypt the string
		String encryptedBinary = xorProc(inputString, xorKey);
		System.out.println("Encrypted Binary: " + encryptedBinary);
		// Decrypt the binary back to the original string
		String decryptedBinary = xorProc(inputString, xorKey);
		System.out.println("Decrypted Binary: " + decryptedBinary);
	}
	public static String xorProc(String inputString, char xorKey) {
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
}


