package edu.umb.cs.cs680.hw11;

public class Pwd implements Command {

	FileSystem fileSystem;
	private String command;
	private String options;

	public Pwd(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
		this.options = options;
	}
	
	public void execute() {
		if(!this.options.isEmpty()) {
			System.out.println("pwd: Options are not allowed..!!");
			return;
		}
		String currentDir = fileSystem.getCurrent().getFullPath();
		System.out.println(currentDir);
	}

	public String getCommandName() {
		return command;
	}

}
