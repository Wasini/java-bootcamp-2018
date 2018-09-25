package com.globant.bootcamp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import lombok.NonNull;

import com.google.common.collect.MinMaxPriorityQueue;

public class Desktop {

    private static final int CAPACITY = 15;

    private MinMaxPriorityQueue<File> fileHistory;

    public Desktop(){
        fileHistory = MinMaxPriorityQueue.orderedBy(byAccesTime)
            .maximumSize(CAPACITY)
            .create();
    }

    public boolean hasRecentFiles() {
        return !fileHistory.isEmpty();
    }

    public void openFile(@NonNull File file) {
        fileHistory.remove(file);
        file.read();
        fileHistory.add(file);
    }

    public boolean hasRecentlyOpen(File file) {
        return fileHistory.contains(file);
    }

    public File getLastOpenedFile() {
        return fileHistory.peekFirst();
    }

    public File getOldestOpenedFile() {
        return fileHistory.peekLast();
    }

    public Collection<Object> getFileHistory() {
        return Arrays.asList(fileHistory.toArray());
    }
    
    private Comparator<File> byAccesTime = new Comparator<File>() {
		@Override
		public int compare(File a, File b) {
			return (a.getAccessTime()).compareTo(b.getAccessTime());
		}
	};
}
