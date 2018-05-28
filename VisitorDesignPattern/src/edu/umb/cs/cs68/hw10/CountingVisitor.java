package edu.umb.cs.cs68.hw10;

public class CountingVisitor implements FSVisitor {

	int dirNum = 0, fileNum = 0, linkNum = 0;
	
	public void visit(Link link) {
		linkNum++;
	}

	public void visit(Directory dir) {
		dirNum++;
	}

	public void visit(File file) {
		fileNum++;
	}

	public int getDirNum() {
		return dirNum;
	}

	public int getFileNum() {
		return fileNum;
	}

	public int getLinkNum() {
		return linkNum;
	}
	
}
