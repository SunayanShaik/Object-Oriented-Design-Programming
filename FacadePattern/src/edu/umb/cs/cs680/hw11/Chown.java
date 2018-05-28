package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Chown implements Command {

	FileSystem fileSystem;
	private String options;
	private String command;

	public Chown(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory current = fileSystem.getCurrent();
		ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
		Boolean childFoundFlag = false;

		if (options.isEmpty()) {
			for (FSElement child : children) {
				childFoundFlag = true;
				if (!(child instanceof Link)) {
					child.setOwner("suny");
				}
			}
			return;
		}

		for (FSElement child : children) {
			if (options.equals(child.getName())) {
				childFoundFlag = true;
				if (!(child instanceof Link)) {
					child.setOwner("suny");
					break;
				} else {
					System.out.println(child.getName() + ": Not a file or directory");
					return;
				}
			}
		}
		if (!childFoundFlag) {
			System.out.println(options + ": " + "No such file or directory");
		}

	}

	public String getCommandName() {
		return command;
	}

}
