package edu.umb.cs.cs680.hw07;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileCachePolicyTest {

	private FileCache fileCache, fileCache1, fileCache2;
	private int sizeOfCache;
	CacheReplacementPolicy replacementPolicy;

	@Before
	public void setUp() throws IOException {
		String fileContent1 = "Hello.! This is file1 data.";
		String fileContent2 = "Hello.! This is file2 data.";
		String fileContent3 = "Hello.! This is file3 data.";

		BufferedWriter out1 = new BufferedWriter(new FileWriter("file1.txt"));
		BufferedWriter out2 = new BufferedWriter(new FileWriter("file2.txt"));
		BufferedWriter out3 = new BufferedWriter(new FileWriter("file3.txt"));

		out1.write(fileContent1);
		out2.write(fileContent2);
		out3.write(fileContent3);

		out1.close();
		out2.close();
		out3.close();
	}

	@Test
	public void fileFetchNullByDefaultTest() throws IOException {
		fileCache = FileCache.getInstance(2);
		fileCache.replace("file1.txt");
		
		String fileContentActual1 = fileCache.fetch("file1.txt");
		String fileContentExpected1 = "Hello.! This is file1 data.";
		assertEquals(fileContentActual1, fileContentExpected1);
		
	}
	
	@Test
	public void fileFetchByFifoTest() throws IOException {
		fileCache = FileCache.getInstance(2, new FIFO());
		fileCache.replace("file1.txt");
		fileCache.replace("file2.txt");
		fileCache.replace("file3.txt");
		
		String fileContentActual1 = fileCache.fetch("file1.txt");
		String fileContentExpected1 = null;
		assertEquals(fileContentActual1, fileContentExpected1);
		
		String fileContentActual2 = fileCache.fetch("file2.txt");
		String fileContentExpected2 = "Hello.! This is file2 data.";
		assertEquals(fileContentActual2, fileContentExpected2);
		
		String fileContentActual3 = fileCache.fetch("file3.txt");
		String fileContentExpected3 = "Hello.! This is file3 data.";
		assertEquals(fileContentActual3, fileContentExpected3);
	}

	@Test
	public void fileReplaceNullReplacementTest() throws IOException {
		CacheReplacementPolicy replacementPolicyActual;
		fileCache.fileCache = null;
		fileCache = FileCache.getInstance(2);
		fileCache.replace("file2.txt");
		assertEquals(fileCache.getCachedContentCount(), 0);
		replacementPolicyActual = fileCache.getReplacementPolicy();
		assertEquals(NullReplacement.class, replacementPolicyActual.getClass());
	}

	@Test
	public void fileReplaceFifoReplacementTest() throws IOException {
		CacheReplacementPolicy replacementPolicyActual;
		fileCache.fileCache = null;
		fileCache = FileCache.getInstance(2, new FIFO());
		fileCache.replace("file1.txt");
		fileCache.replace("file2.txt");
		fileCache.replace("file3.txt");
		assertEquals(fileCache.getCachedContentCount(), 2);
		replacementPolicyActual = fileCache.getReplacementPolicy();
		assertEquals(FIFO.class, replacementPolicyActual.getClass());
	}
	
	@Test
	public void getInstanceUsingConstructorNullByDefaultTest() {
		fileCache1 = FileCache.getInstance(2);
		fileCache2 = FileCache.getInstance(2);
		assertEquals(fileCache1, fileCache2);
		
	}
	
	@Test
	public void getInstanceUsingConstructorByFIFOTest() {
		fileCache1 = FileCache.getInstance(2, new FIFO());
		fileCache2 = FileCache.getInstance(2, new FIFO());
		assertEquals(fileCache1, fileCache2);
		
	}
	
	@Test
	public void FileCachingTestDemo() throws IOException {
		
		fileCache = FileCache.getInstance(2);
		
		System.out.println("*****************************Null Replacement Policy********************************");
		System.out.println("--------------------------File fetch()------------------------------------");
		System.out.println(fileCache.fetch("file1.txt"));
		System.out.println(fileCache.fetch("file2.txt"));
		System.out.println(fileCache.fetch("file3.txt"));
		System.out.println("--------------------------Number of Files cached before Null Replacement Policy------------------------------------");
		System.out.println(fileCache.getCachedContentCount());
		
		fileCache.replace("file1.txt");
		System.out.println("--------------------------File fetch() after replacing(Null replacement policy)------------------------------------");
		System.out.println(fileCache.fetch("file1.txt"));
		System.out.println(fileCache.fetch("file2.txt"));
		System.out.println(fileCache.fetch("file3.txt"));
		System.out.println("--------------------------Number of Files cached after Null Replacement Policy------------------------------------");
		System.out.println(fileCache.getCachedContentCount());
		
		fileCache = FileCache.getInstance(2, new FIFO());
		
		System.out.println("*****************************FIFO Replacement Policy********************************");
		System.out.println("--------------------------File fetch()------------------------------------");
		System.out.println(fileCache.fetch("file1.txt"));
		System.out.println(fileCache.fetch("file2.txt"));
		System.out.println(fileCache.fetch("file3.txt"));
		System.out.println("--------------------------Number of Files cached before FIFO Replacement Policy------------------------------------");
		System.out.println(fileCache.getCachedContentCount());
		
		fileCache.replace("file1.txt");
		System.out.println("--------------------------File fetch() after replacing(FIFO replacement policy)------------------------------------");
		System.out.println(fileCache.fetch("file1.txt"));
		System.out.println(fileCache.fetch("file2.txt"));
		System.out.println(fileCache.fetch("file3.txt"));
		System.out.println("--------------------------Number of Files cached after FIFO Replacement Policy------------------------------------");
		System.out.println(fileCache.getCachedContentCount());
	}

	@After
	public void tearDown() {
		File file1 = new File("file1.txt");
		File file2 = new File("file2.txt");
		File file3 = new File("file3.txt");
		
		file1.deleteOnExit();
		file2.deleteOnExit();
		file3.deleteOnExit();
	}
	
}
