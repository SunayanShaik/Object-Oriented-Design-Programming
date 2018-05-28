package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileSystem {
	
	private Directory root, current;

	private FileSystem(Directory root) {
		this.root = root;
	};

	private static FileSystem fileSystem = null;

	public static FileSystem getFileSystem(Directory root) {
		if (fileSystem == null)
			fileSystem = new FileSystem(root);
		return fileSystem;
	}

	public void showAllElements() {
		printTreeStructure(this.root,""); 
	}
	
	private void printTreeStructure(Directory dir, String prefix) {
		System.out.println(prefix + "├── " + dir.getName() + "[" + dir.getSize() + "]");
		prefix = prefix + "    ";
		ArrayList<FSElement> children = dir.getChildren();
		for(FSElement child : children) {
			if (child instanceof Directory) {
				printTreeStructure((Directory)child, prefix);
			}
			
			if (child instanceof Link) {
				System.out.println(prefix + "├── " + child.getName() + " ---> " + ((Link)child).getTargetFullPath() + " [" + ((Link)child).getTargetSize() + "]");
			}
			
			if (child instanceof File) {
				System.out.println(prefix + "├── " + child.getName() + "[" + child.getSize() + "] ");
			}
		}
	}
	
	public void setRoot(Directory root) {
		this.root = root;
	}

	public Directory getRoot() {
		return root;
	}

	public Directory getCurrent() {
		return current;
	}

	public void setCurrent(Directory current) {
		this.current = current;
	}
	
	public void addChild(Directory current, FSElement child) {
		current.appendChild(child);
	}
	
	public ArrayList<FSElement> getChildren(Directory current) {
		return current.getChildren();
	}
	
	public int getInsertionLocation(Directory parent, FSElement child) {
		return parent.getChildren().indexOf(child);
	}
	
	public ArrayList<FSElement> sort(Directory dir, Comparator<FSElement> comparator) {
		ArrayList<FSElement> dirChildren = dir.getChildren();
		Collections.sort(dirChildren, comparator);
		return dirChildren;
	}
	
	public void fileSearchVisit(Directory dir, String extension) {
		FileSearchVisitor fileSearchVisitor = new FileSearchVisitor(extension);
		dir.accept(fileSearchVisitor);
		System.out.println("Number of files found from current dir: " + fileSearchVisitor.getFoundFiles().size());
	}
	
	public void countVisit(Directory dir) {
		CountingVisitor countVisitor = new CountingVisitor();
		dir.accept(countVisitor);
		System.out.println("Number of Directories from current dir: " + countVisitor.getDirNum());
		System.out.println("Number of Files from current dir: " + countVisitor.getFileNum());
		System.out.println("Number of Links from current dir: " + countVisitor.getLinkNum());
	}
	
	public void virusCheckVisit(Directory dir) {
		VirusCheckingVisitor virusVisitor = new VirusCheckingVisitor();
		dir.accept(virusVisitor);
		System.out.println("Number of files quarantined from current dir: " +virusVisitor.getQuarantinedNum());
	}
	
}

