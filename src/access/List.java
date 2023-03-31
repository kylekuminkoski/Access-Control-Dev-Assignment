package access;

import java.util.ArrayList;

public class List {
	String name;
	ArrayList<String> friends;
	
	public List() {
		this.name = "nil";
		this.friends = new ArrayList<String>();
	}
	
	public List(String name) {
		this.name = name;
		this.friends = new ArrayList<String>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newListName) {
		this.name = newListName;
	}
	
	public ArrayList getList() {
		return this.friends;
	}
	
	public void setList(ArrayList list) {
		this.friends = list;
	}
	


}
