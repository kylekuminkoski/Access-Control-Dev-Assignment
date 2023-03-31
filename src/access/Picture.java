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
	String listName;
	ArrayList<String> permissions;
	
	
	public Picture(String pictureName, String ownerName, List list) throws FileNotFoundException {
		this.pictureName = pictureName;
		this.ownerName = ownerName;
		this.list = list;
		this.listName = list.getName();
		this.permissions = new ArrayList<String>();
		this.permissions.add("rw");
		this.permissions.add("--");
		this.permissions.add("--");
	}
	
	public File getFile() {
		return this.name;
	}
	
	public void setFile(File name) {
		this.name = name;
	}
	
	public String getPictureName() {
		return this.pictureName;
	}
	
	public void setPictureName(String name) {
		this.pictureName = name;
	}
	
	public String getOwnerName() {
		return this.ownerName;
	}
	
	public void setOwnerName(String name) {
		this.ownerName = name;
	}
	
	public List getList() {
		return this.list;
	}
	
	public void setList(List newList) {
		this.list = newList;
	}
	
	public String getListName() {
		return this.listName;
	}
	
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	public String getPermissions(int index) {
		return this.permissions.get(index);
	}
	
	public void setPermissions(String perm, int index) {
		this.permissions.remove(index);
		this.permissions.add(index, perm);
	}

}


