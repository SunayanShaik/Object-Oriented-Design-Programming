package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Sort implements Command {

	FileSystem fileSystem;
	private String command;
	ArrayList<FSElement> sortedElements;
	private String options;

	public Sort(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
		this.options = options;
	}

	public void execute() {
		if(!this.options.isEmpty()) {
			System.out.println("sort: Options are not allowed..!!");
			return;
		}
		sortedElements = fileSystem.sort(fileSystem.getCurrent(), new AlphabeticalComparator());
		for(FSElement sortedElement: sortedElements) {
			System.out.print(sortedElement.getName() + "\t");
		}
		System.out.println("\n");
	}

	public String getCommandName() {
		return command;
	}
	
}
