package edu.umb.cs.cs680.hw11;

public class Count implements Command{

	FileSystem fileSystem;
	private String command;
	private String options;

	public Count(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
		this.options = options;
	}

	public void execute() {
		if(!this.options.isEmpty()) {
			System.out.println("count: Options are not allowed..!!");
			return;
		}
		Directory currentDir = fileSystem.getCurrent();
		fileSystem.countVisit(currentDir);
	}

	public String getCommandName() {
		return command;
	}

}
