package edu.umb.cs.cs68.hw10;

import java.util.Date;

public class Link extends FSElement {
	
	FSElement target;
	
	public Link(Directory parent, FSElement target,String name, String owner, Date created, Date lastModified, int size, boolean isFile) {
		super(parent, name, owner, created, lastModified, size, isFile);
		this.target = target;
	}
	
	public int getTargetSize() {
		return target.getSize();
	}
	
	public String getTargetName() {
		return target.getName();
	}
	
	public String getTargetFullPath() {
		return target.getFullPath();
	}
	
	public void accept(FSVisitor fsVisitor) {
		fsVisitor.visit(this);
	}
}
