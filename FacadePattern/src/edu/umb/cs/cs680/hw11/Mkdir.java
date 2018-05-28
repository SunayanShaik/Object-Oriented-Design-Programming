package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class Mkdir implements Command {

	FileSystem fileSystem;
	private String options;
	private String command;

	public Mkdir(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory current = fileSystem.getCurrent();
		Date date = new java.util.Date();
		if (options.isEmpty()) {
			System.out.println("mkdir: missing operand" + " Please provide a valid dir name to create a new directory");
		} else {
			Directory newDir = new Directory(current, options, "sunayan", date, date, 0, false);
			current.appendChild(newDir);
		}

	}

	public String getCommandName() {
		return command;
	}

}
