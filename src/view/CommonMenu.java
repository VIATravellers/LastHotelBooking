package view;

import java.io.Serializable;


public class CommonMenu implements ICommonMenu,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void printOut(Object obj) {
		System.out.println(obj);
		
	}

	

	
}
