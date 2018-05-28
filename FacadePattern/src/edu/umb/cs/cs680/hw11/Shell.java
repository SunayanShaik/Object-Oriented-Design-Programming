package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Shell {
	
	CommandHistory commandHistory = new CommandHistory();
	
	public void startShell() {
		while (true) {
			try {
			String command = inputCommand();
			if(command.equalsIgnoreCase("exit")) {
				break;
			}
			executeCommand(command);
			} catch (Exception ex) {
				System.out.println("Exception occured..!! Please try again..!!");
			}
		}
		System.out.println("Shell Exited..!!");
	}

	private String inputCommand() {
		System.out.println(">");
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		return command;
	}

	public void executeCommand(String command) {
		String commandPrefix = getCommand(command);
		String options = getOptions(command);
		Command commandObj = null;
		if (commandPrefix.isEmpty()) {
			return;
		} else if (commandPrefix.equals("ls")) {
			commandObj = new Ls(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("cd")) {
			commandObj = new Cd(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("pwd")) {
			commandObj = new Pwd(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("dir")) {
			commandObj = new Dir(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("mkdir")) {
			commandObj = new Mkdir(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("rmdir")) {
			commandObj = new Rmdir(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("chown")) {
			commandObj = new Chown(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("ln")) {
			commandObj = new Ln(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("mv")) {
			commandObj = new Mv(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("cp")) {
			commandObj = new Cp(command, options);
			commandObj.execute();
		} else if (commandPrefix.equals("history")) {
			ArrayList<Command> commands = commandHistory.getCommands();
			commandObj = new History(command, commands, options);
			commandObj.execute();
		} else if (commandPrefix.equals("redo")) {
			commandObj = new Redo(command);
			commandObj.execute();
			Command commandObjPoped = commandHistory.pop();
			if (commandObjPoped != null) {
				commandObjPoped.execute();
			}
		} else if(commandPrefix.equals("sort")) {
			commandObj = new Sort(command, options);
			commandObj.execute();
		} else if(commandPrefix.equals("search")) {
			commandObj = new Search(command, options);
			commandObj.execute();
		} else if(commandPrefix.equals("count")) {
			commandObj = new Count(command, options);
			commandObj.execute();
		} else if(commandPrefix.equals("viruscheck")) {
			commandObj = new VirusCheck(command, options);
			commandObj.execute();
		}
		else {
			System.out.println(commandPrefix + ": command not found");
			return;
		}
		commandHistory.push(commandObj);
	}
	
	private String getOptions(String command) {
		if (command.trim().isEmpty()) {
			return "";
		}
		String[] optionsArr = command.split(" ");
		optionsArr = Arrays.copyOfRange(optionsArr, 1, (optionsArr.length));
		String options = String.join(" ", optionsArr);
		return options.trim();
	}

	private String getCommand(String command) {
		if (command.trim().isEmpty()) {
			return "";
		}
		String[] commandArr = command.split(" ");
		commandArr = Arrays.copyOfRange(commandArr, 0, 1);
		return commandArr[0].trim();
	}

}
