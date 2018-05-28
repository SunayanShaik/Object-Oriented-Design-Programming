package edu.umb.cs.cs680.hw11;

public class Search implements Command {

	FileSystem fileSystem;
	private String extension;
	private String command;

	public Search(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.extension = options;
		this.command = command;
	}

	public void execute() {
		Directory currentDir = fileSystem.getCurrent();
		if (extension.isEmpty()) {
			System.out.println("search: provide a valid extension to search Ex: search .txt");
		} else {
			fileSystem.fileSearchVisit(currentDir, extension);
		}	
	}

	public String getCommandName() {
		return command;
	}
	
}
