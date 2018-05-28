package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class CommandHistory {

	ArrayList<Command> commands = new ArrayList<Command>();
	
	public void push(Command command) {
		commands.add(command);
	}
	
	public Command pop() {
		if(commands.size() == 0) {
			return null;
		}
		Command command = commands.get(commands.size() - 1);
		int i=0;
		for(i = commands.size()-1; i>=0; i--) {
			if(commands.get(i).getCommandName() != "redo") {
				command = commands.get(i);
				break;
			}
		}
		if(command.getCommandName() != "redo") {
			return command;
		}
		return null;
	}
	
	public ArrayList<Command> getCommands() {
		return this.commands;
	}
	
}
