/* References:
 * https://chatgpt.com/c/67339582-4e74-8012-bae8-4639f705a681
 * 
 * 
 */

import java.util.*;

public class Enigma {
	public static void main(String[] args) {
		enigmaProc();
	}
	
	// Enigma Cipher
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

    System.out.println("Encrypted Text: " + encryptedText.toString());
}

}
