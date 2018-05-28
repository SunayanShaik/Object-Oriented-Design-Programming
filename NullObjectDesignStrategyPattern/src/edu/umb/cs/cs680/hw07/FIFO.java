package edu.umb.cs.cs680.hw07;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class FIFO implements CacheReplacementPolicy {
	
	@Override
	public void replace(LinkedHashMap<String, String> cache, String targetFile) throws IOException {
	
		byte[] encoded = Files.readAllBytes(Paths.get(targetFile));
		String fileContents = new String(encoded, StandardCharsets.UTF_8);
		cache.put(targetFile, fileContents);
	}
	
}
