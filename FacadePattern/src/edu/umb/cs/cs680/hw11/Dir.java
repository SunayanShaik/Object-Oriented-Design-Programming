package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Dir implements Command {

	FileSystem fileSystem;
	private String options;
	private String command;

	public Dir(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory root = fileSystem.getRoot();
		Directory current = fileSystem.getCurrent();
		ArrayList<FSElement> currDirChildren = fileSystem.getCurrent().getChildren();
		String type = null;
		Boolean childFoundFlag = false;

		if (options.isEmpty() || options.equals("..")) {
			childFoundFlag = true;
			for (FSElement child : currDirChildren) {
				type = getChildType(child);
				System.out.println(type + "\t" + child.getName() + "\t" + child.getSize() + "\t" + child.getOwner());
			}
		} else {
			for (FSElement currDirChild : currDirChildren) {
				type = getChildType(currDirChild);
				if (options.equals(currDirChild.getName())) {
					childFoundFlag = true;
					if (currDirChild instanceof File) {
						System.out.println(type + "\t" + currDirChild.getName() + "\t" + currDirChild.getSize() + "\t"
								+ currDirChild.getOwner());
					} else if (currDirChild instanceof Directory) {
						System.out.println(type + "\t" + currDirChild.getName() + "\t" + currDirChild.getSize() + "\t"
								+ currDirChild.getOwner());
					} else {
						System.out.println(currDirChild.getName() + ": dir command disabled for Link");
						return;
					}
				}
			}

		}
		if (!childFoundFlag) {
			System.out.println(options + ": " + "No such file or directory");
		}
	}

	private String getChildType(FSElement child) {
		String type = "";
		if (child instanceof File) {
			type = "File";
		} else if (child instanceof Directory) {
			type = "Dir";
		} else {
			type = "Link";
		}
		return type;
	}

	public String getCommandName() {
		return command;
	}
}
