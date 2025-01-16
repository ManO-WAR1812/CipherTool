/* NOTES:
 * Add try + catch for shift vals if user adds a string instead of an Int
 * 
 * 
 * 
 */

import java.util.*;

public class CeasarTest{
	public static void main(String [] args){
		caesarProc();
	}
	
	public static void caesarProc(){
		String quitAns = "";
		Scanner caesarChoice = new Scanner(System.in);
		
		while(true){ //Loop inspired by ChatGPT
			System.out.println("Add Text or Enter \"q\" to exit: ");
			quitAns = caesarChoice.nextLine();
			if(quitAns.equals("q")){
				System.out.println("Exiting Program...");
				break;
			}
			System.out.print("Add Shift Value: ");
			int shiftVal = caesarChoice.nextInt();
			caesarChoice.nextLine();
			
			String cipherText = caesarEnc(quitAns, shiftVal);
			System.out.print("Cipher text: " + cipherText + "\n");
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
