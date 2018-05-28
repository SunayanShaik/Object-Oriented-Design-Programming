package edu.umb.cs.cs680.hw09;

import java.util.ArrayList;
import java.util.Date;

public class Directory extends FSElement{
	private FSElement parent;
	private ArrayList<FSElement> children = new ArrayList<FSElement>();
	
	public Directory(Directory parent, String name, String owner, Date created, Date lastModified, int size, boolean isFile) {
		super(parent, name, owner, created, lastModified, size, isFile);
	}
	
	public ArrayList<FSElement> getChildren() {
		return this.children;
	}
	
	public void appendChild(FSElement fsElement) {
		children.add(fsElement);
	}
	
}
