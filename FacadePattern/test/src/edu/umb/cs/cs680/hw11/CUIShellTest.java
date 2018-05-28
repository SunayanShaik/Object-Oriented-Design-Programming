package edu.umb.cs.cs680.hw11;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CUIShellTest {

	private Directory root, system, home, pictures;
	private File a, b, c, d, e, f;
	private Link x, y;
	private FileSystem fileSystem;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	Shell shell;

	@Before
	public void setUp() throws Exception {

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

	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
		fileSystem = null;
	}

	@Test
	public void showElementsTest() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Tree Structure: ");
		System.out.println("----------------------------------------------------------------------");
		FileSystem fileSystem = FileSystem.getFileSystem(root);
		fileSystem.showAllElements();
	}

	@Test
	public void testShell() {
		System.setOut(new PrintStream(outContent));
		Shell shell = new Shell();
		shell.executeCommand("");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(out, "");
		
		shell.executeCommand("sunayan");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(out, "sunayan: command not found");
	}

	@Test
	public void testPwd() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "/root";
		String expected2 = "/root/home";
		String expected3 = "/root/home/pictures";
		String expected4 = "pwd: Options are not allowed..!!";

		shell.executeCommand("pwd");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("cd home");
		shell.executeCommand("pwd");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("cd pictures");
		shell.executeCommand("pwd");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);
		
		shell.executeCommand("pwd as");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);
	}

	@Test
	public void testCd() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "home";
		String expected2 = "pictures";
		String expected3 = "root";
		String expected4 = "suny: No such file or directory";
		String expected5 = "d.doc: Not a directory";
		String expected6 = "e.txt: Not a directory";
		String expected7 = ">a.txt	b.txt	c.pdf";
		String expected8 = "tempLink1: Not a directory";

		shell.executeCommand("cd home");
		assertEquals(expected1, fileSystem.getCurrent().getName());

		shell.executeCommand("cd pictures");
		assertEquals(expected2, fileSystem.getCurrent().getName());

		shell.executeCommand("cd ..");
		assertEquals(expected1, fileSystem.getCurrent().getName());

		shell.executeCommand("cd");
		assertEquals(expected3, fileSystem.getCurrent().getName());
		
		shell.executeCommand("cd suny");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);
		
		shell.executeCommand("cd home");
		shell.executeCommand("cd d.doc");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected5, out);
		
		shell.executeCommand("cd pictures");
		shell.executeCommand("cd y");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected6, out);
		

		shell.executeCommand("cd ..");
		shell.executeCommand("cd x");
		shell.executeCommand("ls");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected7, out);
		
		shell.executeCommand("cd");
		shell.executeCommand("cd home");
		shell.executeCommand("ln d.doc tempLink1");
		shell.executeCommand("ln tempLink1 tempLink2");
		shell.executeCommand("cd tempLink2");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected8, out);
		
		shell.executeCommand("cd");
		shell.executeCommand("cd ..");
		assertEquals(expected3, fileSystem.getCurrent().getName());
		
	}

	@Test
	public void testLs() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = ">system" + "\t" + "home";
		String expected2 = ">d.doc" + "\t" + "x" + "\t" + "pictures";
		String expected3 = ">e.txt" + "\t" + "f.pdf" + "\t" + "y";
		String expected4 = "ls: Options are not allowed..!!";

		shell.executeCommand("ls");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("cd home");
		shell.executeCommand("ls");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("cd pictures");
		shell.executeCommand("ls");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);
		
		shell.executeCommand("ls as");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);
	}

	@Test
	public void testDir() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "Dir" + "\t" + "system" + "\t" + "60" + "\t" + "sunayan";
		String expected2 = "File\td.doc\t40\tsunayan";
		String expected3 = "File\te.txt\t50\tsunayan";
		String expected4 = "x: dir command disabled for Link";
		String expected5 = "suny: No such file or directory";
		String expected6 = "Dir" + "\t" + "home" + "\t" + "150" + "\t" + "sunayan";

		shell.executeCommand("dir");
		String out = outContent.toString().trim();
		outContent.reset();
		assertTrue(out.contains(expected1));

		shell.executeCommand("dir ..");
		out = outContent.toString().trim();
		outContent.reset();
		assertTrue(out.contains(expected1));
		
		shell.executeCommand("dir home");
		out = outContent.toString().trim();
		outContent.reset();
		assertTrue(out.contains(expected6));
		
		shell.executeCommand("cd home");
		shell.executeCommand("dir");
		out = outContent.toString().trim();
		outContent.reset();
		assertTrue(out.contains(expected2));

		shell.executeCommand("dir x");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);

		shell.executeCommand("dir suny");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected5, out);

		shell.executeCommand("dir d.doc");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("cd pictures");
		shell.executeCommand("dir");
		out = outContent.toString().trim();
		outContent.reset();
		assertTrue(out.contains(expected3));

		shell.executeCommand("dir e.txt");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);
	}

	@Test
	public void testMkdir() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = ">system\thome\ttempDir";
		String expected2 = "mkdir: missing operand Please provide a valid dir name to create a new directory";

		shell.executeCommand("mkdir tempDir");
		shell.executeCommand("ls");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("mkdir");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

	}

	@Test
	public void testRmdir() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = ">system\thome";
		String expected2 = "rmdir: missing operand Please provide a valid dir name to remove directory";
		String expected3 = "suny: No such file or directory";
		String expected4 = "x: Not a directory";

		shell.executeCommand("mkdir tempDir");
		shell.executeCommand("rmdir tempDir");
		shell.executeCommand("ls");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("rmdir");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("rmdir suny");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);

		shell.executeCommand("cd home");
		shell.executeCommand("rmdir x");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);
	}

	@Test
	public void testChown() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "suny";
		String expected2 = "Dir	system	60	suny\n" + "Dir	home	150	suny";
		String expected3 = "x: Not a file or directory";
		String expected4 = "s: No such file or directory";
		String expected5 = "File	e.txt	50	suny\n" + "File	f.pdf	60	suny";

		shell.executeCommand("chown system");
		shell.executeCommand("dir");
		String out = outContent.toString().trim();
		outContent.reset();
		assertTrue(expected1, out.contains(expected1));

		shell.executeCommand("chown");
		shell.executeCommand("dir");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("chown s");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);

		shell.executeCommand("cd home");
		shell.executeCommand("chown x");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);

		shell.executeCommand("cd pictures");
		shell.executeCommand("chown");
		shell.executeCommand("dir");
		out = outContent.toString().trim();
		outContent.reset();
		assertTrue(expected5, out.contains(expected5));
	}

	@Test
	public void testLn() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = ">system\thome\ttempLink";
		String expected2 = "ln: missing operand Please provide a valid <src file>  <target file>";
		String expected3 = "ln: missing one operand Please provide a valid <src file>  <target file>";
		String expected4 = "x: No such file or directory";

		shell.executeCommand("ln home tempLink");
		shell.executeCommand("ls");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("ln");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("ln home");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);

		shell.executeCommand("cd system");
		shell.executeCommand("ln x suny");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);
	}

	@Test
	public void testMv() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = ">x\tpictures";
		String expected2 = "mv: missing operand Please provide a valid <src file>  <target file>";
		String expected3 = "mv: missing one operand Please provide a valid <src file>  <target file>";
		String expected4 = "pictures: Provided dest dit doesn't exist";
		String expected5 = "d: Provided src file/dir doesn't exist";
		String expected6 = "y: Not a directory";

		shell.executeCommand("cd home");
		shell.executeCommand("mv d.doc pictures");
		shell.executeCommand("ls");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("mv d.doc");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);

		shell.executeCommand("mv");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("cd pictures");
		shell.executeCommand("mv d.doc pictures");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);

		shell.executeCommand("mv d pictures");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected5, out);

		shell.executeCommand("mv d.doc y");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected6, out);
	}

	@Test
	public void testCp() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = ">d.doc\tx\tpictures";
		String expected2 = "pic: Provided dest dit doesn't exist";
		String expected3 = "d: Provided src file/dir doesn't exist";
		String expected4 = "cp: missing operand Please provide a valid <src file>  <target file>";
		String expected5 = "cp: missing one operand Please provide a valid <src file>  <target file>";
		String expected6 = "x: Not a directory";
		String expected7 = "x: Provided src is a link. Cannot copy a link";
		String expected8 = ">e.txt\tf.pdf\ty\td.doc\ttempDir";

		shell.executeCommand("cd home");
		shell.executeCommand("cp d.doc pictures");
		shell.executeCommand("ls");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("cp d.doc pic");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

		shell.executeCommand("cp d pictures");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected3, out);

		shell.executeCommand("cp");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected4, out);

		shell.executeCommand("cp d.doc");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected5, out);

		shell.executeCommand("cp d.doc x");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected6, out);

		shell.executeCommand("cp x pictures");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected7, out);

		shell.executeCommand("mkdir tempDir");
		shell.executeCommand("cp tempDir pictures");
		shell.executeCommand("cd pictures");
		shell.executeCommand("ls");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected8, out);
	}

	@Test
	public void testHistory() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "mkdir tempDir\n" + "cd home\n" + "ls";
		String expected2 = "history: Options are not allowed..!!";

		shell.executeCommand("mkdir tempDir");
		shell.executeCommand("cd home");
		shell.executeCommand("ls");
		outContent.reset();
		shell.executeCommand("history");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);
		
		shell.executeCommand("history as");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);
	}

	@Test
	public void testRedo() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "Redo of the recently-executed command\n" + ">d.doc	x	pictures";
		String expected2 = "Redo of the recently-executed command\n" + 
				">d.doc	x	pictures";

		shell.executeCommand("redo");
		shell.executeCommand("redo");
		shell.executeCommand("redo");
		outContent.reset();
		String out = outContent.toString().trim();
		assertEquals("", out);
		
		
		shell.executeCommand("mkdir tempDir");
		shell.executeCommand("cd home");
		shell.executeCommand("ls");
		outContent.reset();
		shell.executeCommand("redo");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);
		
		shell.executeCommand("cd home");
		shell.executeCommand("ls");
		outContent.reset();
		shell.executeCommand("redo");
		shell.executeCommand("redo");
		outContent.reset();
		shell.executeCommand("redo");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);
	}

	@Test
	public void testSort() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "d.doc\tpictures\tx";
		String expected2 = "sort: Options are not allowed..!!";

		shell.executeCommand("cd home");
		shell.executeCommand("sort");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);
		
		shell.executeCommand("sort as");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);
	}

	@Test
	public void testSearch() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "Number of files found from current dir: 1";
		String expected2 = "search: provide a valid extension to search Ex: search .txt";

		shell.executeCommand("cd home");
		shell.executeCommand("search .txt");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);

		shell.executeCommand("search");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);
	}

	@Test
	public void testCount() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "Number of Directories from current dir: 2\n" + "Number of Files from current dir: 3\n"
				+ "Number of Links from current dir: 2";
		String expected2 = "count: Options are not allowed..!!";

		shell.executeCommand("cd home");
		shell.executeCommand("count");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);
		
		shell.executeCommand("count as");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);

	}
	
	@Test
	public void testVirusCheck() {
		System.setOut(new PrintStream(outContent));
		shell = new Shell();
		String expected1 = "Number of files quarantined from current dir: 2";
		String expected2 = "viruscheck: Options are not allowed..!!";

		shell.executeCommand("cd home");
		shell.executeCommand("viruscheck");
		String out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected1, out);
		
		shell.executeCommand("viruscheck as");
		out = outContent.toString().trim();
		outContent.reset();
		assertEquals(expected2, out);
	}
	
	@Test
	public void testCommandObjectNamesAndConstructors() {
		Command command = new Pwd("pwd", "");
		assertEquals("pwd", command.getCommandName());
		
		command = new Cd("cd", "home");
		assertEquals("cd", command.getCommandName());
		
		command = new Ls("ls", "");
		assertEquals("ls", command.getCommandName());
		
		command = new Dir("dir", "home");
		assertEquals("dir", command.getCommandName());
		
		command = new Mkdir("mkdir", "home");
		assertEquals("mkdir", command.getCommandName());
		
		command = new Rmdir("rmdir", "home");
		assertEquals("rmdir", command.getCommandName());
		
		command = new Ln("ln", "home homeLink");
		assertEquals("ln", command.getCommandName());
		
		command = new Mv("mv", "home homeLink");
		assertEquals("mv", command.getCommandName());
		
		command = new Cp("cp", "home homeLink");
		assertEquals("cp", command.getCommandName());
		
		command = new Chown("chown", "home");
		assertEquals("chown", command.getCommandName());
		
		command = new History("history", new ArrayList(), "");
		assertEquals("history", command.getCommandName());
		
		command = new Redo("redo");
		assertEquals("redo", command.getCommandName());
		
		command = new Sort("sort", "");
		assertEquals("sort", command.getCommandName());
		
		command = new Count("count", "");
		assertEquals("count", command.getCommandName());
		
		command = new VirusCheck("viruscheck", "");
		assertEquals("viruscheck", command.getCommandName());
		
		command = new Search("search", ".txt");
		assertEquals("search", command.getCommandName());
	}

}
