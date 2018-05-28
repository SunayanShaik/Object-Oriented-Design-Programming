package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class File extends FSElement{

	private boolean hasVirus = false;
	
	public File(Directory parent, String name, String owner, Date created, Date lastModified, int size, boolean isFile) {
		super(parent, name, owner, created, lastModified, size, isFile);
	}
	
	public void accept(FSVisitor fsVisitor) {
		fsVisitor.visit(this);
	}

	public boolean isHasVirus() {
		return hasVirus;
	}

	public void setHasVirus(boolean hasVirus) {
		this.hasVirus = hasVirus;
	}
}
