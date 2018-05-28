package edu.umb.cs.cs680.hw11;

public class TimeStampComparator {

	public int compare(FSElement fs1, FSElement fs2) {
		return fs1.getLastModified().compareTo(fs2.getLastModified());
	}
	
}
