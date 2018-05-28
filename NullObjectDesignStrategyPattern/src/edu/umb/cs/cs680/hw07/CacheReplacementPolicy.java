package edu.umb.cs.cs680.hw07;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public interface CacheReplacementPolicy {

	public void replace(LinkedHashMap<String, String> cache, String targetFile) throws IOException;
	
}
