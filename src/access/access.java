package access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class access{
	static File instructions, audit, friends, lists, pictures;
	static PrintStream consoleOut, auditOut, friendsOut, listsOut, picturesOut;
	private static String userSession;
	private static boolean ownerExists;
	private static boolean ownerView;
	private static boolean friendView;
	private static Session user;

	public static void main(String[] args) throws Exception {
		ownerExists = false; // used to check if the profile owner was already created
		ownerView = false; // used to check if the profile owner is viewing the profile
		friendView = false; // used to check if a friend is already viewing the profile
		//Console Print Stream
		consoleOut = System.out;
		
		// Create nil list
		List nil = new List();
		
		// Create null Session
		user = new Session();
		
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
		
		if(reader.hasNextLine()) {
			String first = reader.nextLine();
			StringTokenizer firstToken = new StringTokenizer(first);
			
			//This conditional block is used to check if the profile owner is created yet
			//If no, it set's the ownerName and allows the owner to create the profile
			//View is then set back to false before any more instructions are executed
			if(!user.isSessionCreated()) {
				if(!firstToken.nextToken().equals("friendadd")) {
					consoleOut.println("Error: a profile owner must be created");
					auditOut.println("Error: a profile owner must be created");
					return;
				}
				
				String ownerName = firstToken.nextToken();
				user.setOwner(ownerName);
				user.setOwnerViewing(true);
				friendAdd(ownerName);
				user.setOwnerViewing(false);
			}
			
			
		}
		
		
		//while instruction is not 'end'
		
		while(reader.hasNextLine()) { //Iterates through each line in instruction file
			String line = reader.nextLine();
			
			if(line.equals("end")) // Ends instruction execution when end is given
				break;
			
			StringTokenizer tokens = new StringTokenizer(line);
			while(tokens.hasMoreTokens()) { //Iterates through each token in a line
				String token = tokens.nextToken();
				
				switch (token) 
				{
				case "friendadd":
					friendAdd(tokens.nextToken());
					break;
				
				case "viewby":
					viewBy(tokens.nextToken());
					break;
					
				case "logout":
					consoleOut.println("Friend " + user.getUserName() + " logged out");
					auditOut.println("Friend " + user.getUserName() + " logged out");
					user = user.logout();
					break;
					
				
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
		if(!user.getOwnerViewing()) {
			consoleOut.println("Error: only profile owner may issue friendadd command");
			auditOut.println("Error: only profile owner may issue friendadd command");
		}
		
		
		if(friendExists(friendName)) {
			consoleOut.println("Error: friend " + friendName + " already exists");
			auditOut.println("Error: friend " + friendName + " already exists");
			return;
		}

		friendsOut.println(friendName);
		consoleOut.println("Friend " + friendName + " added");
		auditOut.println("Friend " + friendName + " added");
	}
	
	private static void viewBy(String friendName) throws FileNotFoundException {
		
		if(!friendExists(friendName)) {
			consoleOut.println("Error: friend " + friendName + " does not exist");
			auditOut.println("Error: friend " + friendName + " does not exist");
			return;
		}
		
		if(user.getUserName() != null) {
			consoleOut.println("Error: concurrent users are not supported");
			auditOut.println("Error: concurrent users are not supported");
			return;
		}
		
		user.setUserName(friendName);
		
		if(user.getUserName().equals(user.getOwner()))
			user.setOwnerViewing(true);
		
		consoleOut.println("Friend " + friendName + " views the profile");
		auditOut.println("Friend " + friendName + " views the profile");
		
	}
	


}
