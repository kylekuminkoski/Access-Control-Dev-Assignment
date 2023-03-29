package access;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class access {

	public static void main(String[] args) {
		//Create nil list
		List nil = new List();

		File audit = new File("audit.txt");
		try {
			audit.delete();
			audit.createNewFile();
			PrintStream out = new PrintStream(new FileOutputStream(audit));
			System.setOut(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//initialize file
		String inputFile = args[0];
		File instructions = new File(inputFile);
		
		//check if first line of file is 'friendadd', if yes move on call function and get next line, if no output   error
		Scanner reader = null;
		try {
			reader = new Scanner(instructions);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		//while instruction is not 'end'
		
		
		
		
		//get next instruction
		

		reader.close();
	}
	
	public void friendadd(String friendname) {
		
		
		
	}

}
