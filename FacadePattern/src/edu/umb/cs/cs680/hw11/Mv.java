package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Date;

public class Mv implements Command {

	FileSystem fileSystem;
	private String options;
	private String command;

	public Mv(String command, String options) {
		fileSystem = FileSystem.getFileSystem(null);
		this.options = options;
		this.command = command;
	}

	public void execute() {
		Directory current = fileSystem.getCurrent();
		Date date = new java.util.Date();
		
		if (options.isEmpty()) {
			System.out.println("mv: missing operand" + " Please provide a valid <src file>  <target file>");
			return;
		}
		String[] optionsArr = options.split(" ");
		if(optionsArr.length != 2) {
			System.out.println("mv: missing one operand" + " Please provide a valid <src file>  <target file>");
			return;
		}
		String srcOption = optionsArr[0];
		String destOption = optionsArr[1];
		ArrayList<FSElement> children = fileSystem.getCurrent().getChildren();
		Boolean srcFoundFlag = false, destFoundFlag = false;
		FSElement srcElement = null, destDir = null;
		
		for (FSElement child : children) {
			if(srcOption.equals(child.getName())) {
				srcFoundFlag = true;
				srcElement = child;
			}
			if(destOption.equals(child.getName())) {
				destFoundFlag = true;
				if(!(child instanceof Directory)) {
					System.out.println(destOption + ": Not a directory");
					return;
				}
				destDir = child;
			}
		}
		if(!srcFoundFlag) {
			System.out.println(srcOption + ": Provided src file/dir doesn't exist");
			return;
		}
		
		if(!destFoundFlag) {
			System.out.println(destOption + ": Provided dest dit doesn't exist");
			return;
		}
		
		fileSystem.addChild((Directory) destDir, srcElement);
		current.removeChild(srcElement);
	}

	public String getCommandName() {
		return command;
	}
	
}
