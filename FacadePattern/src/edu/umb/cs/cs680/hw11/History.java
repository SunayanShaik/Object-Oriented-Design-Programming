package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class History implements Command {

	FileSystem fileSystem;
	private ArrayList<Command> commands;
	private String command;
	private String options;

	public History(String command, ArrayList<Command> commands, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
		this.commands = commands;
		this.options = options;
	}

	public void execute() {
		if(!this.options.isEmpty()) {
			System.out.println("history: Options are not allowed..!!");
			return;
		}
		for(Command command: commands) {
			System.out.println(command.getCommandName());
		}
	}

	public String getCommandName() {
		return this.command;
	}
	
}
