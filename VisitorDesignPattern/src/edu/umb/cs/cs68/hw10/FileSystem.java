package edu.umb.cs.cs68.hw10;

import java.util.ArrayList;

public class FileSystem {
	
	private Directory root;

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
		print(this.root,""); 
	}
	
	private void print(Directory dir, String prefix) {
		System.out.println(prefix + "├── " + dir.getName() + "[" + dir.getSize() + "]");
		prefix = prefix + "    ";
		ArrayList<FSElement> children = dir.getChildren();
		for(FSElement child : children) {
			if (child instanceof Directory) {
				print((Directory)child, prefix);
			}
			
			if (child instanceof Link) {
				System.out.println(prefix + "├── " + child.getName() + " ---> " + ((Link)child).getTargetFullPath() + " [" + ((Link)child).getTargetSize() + "]");
			}
			
			if (child instanceof File) {
				System.out.println(prefix + "├── " + child.getName() + "[" + child.getSize() + "] ");
			}
		}
	}
}

