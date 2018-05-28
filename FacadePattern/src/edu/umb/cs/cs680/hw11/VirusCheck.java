package edu.umb.cs.cs680.hw11;

public class VirusCheck implements Command {

	FileSystem fileSystem;
	private String command;
	private String options;

	public VirusCheck(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.command = command;
		this.options = options;
	}

	public void execute() {
		if(!this.options.isEmpty()) {
			System.out.println("viruscheck: Options are not allowed..!!");
			return;
		}
		Directory currentDir = fileSystem.getCurrent();
		fileSystem.virusCheckVisit(currentDir);
	}

	public String getCommandName() {
		return command;
	}
	
}
