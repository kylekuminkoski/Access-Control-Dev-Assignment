package access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class access {
	static File instructions;
	static PrintStream auditOut;
	static PrintStream consoleOut;
	static PrintStream friendsOut;
	static PrintStream listsOut;
	static PrintStream picturesOut;
	
	public static void main(String[] args) {
		//Console Print Stream
		consoleOut = System.out;
		
		//Create nil list
		List nil = new List();
		
		//Create Audit Logs
		File audit = new File("audit.txt");
		auditOut = createLog(audit);
		
		//Create Friends Log
		File friends = new File("friends.txt");
		friendsOut = createLog(friends);
		
		//Create Lists Log
		File lists = new File ("lists.txt");
		listsOut = createLog(friends);
		
		//Create Pictures Log
		File pictures = new File ("pictures.txt");
		listsOut = createLog(pictures);		

		//Read in instructions from arguments. If argument is not given, throws error.
		if(args.length != 0) {
		String inputFile = args[0];
		instructions = new File(inputFile);
		}else {
			instructions = new File("myTestCase.txt");
		}
		
		
		
		//check if first line of file is 'friendadd', if yes move on call function and get next line, if no output   error
		Scanner reader = null;
		try {
			reader = new Scanner(instructions);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		//while instruction is not 'end'
		
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			StringTokenizer tokens = new StringTokenizer(line);
			while(tokens.hasMoreTokens()) {
				String token = tokens.nextToken();
				consoleOut.println(token);
				auditOut.println(token);
			}
			
		}
		
		
		
		//get next instruction
		

		reader.close();
	}
	
	
	//Helper method designed to simplify the file creation, overwrite, and output for each log.
	//Method will try
	public static PrintStream createLog(File name) {
		PrintStream print;
		try {
			name.delete();
			name.createNewFile();
			print = new PrintStream(new FileOutputStream(name));
			return print;
		} catch (IOException e) {
			consoleOut.println("Failed to create \"" + name.getName() + "\" file. Exiting...");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void friendadd(String friendname) {
		
		
		
	}

}
