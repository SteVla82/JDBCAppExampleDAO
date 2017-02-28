package gr.haec.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;


public class Library {

	public static int randomInteger() {
		return new SecureRandom().nextInt();
	}

	public static String readString() {

		try {
			return new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			return "";
		}
	}

	public static char readChar() {

		return readString().charAt(0);
	}

	public static int readInt() {

		try {
			return Integer.parseInt(readString());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
}
