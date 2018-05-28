package edu.umb.cs.cs680.hw11;

import java.util.Comparator;

public class ReverseAlphabeticalComparator implements Comparator<FSElement>{

	public int compare(FSElement fs1, FSElement fs2) {
		return fs2.getName().compareTo(fs1.getName());
	}
	
}
