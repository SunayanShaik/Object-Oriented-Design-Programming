package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Date;

public class Ln implements Command {

	FileSystem fileSystem;
	private String options;
	private String command;

	public Ln(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory current = fileSystem.getCurrent();
		Date date = new java.util.Date();
		
		if (options.isEmpty()) {
			System.out.println("ln: missing operand" + " Please provide a valid <src file>  <target file>");
			return;
		}
		String[] optionsArr = options.split(" ");
		if(optionsArr.length != 2) {
			System.out.println("ln: missing one operand" + " Please provide a valid <src file>  <target file>");
			return;
		}
		String targetoption = optionsArr[0];
		String linkNameOption = optionsArr[1];
		ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
		Boolean childFoundFlag = false;
		Link newLink = null;

		for (FSElement child : children) {
			if (targetoption.equals(child.getName())) {
				childFoundFlag = true;
				newLink = new Link(current, child, linkNameOption, "sunayan", date, date, 0, false);
			}
		}
		if (childFoundFlag) {
			current.appendChild(newLink);
		}
		if (!childFoundFlag) {
			System.out.println(targetoption + ": No such file or directory");
		}

	}

	public String getCommandName() {
		return command;
	}

}
