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
	static File instructions, audit, friends, lists, pictures;
	static PrintStream consoleOut, auditOut, friendsOut, listsOut, picturesOut;

	public static void main(String[] args) throws Exception {
		boolean ownerExists = false;
		
		//Console Print Stream
		consoleOut = System.out;
		
		//Create nil list
		List nil = new List();
		
		//Create Audit Logs
		audit = new File("audit.txt");
		auditOut = createLog(audit);
		
		//Create Friends Log
		friends = new File("friends.txt");
		friendsOut = createLog(friends);
		
		//Create Lists Log
		lists = new File ("lists.txt");
		listsOut = createLog(friends);
		
		//Create Pictures Log
		pictures = new File ("pictures.txt");
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
					friendAdd(tokens.nextToken());
				}
				
				
			}
			
		}

		reader.close();
	}
	
	
	//Helper method designed to simplify the file creation, overwrite, and output for each log.
	//Method will delete existing log file and create a new text file with the given file name.
	//Arguments: log file name of File type Object
	//Returns: PrintStream for log file
	private static PrintStream createLog(File name) {
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
	
	//Helper method to determine if a friend name already exists in friends.txt
	//Arguments: The friend name being checked. String type variable
	//Returns: true if the friend exists, false if not
	private static boolean friendExists(String friendName) throws FileNotFoundException {
		boolean exists = false;
		Scanner reader = new Scanner(friends);
		
		while(reader.hasNext()) {
			if(reader.next().equals(friendName)) {
				exists = true;
			}		
		}
		reader.close();
		return exists;
	}
	
	private static void friendAdd(String friendName) throws FileNotFoundException {
		
		if(friendExists(friendName)) {
			consoleOut.println("Error: friend " + friendName + " already exists");
			auditOut.println("Error: friend " + friendName + " already exists");
			return;
		}

		friendsOut.println(friendName);
		consoleOut.println("Friend " + friendName + " added");
		auditOut.println("Friend " + friendName + " added");
	}
	
	private static void viewBy(String friendName) {
		
	}

}
