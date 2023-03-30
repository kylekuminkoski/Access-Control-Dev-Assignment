package access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
	
	public static void main(String[] args) throws Exception {
		boolean ownerExists = false;
		
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
		
		while(reader.hasNextLine()) { //Iterates through each line in instruction file
			String line = reader.nextLine();
			
			if(line.equals("end")) // Ends instruction execution when end is given
				break;
			
			StringTokenizer tokens = new StringTokenizer(line);
			while(tokens.hasMoreTokens()) { //Iterates through each token in a line
				String token = tokens.nextToken();
				
				if(!ownerExists && token.equals("friendadd")) {
					ownerExists = true;
					friendadd(tokens.nextToken(), friends);
				}
				
				
			}
			
		}

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
	
	public static void friendadd(String friendName, File friendslist) throws Exception {
		Scanner reader = new Scanner(friendslist);
		
		while(reader.hasNext()) {
			if(reader.next().equals(friendName)) {
				consoleOut.println("Error: friend " + friendName + " already exists");
				auditOut.println("Error: friend " + friendName + " already exists");
				reader.close();
				return;
			}		
		}
		reader.close();
		friendsOut.println(friendName);
		consoleOut.println("Friend " + friendName + " added");
		auditOut.println("Friend " + friendName + " added");
	}

}
