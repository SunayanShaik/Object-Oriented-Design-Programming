package edu.umb.cs.cs680.hw10;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.umb.cs.cs68.hw10.CountingVisitor;
import edu.umb.cs.cs68.hw10.Directory;
import edu.umb.cs.cs68.hw10.File;
import edu.umb.cs.cs68.hw10.FileSearchVisitor;
import edu.umb.cs.cs68.hw10.FileSystem;
import edu.umb.cs.cs68.hw10.Link;
import edu.umb.cs.cs68.hw10.VirusCheckingVisitor;

public class VisitorsTest {

	private Directory rootDir, systemDir, homeDir, picturesDir;
	private File a, b, c, d, e, f;
	private Link x, y;
	private FileSearchVisitor fsVisitor1, fsVisitor2, fsVisitor3;
	private CountingVisitor countingVisitor1, countingVisitor2, countingVisitor3;
	
	@Before
	public void setUp() throws Exception {
		
		Date created = new Date(System.currentTimeMillis() - 3600 * 1000);
		Date modified = new java.util.Date();
		rootDir = new Directory(null, "root", "sunayan", created, modified, 0, false);
		systemDir = new Directory(rootDir, "system", "sunayan", created, modified, 0, false);
		homeDir = new Directory(rootDir, "home", "sunayan", created, modified, 0, false);
		picturesDir = new Directory(homeDir, "pictures", "sunayan", created, modified, 0, false);
		
		rootDir.appendChild(systemDir);
		rootDir.appendChild(homeDir);
		
		a = new File(systemDir, "a.txt", "sunayan", created, modified, 10, true);
		b = new File(systemDir, "b.txt", "sunayan", created, modified, 20, true);
		c = new File(systemDir, "c.pdf", "sunayan", created, modified, 30, true);
		
		systemDir.appendChild(a);
		systemDir.appendChild(b);
		systemDir.appendChild(c);
		
		d = new File(homeDir, "d.doc", "sunayan", created, modified, 40, true);
		x = new Link(homeDir, systemDir, "x", "sunayan", created, modified, 0, false);
		
		homeDir.appendChild(d);
		homeDir.appendChild(x);
		homeDir.appendChild(picturesDir);
		
		e = new File(picturesDir, "e.txt", "sunayan",created, modified, 50, true);
		f = new File(picturesDir, "f.pdf", "sunayan",created, modified, 60, true);
		y = new Link(picturesDir, e, "y", "sunayan",created, modified, 0, false);
		
		picturesDir.appendChild(e);
		picturesDir.appendChild(f);
		picturesDir.appendChild(y);
		
		a.setHasVirus(true);
		d.setHasVirus(true);
		f.setHasVirus(true);
		
	}

	@After
	public void tearDown() throws Exception {
		fsVisitor1 = null;
		fsVisitor2 = null;
		fsVisitor3 = null;
		countingVisitor1 = null;
		countingVisitor2 = null;
		countingVisitor3 = null;
	}

	@Test
	public void showAllElementsTest() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Tree Structure: ");
		System.out.println("----------------------------------------------------------------------");
		FileSystem fileSystem = FileSystem.getFileSystem(rootDir);
		fileSystem.showAllElements();
	}
	
	@Test
	public void testFileSearchVisitorOnRoot() {
		int expectedTxtFileSize = 3;
		int expectedPdfSize = 2;
		int expectedDocSize = 1;
		
		fsVisitor1 = new FileSearchVisitor(".txt");
		fsVisitor2 = new FileSearchVisitor(".pdf");
		fsVisitor3 = new FileSearchVisitor(".doc");
		rootDir.accept(fsVisitor1);
		rootDir.accept(fsVisitor2);
		rootDir.accept(fsVisitor3);
		
		int actualTxtFieldSize = fsVisitor1.getFoundFiles().size();
		int actualPdfSize = fsVisitor2.getFoundFiles().size();
		int actualDocSize = fsVisitor3.getFoundFiles().size();
		
		assertEquals(expectedTxtFileSize, actualTxtFieldSize);
		assertEquals(expectedPdfSize, actualPdfSize);
		assertEquals(expectedDocSize, actualDocSize);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("FileSearchVisitor sizes in a tree :");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("For '.txt' files in rootDir : " +actualTxtFieldSize);
		System.out.println("For '.pdf' files in rootDir : " +actualPdfSize);
		System.out.println("For '.doc' files in rootDir : " +actualDocSize);
	}
	
	@Test
	public void testCountingVisitorOnDirNum() {
		int expectedRootDirCount = 4;
		int expectedHomeDirCount = 2;
		int expectedPicturesDirCount = 1;
		
		countingVisitor1 = new CountingVisitor();
		countingVisitor2 = new CountingVisitor();
		countingVisitor3 = new CountingVisitor();
		
		rootDir.accept(countingVisitor1);
		homeDir.accept(countingVisitor2);
		picturesDir.accept(countingVisitor3);
		
		int actualRootDirCount = countingVisitor1.getDirNum();
		int actualHomeDirCount = countingVisitor2.getDirNum();
		int actualPicturesDirCount = countingVisitor3.getDirNum();
		assertEquals(expectedRootDirCount, actualRootDirCount);
		assertEquals(expectedHomeDirCount, actualHomeDirCount);
		assertEquals(expectedPicturesDirCount, actualPicturesDirCount);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Counting Number of Dir in a tree :");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("On rootDir     : " +actualRootDirCount);
		System.out.println("On homeDir     : " +actualHomeDirCount);
		System.out.println("On picturesDir : " +actualPicturesDirCount);
	}
	
	@Test
	public void testCountingVisitorOnFileNum() {
		int expectedRootFilesCount = 6;
		int expectedHomeFilesCount = 3;
		int expectedPicturesFilesDirCount = 2;
		
		countingVisitor1 = new CountingVisitor();
		countingVisitor2 = new CountingVisitor();
		countingVisitor3 = new CountingVisitor();
		
		rootDir.accept(countingVisitor1);
		homeDir.accept(countingVisitor2);
		picturesDir.accept(countingVisitor3);
		
		int actualRootFilesCount = countingVisitor1.getFileNum();
		int actualHomeFilesCount = countingVisitor2.getFileNum();
		int actualPicturesFilesCount = countingVisitor3.getFileNum();
		
		assertEquals(expectedRootFilesCount, actualRootFilesCount);
		assertEquals(expectedHomeFilesCount, actualHomeFilesCount);
		assertEquals(expectedPicturesFilesDirCount, actualPicturesFilesCount);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Counting Number of Files in a tree :");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("On rootDir     : " +actualRootFilesCount);
		System.out.println("On homeDir     : " +actualHomeFilesCount);
		System.out.println("On picturesDir : " +actualPicturesFilesCount);
	}
	
	@Test
	public void testCountingVisitorOnLinksNum() {
		int expectedRootLinksCount = 2;
		int expectedHomeLinksCount = 2;
		int expectedPicturesLinksCount = 1;
		
		countingVisitor1 = new CountingVisitor();
		countingVisitor2 = new CountingVisitor();
		countingVisitor3 = new CountingVisitor();
		
		rootDir.accept(countingVisitor1);
		homeDir.accept(countingVisitor2);
		picturesDir.accept(countingVisitor3);
		
		int actualRootLinksCount = countingVisitor1.getLinkNum();
		int actualHomeLinksCount = countingVisitor2.getLinkNum();
		int actualPicturesLinksCount = countingVisitor3.getLinkNum();
		
		assertEquals(expectedRootLinksCount, actualRootLinksCount);
		assertEquals(expectedHomeLinksCount, actualHomeLinksCount);
		assertEquals(expectedPicturesLinksCount, actualPicturesLinksCount);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Counting Number of Links in a tree :");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("On rootDir     : " +actualRootLinksCount);
		System.out.println("On homeDir     : " +actualHomeLinksCount);
		System.out.println("On picturesDir : " +actualPicturesLinksCount);
	}
	
	@Test
	public void testVirusCheckingVisitor() {
		
		int expectedRootQuarantinedNum = 3;
		int expectedHomeQuarantinedNum = 2;
		int expectedPicturesQuarantinedNum = 1;
		
		VirusCheckingVisitor virusCheckVisitor1 , virusCheckVisitor2, virusCheckVisitor3;
		virusCheckVisitor1 = new VirusCheckingVisitor();
		virusCheckVisitor2 = new VirusCheckingVisitor();
		virusCheckVisitor3 = new VirusCheckingVisitor();
		
		rootDir.accept(virusCheckVisitor1);
		homeDir.accept(virusCheckVisitor2);
		picturesDir.accept(virusCheckVisitor3);
		
		int actualRootQuarantinedNum = virusCheckVisitor1.getQuarantinedNum();
		int actualHomeQuarantinedNum = virusCheckVisitor2.getQuarantinedNum();
		int actualPicturesQuarantinedNum = virusCheckVisitor3.getQuarantinedNum();
		
		assertEquals(expectedRootQuarantinedNum, actualRootQuarantinedNum);
		assertEquals(expectedHomeQuarantinedNum, actualHomeQuarantinedNum);
		assertEquals(expectedPicturesQuarantinedNum, actualPicturesQuarantinedNum);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Checking Virus in tree :");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("On rootDir     : " +actualRootQuarantinedNum);
		System.out.println("On homeDir     : " +actualHomeQuarantinedNum);
		System.out.println("On picturesDir : " +actualPicturesQuarantinedNum);
	}

}
