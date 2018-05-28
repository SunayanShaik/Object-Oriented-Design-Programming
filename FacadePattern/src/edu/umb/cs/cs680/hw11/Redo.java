package edu.umb.cs.cs680.hw11;

public class Redo implements Command{

	FileSystem fileSystem;
	private String command;

	public Redo(String command) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
	}

	public void execute() {
		System.out.println("Redo of the recently-executed command");
	}

	public String getCommandName() {
		return command;
	}
	
}
