package edu.umb.cs.cs680.hw07;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileCache {

	private LinkedHashMap<String, String> cache;
	private int SIZE_OF_CACHE = 0;
	private CacheReplacementPolicy replacementPolicy;
	public static FileCache fileCache;

	public static FileCache getInstance(int sizeOfCache) {
		if(fileCache == null) {
			fileCache = new FileCache(sizeOfCache);
		}
		fileCache.setReplacementPolicy(new NullReplacement());
		return fileCache;
	}
	
	public static FileCache getInstance(int sizeOfCache, CacheReplacementPolicy replacementPolicy) {
		if(fileCache == null) {
			fileCache = new FileCache(sizeOfCache, replacementPolicy);
		}
		fileCache.setReplacementPolicy(replacementPolicy);
		return fileCache;
	}
	
	private FileCache(int sizeOfCache) {
		this.SIZE_OF_CACHE = sizeOfCache;
		constructCache(sizeOfCache);
	}
	
	private FileCache(int sizeOfCache, CacheReplacementPolicy replacementPolicy) {
		this.replacementPolicy = replacementPolicy;
		constructCache(sizeOfCache);
		
	}
	
	private void constructCache(int sizeOfCache) {
		this.cache = new LinkedHashMap<String, String>(sizeOfCache) {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
				return size() > sizeOfCache;
			}
		};
	}

	public String fetch(String targetFile) {
		
		return cache.get(targetFile);
	}
	
	public void replace(String targetFile) throws IOException {
		replacementPolicy.replace(cache, targetFile);
	}
	
	public CacheReplacementPolicy getReplacementPolicy() {
		return this.replacementPolicy;
	}
	
	private void setReplacementPolicy(CacheReplacementPolicy cacheReplacementPolicy) {
		this.replacementPolicy = cacheReplacementPolicy;
	}
	
	public int getCachedContentCount() {
		return this.cache.size();
	}

}
