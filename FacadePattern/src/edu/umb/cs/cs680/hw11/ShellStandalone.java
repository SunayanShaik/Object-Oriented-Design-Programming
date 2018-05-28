package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class ShellStandalone {

	private Directory root, system, home, pictures;
	private File a, b, c, d, e, f;
	private Link x, y;
	private FileSystem fileSystem;
	
	public static void main(String[] args) { 
		ShellStandalone shellStandalone = new ShellStandalone();
		shellStandalone.constructFileSystemTree();
		System.out.println("Shell Started..!! Please Enter the commands..!!");
		Shell shell = new Shell();
		shell.startShell();
	}
	
	private void constructFileSystemTree() {
		Date created = new Date(System.currentTimeMillis() - 3600 * 1000);
		Date modified = new java.util.Date();
		root = new Directory(null, "root", "sunayan", created, modified, 0, false);
		system = new Directory(root, "system", "sunayan", created, modified, 0, false);
		home = new Directory(root, "home", "sunayan", created, modified, 0, false);
		pictures = new Directory(home, "pictures", "sunayan", created, modified, 0, false);

		root.appendChild(system);
		root.appendChild(home);

		a = new File(system, "a.txt", "sunayan", created, modified, 10, true);
		b = new File(system, "b.txt", "sunayan", created, modified, 20, true);
		c = new File(system, "c.pdf", "sunayan", created, modified, 30, true);

		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);

		d = new File(home, "d.doc", "sunayan", created, modified, 40, true);
		x = new Link(home, system, "x", "sunayan", created, modified, 0, false);

		home.appendChild(d);
		home.appendChild(x);
		home.appendChild(pictures);

		e = new File(pictures, "e.txt", "sunayan", created, modified, 50, true);
		f = new File(pictures, "f.pdf", "sunayan", created, modified, 60, true);
		y = new Link(pictures, e, "y", "sunayan", created, modified, 0, false);

		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);

		a.setHasVirus(true);
		d.setHasVirus(true);
		f.setHasVirus(true);

		fileSystem = FileSystem.getFileSystem(root);
		fileSystem.setCurrent(root);
		fileSystem.showAllElements();
	}

}
