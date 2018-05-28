package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Ls implements Command {

	FileSystem fileSystem;
	private String command;
	private String options;

	public Ls(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
		this.options = options;
	}
	
	public void execute() {
		if(!this.options.isEmpty()) {
			System.out.println("ls: Options are not allowed..!!");
			return;
		}
		ArrayList<FSElement> currDirChildren = fileSystem.getCurrent().getChildren();
		System.out.print(">");
		for(FSElement child: currDirChildren) {
			System.out.print(child.getName() + "\t");
		}
		System.out.println("\n");
	}

	public String getCommandName() {
		return command;
	}

}
