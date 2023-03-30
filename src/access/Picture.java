package access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Picture {
	File name;
	String pictureName;
	String ownerName;
	List list;
	ArrayList<String> permissions;
	
	
	public Picture(String pictureName, String ownerName, List list) throws FileNotFoundException {
				
		this.name = new File(pictureName + ".txt");
		PrintStream pictureOut = new PrintStream(new FileOutputStream(pictureName));

		this.pictureName = pictureName;
		this.ownerName = ownerName;
		this.list = list;
		this.permissions = new ArrayList<String>();
		this.permissions.add("rw");
		this.permissions.add("--");
		this.permissions.add("--");
	}

}


