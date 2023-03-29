package access;

import java.util.ArrayList;

public class Picture {
	String name;
	String ownerName;
	List list;
	ArrayList<String> permissions;
	
	
	public Picture(String name, String ownerName,List list) {
		this.name = name;
		this.ownerName = name;
		this.list = list;
		this.permissions = new ArrayList<String>();
		this.permissions.add("rw");
		this.permissions.add("--");
		this.permissions.add("--");
	}

}


