package view;

import java.io.Serializable;

public class ExitProgramMenu implements IExitProgram ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void exitMenu() {
		System.out.println("You have end the program");
		
	}
	
	
	
}
