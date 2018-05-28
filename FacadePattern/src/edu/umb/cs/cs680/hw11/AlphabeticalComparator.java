package edu.umb.cs.cs680.hw11;

import java.util.Comparator;

public class AlphabeticalComparator implements Comparator<FSElement>{

	public int compare(FSElement fs1, FSElement fs2) {
		return fs1.getName().compareTo(fs2.getName());
	}
	
}
