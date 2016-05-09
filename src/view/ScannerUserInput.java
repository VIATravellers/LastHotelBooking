package view;

import java.io.Serializable;
import java.util.Scanner;

public class ScannerUserInput implements IScannerUserInput,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int scannerUserInputInt(int i) {
		Scanner scanner = new Scanner(System.in);
		i = scanner.nextInt();
		return i;
		
	}

	@Override
	public String scannerUserInputString(String s) {
		Scanner scanner = new Scanner(System.in);
		s = scanner.nextLine();
		return s;
	}

	@Override
	public char scannerUserInputChar(char c) {
		Scanner scanner = new Scanner(System.in);
		c = scanner.next().charAt(0);
		return c;
	}

	@Override
	public void scannerEmptyLine() {
		Scanner scanner  = new Scanner(System.in);
		scanner.nextLine();
		
	}

	public int scannerUserInputInt2(int percent) {
		Scanner scanner = new Scanner(System.in) ;
		percent =scanner.nextInt();
		return percent ;
	}

}
