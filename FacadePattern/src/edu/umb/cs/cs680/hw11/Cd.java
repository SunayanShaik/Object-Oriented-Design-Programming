package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Cd implements Command {

	private FileSystem fileSystem;
	private String options;
	private String command;

	public Cd(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory root = fileSystem.getRoot();
		Directory current = fileSystem.getCurrent();
		ArrayList<FSElement> currChildren = current.getChildren();

		if (options.isEmpty()) {
			fileSystem.setCurrent(root);
		} else if (options.equals("..")) {
			if (!current.equals(root)) {
				fileSystem.setCurrent(current.getParent());
			}
		} else {
			Boolean childFoundFlag = false;
			for (FSElement currChild : currChildren) {
				if (options.equals(currChild.getName())) {
					childFoundFlag = true;
					if (currChild instanceof File) {
						System.out.println(options + ": " + "Not a directory");
					} else if (currChild instanceof Link) {
						FSElement target = ((Link) currChild).getTarget();
						if (target instanceof File || target instanceof Link) {
							System.out.println(target.getName() + ": " + "Not a directory");
						} else {
							fileSystem.setCurrent((Directory) target);
						}
					} else {
						fileSystem.setCurrent((Directory) currChild);
					}
				}
			}
			if(!childFoundFlag) {
				System.out.println(options + ": " + "No such file or directory");
			}
		}
	}

	public String getCommandName() {
		return command;
	}

}
