package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Rmdir implements Command {

	FileSystem fileSystem;
	private String options;
	private String command;

	public Rmdir(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory current = fileSystem.getCurrent();
		ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
		Boolean childFoundFlag = false;

		if (options.isEmpty()) {
			System.out.println("rmdir: missing operand" + " Please provide a valid dir name to remove directory");
			return;
		}
		FSElement childToRemove = null;
		for (FSElement child : children) {
			if (options.equals(child.getName())) {
				childFoundFlag = true;
				if (child instanceof Directory) {
					childToRemove = child;
					break;
				} else {
					System.out.println(child.getName() + ": Not a directory");
					return;
				}
			}
		}
		if (childToRemove != null) {
			current.removeChild(childToRemove);
		}
		if (!childFoundFlag) {
			System.out.println(options + ": " + "No such file or directory");
		}

	}

	public String getCommandName() {
		return command;
	}
}
