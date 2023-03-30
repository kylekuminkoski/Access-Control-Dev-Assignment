package access;

import java.io.File;
import java.util.ArrayList;

public class Picture {
	File name;
	String ownerName;
	List list;
	ArrayList<String> permissions;
	
	
	public Picture(File name, String ownerName, List list) {
		this.name = name;
		this.ownerName = ownerName;
		this.list = list;
		this.permissions = new ArrayList<String>();
		this.permissions.add("rw");
		this.permissions.add("--");
		this.permissions.add("--");
	}

}


